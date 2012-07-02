package com.wcs.common.service;

import com.wcs.common.util.ConfigManager;
import com.wcs.common.util.JDBCUtils;
import com.wcs.common.util.NetUtils;
import com.wcs.common.util.SqlUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.*;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@TransactionAttribute(value = TransactionAttributeType.NEVER)
public class SyncJsonService implements Serializable {

    private static final long serialVersionUID = -4531023608569097120L;

    private static Logger logger;

    private static final String MAX_VERSION_SQL = "SELECT MAX(version) FROM SYNCLOG WHERE SYNC_TYPE=? AND UPPER(SYNC_IND)= 'Y'";
    private static final String DEL_SQL = "DELETE FROM ";

    private static final String INSERT_LOG_SQL = "INSERT INTO SYNCLOG(ID,VERSION,SYNC_IND,SYNC_TYPE,REMARKS) VALUES(nextval for SRC,?,?,?,?)";

    //SUCCESS:同步成功，SUCCESS_EQUAL:版本号相同，DELETE_FAULT:删除失败，INSERT_FAULT:插入失败，DB_VER_NULL:数据库版本号为空，NET_VER_NULL:网络数据版本号为空，VER_NOT_MATCH:版本号不匹配，NET_DATA_NULL:网络数据库为空，FAULT:由于其它表的失败，导致本表失败
    public static enum ProcessResult {
        SUCCESS, SUCCESS_EQUAL, DELETE_FAULT, INSERT_FAULT, DB_VER_NULL, NET_VER_NULL, VER_NOT_MATCH, NET_DATA_NULL, FAULT, JSON_ERROR,
    }

    private static final String SUCCESS_INFO = ":tableName同步成功，更新:count数据";
    private static final String SUCCESS_EQUAL_INFO = ":tableName同步成功，版本号相同";
    private static final String DELETE_FAULT_INFO = ":tableName同步失败，插入数据库失败";
    private static final String INSERT_FAULT_INFO = ":tableName同步失败，插入数据库失败";
    private static final String DB_VER_NULL_INFO = ":tableName同步失败，数据库版本为空";
    private static final String NET_VER_NULL_INFO = ":tableName同步失败，网络返回的版本号为空";
    private static final String VER_NOT_MATCH_INFO = ":tableName同步失败，数据库版本号大于网络返回的版本号";
    private static final String NET_DATA_NULL_INFO = ":tableName同步失败，网络数据为空";
    private static final String JSON_ERROR_INFO = ":tableName同步失败，网络数据格式异常";
    private static final String FAULT_INFO = ":tableName同步失败";


    //key：表名，value：请求地址
    private Map<String, String> uriMap;

    //请求参数：Map<表名，Map<名，值>>
    private Map<String, Map<String, String>> paramMap;

    @EJB
    public JDBCUtils jdbcUtils;

    static {
        logger = LoggerFactory.getLogger(SyncJsonService.class);
    }

    /**
     * <p>同步数据的流程控制。</p>
     * <p/>
     * return
     */
    public void process() {

        logger.debug("Start:启动同步服务.");

        //如果请求地址为空，则添加Map
        if (null == uriMap || uriMap.isEmpty()) {
            uriMap =ConfigManager.getConfigValueMapByFilter("url_");
        }

        //在同步流程前初始化环境
        this.init();

        List<SyncDefineBean> syncList = new ArrayList<SyncDefineBean>();

        try {

            //1.从数据库取得需要同步的表的信息。返回值：key 表名；value 版本号
            Map<String, String> indMap = this.getVersion(uriMap);

            //2.添加请求参数
            this.addQueryParam(uriMap, paramMap);

            //2.建立同步对象及初始化同步的表名
            this.buildSyncBean(indMap, syncList);

            //3.从网络获取最新数据
            Map<String, String> mdsData = this.getRemoteData(uriMap, syncList);

            //4.解析取得的数据，将其转成java对象。这里所接收的数据格式为json格式
            if (this.isCorrect(syncList)) {
                this.marshalData(mdsData, syncList);
            } else {
                return;
            }

            //5.根据版本判断是否更新表
            if (this.isCorrect(syncList)) {
                this.updateInd(indMap, syncList);
            } else {
                return;
            }

            //将删除和插入控制在一个事务中
            this.updateData(syncList);

        } finally {

            //改变同步对象状态
            this.changeStatus(syncList);

            //8.记录日志
            this.log(syncList);

            //在同步流程结束前调用
            this.destroy();
        }

        logger.debug("End:同步服务已停止.");

    }

    /**
     * <p>在同步流程前初始化环境</p>
     * <p/>
     * return
     */
    private void init() {
        logger.debug("Start : init.");
        logger.debug("End : init.");

    }

    /**
     * <p>1.从数据库取得需要同步的表的信息</p>
     *
     * @param uriMap 请求地址,Map<表名，请求地址>
     * @return 版本号, Map<表名,版本号>
     */
    private Map<String, String> getVersion(Map<String, String> uriMap) {
        logger.debug("Start : getVersion.");

        Map<String, String> indMap = new HashMap<String, String>();

        Set<String> keySet = uriMap.keySet();

        try {
            PreparedStatement ps = jdbcUtils.getPreparedStatement(MAX_VERSION_SQL);
            for (String key : keySet) {

                ps.setString(1, key.trim());

                ResultSet rs = ps.executeQuery();

                //如果版本号为空，则代表首次连接,设置为-1
                if (rs.next()) {
                    indMap.put(key, String.valueOf(rs.getLong(1)));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        jdbcUtils.destroy();
        logger.debug("End : getVersion.");
        return indMap;
    }

    /**
     * <p>2.添加请求参数</p>
     *
     * @param uriMap   请求地址，Map<表名，请求地址>
     * @param paramMap 需要添加的参数,Map<名，值>
     *                 <p/>
     *                 return
     */
    private void addQueryParam(Map<String, String> uriMap, Map<String, Map<String, String>> paramMap) {

        logger.debug("Start : addQueryParam.");
        if (null == uriMap || null == paramMap || uriMap.isEmpty() || paramMap.isEmpty()) {
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
        logger.debug("End : addQueryParam.");

    }

    /**
     * <p>建立同步对象及初始化需要同步的表名</p>
     *
     * @param indMap
     * @param syncList
     */
    private void buildSyncBean(Map<String, String> indMap, List<SyncDefineBean> syncList) {

        logger.debug("Start : buildSyncBean.");

        if (null == indMap || 0 == indMap.size()) {
            return;
        }
        Set<String> keySet = indMap.keySet();

        for (String key : keySet) {
            SyncDefineBean defineBean = new SyncDefineBean();
            defineBean.setTableName(key);
            syncList.add(defineBean);
        }

        logger.debug("End : buildSyncBean.");

    }

    /**
     * <p>3.从网络获取最新数据</p>
     *
     * @param uriMap 请求地址，Map<表名，请求地址>
     * @return 远程数据。Map<表名,远程返回的结果>
     */
    private Map<String, String> getRemoteData(Map<String, String> uriMap, List<SyncDefineBean> syncList) {

        logger.debug("Start : getRemoteData.");

        Set<String> keySet = uriMap.keySet();

        Map<String, String> returnMap = new HashMap<String, String>();

        for (String key : keySet) {
            String returnStr = NetUtils.getURLData(uriMap.get(key));

            //如果为空，则设置对应的bean状态为空
            if (StringUtils.isEmpty(returnStr)) {
                for (SyncDefineBean defineBean : syncList) {
                    if (defineBean.getTableName().equalsIgnoreCase(key)) {
                        defineBean.setResult(ProcessResult.NET_DATA_NULL);
                        logger.error("Err : " + NET_DATA_NULL_INFO.replace(":tableName", defineBean.getTableName())  + ".");
                    }
                }
            }
            returnMap.put(key, returnStr);
        }

        logger.debug("End : getRemoteData.");

        return returnMap;
    }

    /**
     * <p>4.解析取得的数据，将其转成java对象。这里所接收的数据格式为json格式</p>
     *
     * @param mdsData json数据
     * @return 同步对象的定义bean
     */
    private void marshalData(Map<String, String> mdsData, List<SyncDefineBean> syncList) {

        logger.debug("Start : marshalData.");

        if (null == mdsData || mdsData.isEmpty()) {
            return;
        }

        for (SyncDefineBean defineBean : syncList) {
            //设置实体对象，版本号

            String jsonStr = mdsData.get(defineBean.getTableName());

            //如果result存在值，不做处理，表示处理数据失败
            if (StringUtils.isEmpty(jsonStr)) {
                defineBean.setResult(ProcessResult.NET_DATA_NULL);
                logger.error("Err : " + NET_DATA_NULL_INFO.replace(":tableName", defineBean.getTableName())  + ".");
                break;
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try {
                jsonNode = mapper.readTree(jsonStr);
            } catch (IOException e) {
                defineBean.setResult(ProcessResult.JSON_ERROR);
                logger.error("Err : " + JSON_ERROR_INFO.replace(":tableName", defineBean.getTableName())  + ".");
                e.printStackTrace();
                return;
            }

            Iterator<JsonNode> jsonNodeIterator = jsonNode.iterator();

            //判断是否存在ts值，如果不存在，则返回
            if (!jsonNodeIterator.hasNext()) {
                defineBean.setResult(ProcessResult.NET_VER_NULL);
                logger.error("Err : " + NET_VER_NULL_INFO.replace(":tableName", defineBean.getTableName())  + ".");
                continue;
            } else {
                //设置ts版本号
                String ts = jsonNodeIterator.next().get("ts").getTextValue();

                if (StringUtils.isNumeric(ts)) {
                    defineBean.setInd(ts);
                } else {
                    defineBean.setResult(ProcessResult.NET_VER_NULL);
                    logger.error("Err : " + NET_VER_NULL_INFO.replace(":tableName", defineBean.getTableName())  + ".");
                }
            }

            List<String> sqlList = new ArrayList<String>();
            //如果不存在具体数据，则继续
            if (!jsonNodeIterator.hasNext()) {
                defineBean.setSqlList(sqlList);
                continue;
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

        logger.debug("End : marshalData.");

    }

    /**
     * <p>5.根据版本判断是否更新表</p>
     *
     * @param indMap   Map<表名，版本号>
     * @param syncList 同步对象
     */
    private void updateInd(Map<String, String> indMap, List<SyncDefineBean> syncList) {

        logger.debug("Start : updateInd.");

        if (null == indMap || indMap.isEmpty()) {
            return;
        }

        Set<String> keySet = indMap.keySet();

        for (SyncDefineBean defineBean : syncList) {
            long ind = StringUtils.isNumeric(defineBean.getInd()) ? Long.parseLong(defineBean.getInd()) : -1;

            for (String key : keySet) {

                long ver = StringUtils.isNumeric(indMap.get(key)) ? Long.parseLong(indMap.get(key)) : -2;

                if (key.equalsIgnoreCase(defineBean.getTableName())) {
                    if (ind > ver) {
                        defineBean.setUpdate(true);
                        break;
                    } else if (ind == ver) {
                        break;
                    } else {
                        defineBean.setResult(ProcessResult.VER_NOT_MATCH);
                        logger.error("Err : " + VER_NOT_MATCH_INFO.replace(":tableName", defineBean.getTableName())  + ".");
                        break;
                    }
                }
            }
        }

        logger.debug("End : updateInd.");

    }

    /**
     * <p>更新数据库，这里做事务的原子处理，如果抛出异常，则全部回滚</p>
     *
     * @param syncList 同步对象
     */
//    @TransactionAttribute(value = TransactionAttributeType.NEVER)
    private void updateData(List<SyncDefineBean> syncList) {

        logger.debug("Start : updateData.");
        try {
            jdbcUtils.initConn();

            //6.删除数据库中待同步的表记录
            if (this.isCorrect(syncList)) {
                this.updateOldSyncData(syncList);
            } else {
                return;
            }

            //7.插入最新的数据
            if (this.isCorrect(syncList)) {
                this.updateNewSyncData(syncList);
            } else {
                return;
            }

            jdbcUtils.destroy();

        } catch (Exception ex) {
            ex.printStackTrace();
            jdbcUtils.rollData();
        }

        logger.debug("End : updateData.");

    }

    /**
     * <p>6.删除数据库中待同步的表记录</p>
     *
     * @param syncList 同步对象
     */
//    @TransactionAttribute(value = TransactionAttributeType.NEVER)
    private void updateOldSyncData(List<SyncDefineBean> syncList) {

        logger.debug("Start : updateOldSyncData.");

        Statement stmt = jdbcUtils.getStatement();
        for (SyncDefineBean defineBean : syncList) {
            try {
                //如果result存在值，则表示之前流程处理失败，不做操作
                if (defineBean.isUpdate() && null == defineBean.getResult()) {
                    stmt.addBatch(DEL_SQL + defineBean.getTableName());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                defineBean.setResult(ProcessResult.DELETE_FAULT);
                logger.error("Err : " + DELETE_FAULT_INFO.replace(":tableName", defineBean.getTableName())  + ".");
            }

        }

        try {
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new RuntimeException();
        }

        logger.debug("End : updateOldSyncData.");
    }

    /**
     * <p>7.更新最新的数据</p>
     *
     * @param syncList
     */
//    @TransactionAttribute(value = TransactionAttributeType.NEVER)
    private void updateNewSyncData(List<SyncDefineBean> syncList) {

        logger.debug("Start : updateNewSyncData.");

        boolean hasErr = false;
        Statement stmt = jdbcUtils.getStatement();

        for (SyncDefineBean defineBean : syncList) {

            try {
                //如果需要更新则，保存对象。result为空，代表之前的流程没有遇到异常情况，保存最终数据
                if (defineBean.isUpdate() && null == defineBean.getResult()) {
                    defineBean.setResult(ProcessResult.SUCCESS);

                    for (String sql : defineBean.getSqlList()) {
                        stmt.addBatch(sql);
                    }
                } else if (!defineBean.isUpdate() && null == defineBean.getResult()) {      //版本号相同时，状态值为空，但也不会更新
                    defineBean.setResult(ProcessResult.SUCCESS_EQUAL);
                    logger.debug("Info : " + SUCCESS_EQUAL_INFO.replace(":tableName", defineBean.getTableName())  + ".");
                }
                stmt.executeBatch();
            } catch (Exception ex) {
                ex.printStackTrace();
                defineBean.setResult(ProcessResult.INSERT_FAULT);
                logger.error("Err : " + INSERT_FAULT_INFO.replace(":tableName", defineBean.getTableName())  + ".");
                hasErr = true;
                break;
            }
        }

        //由于保存到数据库为最后一步，这里的特殊性，如果存在一个保存对象失败，则设置其它对象为空
        if (hasErr == true) {
            for (SyncDefineBean defineBean : syncList) {
                if (ProcessResult.SUCCESS == defineBean.getResult() || ProcessResult.SUCCESS_EQUAL == defineBean.getResult()) {
                    defineBean.setResult(null);
                }
            }
            throw new RuntimeException();
        }

        logger.debug("End : updateNewSyncData.");

    }

    /**
     * <p>8.记录日志</p>
     *
     * @param syncList
     */
    @TransactionAttribute(value = TransactionAttributeType.NEVER)
    private void log(List<SyncDefineBean> syncList) {

        logger.debug("Start : log.");
        if (null == syncList || syncList.isEmpty()) {
            return;
        }

        try {
            PreparedStatement ps = jdbcUtils.getPreparedStatement(INSERT_LOG_SQL);
            for (SyncDefineBean defineBean : syncList) {
                Long version = StringUtils.isNumeric(defineBean.getInd()) ? Long.parseLong(defineBean.getInd()) : -1;

                if (defineBean.getResult() == ProcessResult.SUCCESS) {

                    ps.setLong(1, version);
                    ps.setString(2, "Y");
                    ps.setString(3, defineBean.getTableName());
                    ps.setString(4, SUCCESS_INFO.replace(":tableName", defineBean.getTableName()).replace(":count", String.valueOf(defineBean.getSqlList().size())));

                } else if (defineBean.getResult() == ProcessResult.SUCCESS_EQUAL) {
                    ps.setLong(1, version);
                    ps.setString(2, "Y");
                    ps.setString(3, defineBean.getTableName());
                    ps.setString(4, SUCCESS_EQUAL_INFO.replace(":tableName", defineBean.getTableName()));
                } else if (defineBean.getResult() == ProcessResult.NET_DATA_NULL) {
                    ps.setLong(1, version);
                    ps.setString(2, "N");
                    ps.setString(3, defineBean.getTableName());
                    ps.setString(4, NET_VER_NULL_INFO.replace(":tableName", defineBean.getTableName()));
                } else if (defineBean.getResult() == ProcessResult.DELETE_FAULT) {
                    ps.setLong(1, version);
                    ps.setString(2, "N");
                    ps.setString(3, defineBean.getTableName());
                    ps.setString(4, DELETE_FAULT_INFO.replace(":tableName", defineBean.getTableName()));
                } else if (defineBean.getResult() == ProcessResult.INSERT_FAULT) {
                    ps.setLong(1, version);
                    ps.setString(2, "N");
                    ps.setString(3, defineBean.getTableName());
                    ps.setString(4, INSERT_FAULT_INFO.replace(":tableName", defineBean.getTableName()));
                } else if (defineBean.getResult() == ProcessResult.VER_NOT_MATCH) {
                    ps.setLong(1, version);
                    ps.setString(2, "N");
                    ps.setString(3, defineBean.getTableName());
                    ps.setString(4, VER_NOT_MATCH_INFO.replace(":tableName", defineBean.getTableName()));
                } else if (defineBean.getResult() == ProcessResult.DB_VER_NULL) {
                    ps.setLong(1, version);
                    ps.setString(2, "N");
                    ps.setString(3, defineBean.getTableName());
                    ps.setString(4, DB_VER_NULL_INFO.replace(":tableName", defineBean.getTableName()));
                } else if (defineBean.getResult() == ProcessResult.NET_VER_NULL) {
                    ps.setLong(1, version);
                    ps.setString(2, "N");
                    ps.setString(3, defineBean.getTableName());
                    ps.setString(4, NET_VER_NULL_INFO.replace(":tableName", defineBean.getTableName()));
                } else if (defineBean.getResult() == ProcessResult.NET_DATA_NULL) {
                    ps.setLong(1, version);
                    ps.setString(2, "N");
                    ps.setString(3, defineBean.getTableName());
                    ps.setString(4, NET_DATA_NULL_INFO.replace(":tableName", defineBean.getTableName()));
                } else if (defineBean.getResult() == ProcessResult.JSON_ERROR) {
                    ps.setLong(1, version);
                    ps.setString(2, "N");
                    ps.setString(3, defineBean.getTableName());
                    ps.setString(4, JSON_ERROR_INFO.replace(":tableName", defineBean.getTableName()));
                } else if (defineBean.getResult() == ProcessResult.FAULT) {
                    ps.setLong(1, version);
                    ps.setString(2, "N");
                    ps.setString(3, defineBean.getTableName());
                    ps.setString(4, FAULT_INFO.replace(":tableName", defineBean.getTableName()));
                }

                ps.addBatch();

            }
            ps.executeBatch();
            jdbcUtils.destroy();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            jdbcUtils.rollData();
        }

        logger.debug("End : log.");
    }

    private void destroy() {
        logger.debug("Start : destroy.");

        this.uriMap = new HashMap<String, String>();
        this.paramMap = new HashMap<String, Map<String, String>>();

        logger.debug("End : destroy.");

    }

    /**
     * <p>验证同步对象状态是否正确</p>
     *
     * @param syncList
     * @return
     */
    private boolean isCorrect(List<SyncDefineBean> syncList) {

        for (SyncDefineBean defineBean : syncList) {
            if (null != defineBean.getResult()) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>如果存在一个对象状态不正确，则将其它对象的状态设置为失败</p>
     *
     * @param syncList
     */
    private void changeStatus(List<SyncDefineBean> syncList) {

        if (!isCorrect(syncList)) {
            for (SyncDefineBean defineBean : syncList) {
                if (null == defineBean.getResult()) {
                    defineBean.setResult(ProcessResult.FAULT);
                }
            }
        }

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
