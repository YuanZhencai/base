package com.wcs.common.service;

import com.wcs.common.model.Synclog;
import com.wcs.common.util.NetUtils;
import com.wcs.common.util.SqlUtils;
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
import java.util.*;

/**
 * <p>Project: BTC</p>
 * <p>Description: 负责同步数据，包括获取网络数据，删除数据，插入数据</p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:hujianguang@wcs-global.com">胡建光</a>
 */

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
     * <p>同步数据的流程控制。</p>
     *
     * return
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
     * <p>在同步流程前初始化环境</p>
     *
     * return
     */
    private void init() {
    }

    /**
     * <p>1.从数据库取得需要同步的表的信息</p>
     *
     * @param uriMap 请求地址,Map<表名，请求地址>
     * @return  版本号,Map<表名,版本号>
     */
    private Map<String, String> getVersion(Map<String, String> uriMap) {

        if (null == uriMap || uriMap.isEmpty()) {
            return null;
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
     * <p>2.添加请求参数</p>
     *
     * @param uriMap   请求地址，Map<表名，请求地址>
     * @param paramMap 需要添加的参数,Map<名，值>
     *
     *  return
     */
    private void addQueryParam(Map<String, String> uriMap, Map<String, Map<String, String>> paramMap) {

        if (null == uriMap || null == paramMap ||uriMap.isEmpty() || paramMap.isEmpty()) {
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
                queryStr.append("&");
            }
            //将解析的参数追加到uri
            String uri = uriMap.get(uriKey);
            uriMap.put(uriKey, (uri.endsWith("?") ? uri : uri + "?") + queryStr.toString().trim());

        }

    }

    /**
     * <p>3.从网络获取最新数据</p>
     *
     * @param uriMap  请求地址，Map<表名，请求地址>
     * @return 远程数据。Map<表名,远程返回的结果>
     */
    private Map<String, String> getRemoteData(Map<String, String> uriMap) {

        if(null == uriMap || uriMap.isEmpty()){
            return null;
        }

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
     * <p>4.解析取得的数据，将其转成java对象。这里所接收的数据格式为json格式</p>
     *
     * @param mdsData  json数据
     * @return 同步对象的定义bean
     */
    private List<SyncDefineBean> marshalData(Map<String, String> mdsData) {

        if(null == mdsData || mdsData.isEmpty()){
            return null;
        }

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
     * <p>5.根据版本判断是否更新表</p>
     *
     * @param indMap   Map<表名，版本号>
     * @param syncList  同步对象
     */
    private void updateInd(Map<String, String> indMap, List<SyncDefineBean> syncList) {

        if(null == indMap || null == syncList || indMap.isEmpty() || syncList.isEmpty()){
            return ;
        }

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
     * <p>更新数据库，这里做事务的原子处理，如果抛出异常，则全部回滚</p>
     *
     * @param syncList   同步对象
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
     * <p>6.删除数据库中待同步的表记录</p>
     *
     * @param syncList   同步对象
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
     * <p>7.更新最新的数据</p>
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
     * <p>8.记录日志</p>
     *
     * @param syncList
     */
    private void log(List<SyncDefineBean> syncList) {

        if (null == syncList || syncList.isEmpty()) {
            return;
        }

        for (SyncDefineBean defineBean : syncList) {
            Synclog synclog = new Synclog();

            Long version = StringUtils.isNumeric(defineBean.getInd()) ? Long.parseLong(defineBean.getInd()) : null;
            if (defineBean.getResult() == ProcessResult.SUCCESS) {
                synclog.setSyncType(defineBean.getTableName());
                synclog.setSyncInd("Y");
                synclog.setVersion(version);
                synclog.setRemarks(SUCCESS_INFO.replace(":tableName", defineBean.getTableName()).replace(":count", String.valueOf(defineBean.getSqlList().size())));
            } else if (defineBean.getResult() == ProcessResult.VER_EQUAL) {
                synclog.setSyncType(defineBean.getTableName());
                synclog.setSyncInd("Y");
                synclog.setVersion(version);
                synclog.setRemarks(VER_EQUAL_INFO.replace(":tableName", defineBean.getTableName()));
            } else if (defineBean.getResult() == ProcessResult.FAULT) {
                synclog.setSyncType(defineBean.getTableName());
                synclog.setSyncInd("N");
                synclog.setVersion(version);
                synclog.setRemarks(FAULT_INFO.replace(":tableName", defineBean.getTableName()));
            } else if (defineBean.getResult() == ProcessResult.SERVER_EXCEPT) {
                synclog.setSyncType(defineBean.getTableName());
                synclog.setSyncInd("N");
                synclog.setVersion(version);
                synclog.setRemarks(SERVER_EXCEPT_INFO.replace(":tableName", defineBean.getTableName()));
            } else {
                synclog.setSyncType(defineBean.getTableName());
                synclog.setSyncInd("N");
                synclog.setVersion(version);
                synclog.setRemarks(FAULT_INFO.replace(":tableName", defineBean.getTableName()));
            }

            this.em.persist(synclog);

        }
    }

    /**
     * <p>设置插入语句，版本号 </p>
     *
     * @param defineBean
     * @param jsonStr
     */
    private void fillDefinebean(SyncDefineBean defineBean, String jsonStr) {

        //如果result存在值，不做处理，表示处理数据失败
        if (null == defineBean || null != defineBean.getResult() || StringUtils.isEmpty(jsonStr)) {
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

        //添加需要保持的是对象
        defineBean.setSqlList(sqlList);

    }

    private void destroy() {
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


/**
 * <p>Project: BTC</p>
 * <p>Description: 存储需要插入数据的状态，如果插入结果，表名</p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:hujianguang@wcs-global.com">胡建光</a>
 */
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
