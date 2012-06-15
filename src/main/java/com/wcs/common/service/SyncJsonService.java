package com.wcs.common.service;

import com.wcs.common.model.Synclog;
import com.wcs.common.util.NetUtils;
import com.wcs.common.util.SqlUtils;
import org.apache.activemq.transport.stomp.Stomp;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

@Stateless
public class SyncJsonService implements Serializable {

    private static final long serialVersionUID = -4531023608569097120L;

    private static final String MAX_VERSION_SQL = "SELECT MAX(s.version) FROM Synclog s WHERE s.syncType=:tableName AND UPPER(s.syncInd)= 'Y'";
    private static final String DEL_SQL = "DELETE FROM ";

    //SUCCESS:同步成功，FAULT:同步失败；VER_EQUAL:版本号相等；SERVER_EXCEPT:网络异常
    public static enum ProcessResult {
        SUCCESS, FAULT, VER_EQUAL, SERVER_EXCEPT
    }

    private static final String SERVER_EXCEPT_INFO = "服务端异常，:tableName表同步失败";

    private static final String VER_EQUAL_INFO = "同步成功，:tableName表版本号相同";
    private static final String SUCCESS_INFO = "同步成功，:tableName表更新:count数据";
    private static final String FAULT_INFO = "同步失败，:tableName表插入失败";


    //key：表名，value：请求地址
    private Map<String, String> uriMap;

    //请求参数：Map<表名，Map<名，值>>
    private Map<String, Map<String, String>> paramMap;

    //获取数据源
    @Resource(name = "BTCBASE")
    private DataSource dataSource;

    @PersistenceContext
    public EntityManager em;

    /**
     * 同步数据的流程控制。
     */
    public void process() {

        //在同步流程前初始化环境
        this.init();

        List<SyncDefineBean> syncList = null;

        try {

            //1.从数据库取得需要同步的表的信息。返回值：key 表名；value 版本号
            Map<String, String> indMap = this.getVersion(uriMap);

            //2.添加请求参数
            this.addQueryParam(uriMap, paramMap);

            //3.从网络获取最新数据
            Map<String, String> mdsData = this.getRemoteData(uriMap);

            //4.解析取得的数据，将其转成java对象。这里所接收的数据格式为json格式
            syncList = this.marshalData(mdsData);

            //5.根据版本判断是否更新表
            this.updateInd(indMap, syncList);

            //将删除和插入控制在一个事务中
            this.updateData(syncList);

        } finally {
            //8.记录日志
            this.log(syncList);

            //在同步流程结束前调用
            this.destroy();
        }

    }

    /**
     * 在同步流程前初始化环境
     */
    public void init() {
    }

    /**
     * 1.从数据库取得需要同步的表的信息。返回值：key 表名；value 版本号
     *
     * @param uriMap 请求地址
     * @return
     */
    public Map<String, String> getVersion(Map<String, String> uriMap) {

        if (null == uriMap || uriMap.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Map<String, String> indMap = new HashMap<String, String>();

        Set<String> keySet = uriMap.keySet();

        Query query = em.createQuery(MAX_VERSION_SQL);
        for (String key : keySet) {

            query.setParameter("tableName", key);
            Object version = query.getSingleResult();

            //如果版本号为空，则代表首次连接,直接返回空Map对象
            if (null != version) {
                indMap.put(key, version.toString().trim());
            } else {
                return new HashMap<String, String>();
            }

        }
        return indMap;
    }

    /**
     * 2.添加请求参数
     *
     * @param uriMap   请求地址，这里的key值为表名
     * @param paramMap 需要添加的参数，这里的key值为表名
     */
    private void addQueryParam(Map<String, String> uriMap, Map<String, Map<String, String>> paramMap) {

        if (null == paramMap || paramMap.isEmpty()) {
            return;
        }

        Set<String> uriSet = uriMap.keySet();

        for (String uriKey : uriSet) {

            //根据key值（这里代表的是表名），取的请求参数名值对
            Map<String, String> paramSet = paramMap.get(uriKey);
            Set<String> paramsSet = paramSet.keySet();

            //临时存储请求值
            StringBuilder queryStr = new StringBuilder("");

            for (String paramKey : paramsSet) {
                queryStr.append(paramKey);
                queryStr.append("=");
                queryStr.append(paramSet.get(paramKey));
            }
            //将解析的参数追加到uri
            String uri = uriMap.get(uriKey);
            uriMap.put(uriKey, (uri.endsWith("?") ? uri : uri + "?") + queryStr.toString().trim());

        }

    }

    /**
     * 3.从网络获取最新数据
     *
     * @param uriMap
     * @return 远程数据。key 表名；value 远程返回的结果
     */
    private Map<String, String> getRemoteData(Map<String, String> uriMap) {

        Set<String> keySet = uriMap.keySet();

        Map<String, String> returnMap = new HashMap<String, String>();

        for (String key : keySet) {
            String returnStr = NetUtils.getURLData(uriMap.get(key));
            if (StringUtils.isEmpty(returnStr)) {
                returnStr = "[]";
            }
            returnMap.put(key, returnStr.trim());
        }

        return returnMap;
    }

    /**
     * 4.解析取得的数据，将其转成java对象。这里所接收的数据格式为json格式
     *
     * @param mdsData
     * @return 同步数据的对象列表,
     */
    private List<SyncDefineBean> marshalData(Map<String, String> mdsData) {

        List<SyncDefineBean> syncDefineBeanList = new ArrayList<SyncDefineBean>();
        Set<String> keySet = mdsData.keySet();


        //检查mds数据是否存在[]，如果存在表示返回的数据有误，不做后续更新处理,及所有表都不同步
        ProcessResult result = null;
        for (String key : keySet) {
            if (null == mdsData.get(key) || "[]".equals(mdsData.get(key))) {
                result = ProcessResult.SERVER_EXCEPT;
                break;
            }
        }

        for (String key : keySet) {
            SyncDefineBean defineBean = new SyncDefineBean();
            defineBean.setTableName(key);

            //如果服务器端获取数据为[]，或为空，则同步失败
            if (result == ProcessResult.SERVER_EXCEPT) {
                defineBean.setResult(result);
            }

            //设置实体对象，版本号
            this.fillDefinebean(defineBean, mdsData.get(key));
            syncDefineBeanList.add(defineBean);
        }

        return syncDefineBeanList;
    }

    /**
     * 5.根据版本判断是否更新表
     *
     * @param indMap
     * @param syncList
     */
    private void updateInd(Map<String, String> indMap, List<SyncDefineBean> syncList) {
        Set<String> keySet = indMap.keySet();

        for (SyncDefineBean defineBean : syncList) {
            //存在result值，表示流程处理失败，不做后续处理
            if (null != defineBean.getResult()) {
                break;
            }

            long ind = StringUtils.isNumeric(defineBean.getInd()) ? Long.parseLong(defineBean.getInd()) : -1;

            for (String key : keySet) {

                long ver = StringUtils.isNumeric(indMap.get(key)) ? Long.parseLong(indMap.get(key)) : -2;

                if (key.equalsIgnoreCase(defineBean.getTableName())) {
                    if (ind == ver) {
                        defineBean.setResult(ProcessResult.VER_EQUAL);
                        break;
                    } else if (ind > ver) {
                        defineBean.setUpdate(true);
                        break;
                    }
                }
            }
        }

    }

    /**
     * 更新数据库，这里做事务的原子处理，如果抛出异常，则全部回滚
     *
     * @param syncList
     */
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    private void updateData(List<SyncDefineBean> syncList) {
        //开启事务
        Connection conn = null;
        Statement stmt = null;
        boolean updateSuccess = true;
        try {
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            stmt = conn.createStatement();

            //6.删除数据库中待同步的表记录
            this.updateOldSyncData(stmt, syncList);

            //7.插入最新的数据
            this.updateNewSyncData(stmt, syncList);

        } catch (Exception ex) {
            updateSuccess = false;
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } finally {

            if (updateSuccess == false) {
                for (SyncDefineBean defineBean : syncList) {
                    defineBean.setResult(ProcessResult.FAULT);
                }
            }

            try {
                if (null != conn || !conn.isClosed()) {
                    stmt = null;
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    /**
     * 6.删除数据库中待同步的表记录
     *
     * @param syncList
     */
    private void updateOldSyncData(Statement stmt, List<SyncDefineBean> syncList) throws Exception {

        for (SyncDefineBean defineBean : syncList) {

            //如果result存在值，则表示之前流程处理失败，不做操作
            if (defineBean.isUpdate() && null == defineBean.getResult()) {
                stmt.addBatch(DEL_SQL + defineBean.getTableName());
            }

            stmt.executeBatch();

        }
    }

    /**
     * 7.更新最新的数据
     *
     * @param syncList
     */
    private void updateNewSyncData(Statement stmt, List<SyncDefineBean> syncList) throws Exception {

        for (SyncDefineBean defineBean : syncList) {

            //如果需要更新则，保存对象。result为空，代表之前的流程没有遇到异常情况，保存最终数据
            if (defineBean.isUpdate() && null == defineBean.getResult()) {
                defineBean.setResult(ProcessResult.SUCCESS);

                for (String sql : defineBean.getSqlList()) {
                    stmt.addBatch(sql);
                }
            }
            defineBean.setCount(stmt.executeBatch().length);
        }
    }

    /**
     * 8.记录日志
     *
     * @param syncList
     */
    private void log(List<SyncDefineBean> syncList) {

        if (null == syncList) {
            return;
        }

        for (SyncDefineBean defineBean : syncList) {
            Synclog synclog = new Synclog();

            Long version = StringUtils.isNumeric(defineBean.getInd()) ? Long.parseLong(defineBean.getInd()) : null;
            Timestamp currentTime = new Timestamp(this.findCurrentTimestamp().getTime());
            if (defineBean.getResult() == ProcessResult.SUCCESS) {
                synclog.setSyncType(defineBean.getTableName());
                synclog.setSyncInd("Y");
                synclog.setVersion(version);
                synclog.setSyncDatetime(currentTime);
                synclog.setRemarks(SUCCESS_INFO.replace(":tableName", defineBean.getTableName()).replace(":count", String.valueOf(defineBean.getSqlList().size())));
            } else if (defineBean.getResult() == ProcessResult.VER_EQUAL) {
                synclog.setSyncType(defineBean.getTableName());
                synclog.setSyncInd("Y");
                synclog.setVersion(version);
                synclog.setSyncDatetime(currentTime);
                synclog.setRemarks(VER_EQUAL_INFO.replace(":tableName", defineBean.getTableName()));
            } else if (defineBean.getResult() == ProcessResult.FAULT) {
                synclog.setSyncType(defineBean.getTableName());
                synclog.setSyncInd("N");
                synclog.setVersion(version);
                synclog.setSyncDatetime(currentTime);
                synclog.setRemarks(FAULT_INFO.replace(":tableName", defineBean.getTableName()));
            } else if (defineBean.getResult() == ProcessResult.SERVER_EXCEPT) {
                synclog.setSyncType(defineBean.getTableName());
                synclog.setSyncInd("N");
                synclog.setVersion(version);
                synclog.setSyncDatetime(currentTime);
                synclog.setRemarks(SERVER_EXCEPT_INFO.replace(":tableName", defineBean.getTableName()));
            } else {
                synclog.setSyncType(defineBean.getTableName());
                synclog.setSyncInd("N");
                synclog.setVersion(version);
                synclog.setSyncDatetime(currentTime);
                synclog.setRemarks(FAULT_INFO.replace(":tableName", defineBean.getTableName()));
            }

            this.em.persist(synclog);

        }
    }

    public Date findCurrentTimestamp() {
        Query query = em.createNativeQuery("SELECT current timestamp FROM sysibm.sysdummy1", Timestamp.class);
        return new Date(Timestamp.valueOf(query.getSingleResult().toString()).getTime());
    }

    /**
     * 设置插入语句，版本号
     *
     * @param defineBean
     * @param jsonStr
     */
    private void fillDefinebean(SyncDefineBean defineBean, String jsonStr) {

        //如果result存在值，不做处理，表示处理数据失败
        if (null != defineBean.getResult()) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Iterator<JsonNode> jsonNodeIterator = jsonNode.iterator();

        //判断是否存在ts值，如果不存在，则返回
        if (!jsonNodeIterator.hasNext()) {
            return;
        } else {
            //设置ts版本号
            defineBean.setInd(jsonNodeIterator.next().get("ts").getTextValue());
        }

        List<String> sqlList = new ArrayList<String>();
        //如果不存在具体数据则返回
        if (!jsonNodeIterator.hasNext()) {
            defineBean.setSqlList(sqlList);
            return;
        }

        while (jsonNodeIterator.hasNext()) {
            JsonNode bodyNode = jsonNodeIterator.next();
            Iterator<String> fieldNameIt = bodyNode.getFieldNames();

            //存储数据库，字段名与字段值
            Map<String, String> map = new HashMap<String, String>();

            while (fieldNameIt.hasNext()) {
                String key = fieldNameIt.next();
                String value = bodyNode.get(key).getTextValue();
                map.put(key, value);
            }
            String sql = SqlUtils.buildInsertSql(defineBean.getTableName(), map);
            if (!StringUtils.isEmpty(sql)) {
                sqlList.add(sql);
            }
        }

        //设置需要保持的是对象
        defineBean.setSqlList(sqlList);

    }

    public void destroy() {
        uriMap = null;
        paramMap = null;
    }

    public void setUriMap(Map<String, String> uriMap) {
        this.uriMap = uriMap;
    }

    public void setParamMap(Map<String, Map<String, String>> paramMap) {
        this.paramMap = paramMap;
    }

}

class SyncDefineBean {

    //表名
    private String tableName;

    //版本号
    private String ind;

    //是否需要更新
    private boolean update;

    //流程处理结果;
    private SyncJsonService.ProcessResult result;

    //需要保存的对象
    private List<String> sqlList;

    private int count;

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getInd() {
        return ind;
    }

    public void setInd(String ind) {
        this.ind = ind;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public List<String> getSqlList() {
        return sqlList;
    }

    public void setSqlList(List<String> sqlList) {
        this.sqlList = sqlList;
    }

    public SyncJsonService.ProcessResult getResult() {
        return result;
    }

    public void setResult(SyncJsonService.ProcessResult result) {
        this.result = result;
    }

}
