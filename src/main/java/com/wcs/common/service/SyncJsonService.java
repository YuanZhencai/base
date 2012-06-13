package com.wcs.common.service;

import com.wcs.common.model.Synclog;
import com.wcs.common.util.NetUtils;
import com.wcs.common.util.ReflectUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Stateless
public class SyncJsonService implements Serializable {

    private static final long serialVersionUID = -4531023608569097120L;

    private static final String P_URI = "http://10.228.191.203:9085/rs/data/SAP.HR.P?";
    private static final String O_URI = "http://10.228.191.203:9085/rs/data/SAP.HR.O?";
    private static final String S_URI = "http://10.228.191.203:9085/rs/data/SAP.HR.S?";
    private static final String PU_URI = "http://10.228.191.203:9085/rs/data/SAP.HR.PU?";
    private static final String PS_URI = "http://10.228.191.203:9085/rs/data/SAP.HR.PS?";

    private static final String MAX_VERSION_SQL = "SELECT MAX(s.version) FROM Synclog s WHERE s.syncType=:tableName AND UPPER(s.syncInd)= 'Y'";
    private static final String DEL_SQL = "DELETE FROM ";

    private static final String PACKAGE = "com.wcs.common.model.";

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

            //6.删除数据库中待同步的表记录
            this.updateOldSyncData(syncList);

            //7.插入最新的数据
            this.updateNewSyncData(syncList);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
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

        //如果请求地址列表为空，则添加默认访问地址，及默认参数
        if (null == uriMap || uriMap.isEmpty()) {
            uriMap = new HashMap<String, String>();

            uriMap.put("P", P_URI);
            uriMap.put("O", O_URI);
            uriMap.put("S", S_URI);
            uriMap.put("PU", PU_URI);
            uriMap.put("PS", PS_URI);

            //请求数据时，需要加上ts版本号，所以先从数据库获取一次版本号。
            Map<String, String> versionMap = this.getVersion(uriMap);

            Set<String> keySet = versionMap.keySet();
            for (String key : keySet) {
                String param = StringUtils.isEmpty(versionMap.get(key)) ? "ts=" + versionMap.get(key) : "";
                uriMap.put(key, uriMap.get(key) + param);
            }

        }

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
     * 6.删除数据库中待同步的表记录
     *
     * @param syncList
     */
    private void updateOldSyncData(List<SyncDefineBean> syncList) {

        for (SyncDefineBean defineBean : syncList) {
            //如果result存在值，则表示之前流程处理失败，不做操作
            if (defineBean.isUpdate() && null == defineBean.getResult()) {
                Query query = this.em.createQuery(DEL_SQL + defineBean.getTableName());
                query.executeUpdate();
            }
        }

    }

    /**
     * 7.更新最新的数据
     *
     * @param syncList
     */
    private void updateNewSyncData(List<SyncDefineBean> syncList) {
        for (SyncDefineBean defineBean : syncList) {

            //如果需要更新则，保存对象。result为空，代表之前的流程没有遇到异常情况，保存最终数据
            if (defineBean.isUpdate() && null == defineBean.getResult()) {
                defineBean.setResult(ProcessResult.SUCCESS);
                for (Object obj : defineBean.getObjList()) {
                    try {
                        this.em.persist(obj);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        defineBean.setResult(ProcessResult.FAULT);
                        throw new RuntimeException();
                    }
                }
            }
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
            if (defineBean.getResult() == ProcessResult.SUCCESS) {
                synclog.setSyncType(defineBean.getTableName());
                synclog.setSyncInd("Y");
                synclog.setVersion(version);
                synclog.setRemarks(SUCCESS_INFO.replace(":tableName", defineBean.getTableName()).replace(":count", String.valueOf(defineBean.getObjList().size())));
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
     * 设置实体对象，版本号
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
            return ;
        }

        Iterator<JsonNode> jsonNodeIterator = jsonNode.iterator();

        //判断是否存在ts值，如果不存在，则返回
        if (!jsonNodeIterator.hasNext()) {
            return;
        }else{
            //设置ts版本号
            defineBean.setInd(jsonNodeIterator.next().get("ts").getTextValue());
        }

        List<Object> objList = new ArrayList<Object>();
        //如果不存在具体数据则返回
        if (!jsonNodeIterator.hasNext()) {
            defineBean.setObjList(objList);
            return;
        }

        while (jsonNodeIterator.hasNext()) {
            JsonNode bodyNode = jsonNodeIterator.next();
            Iterator<String> fieldNameIt = bodyNode.getFieldNames();
            ReflectUtils reflectUtils = new ReflectUtils(PACKAGE + defineBean.getTableName());

            while (fieldNameIt.hasNext()) {
                String key = fieldNameIt.next();
                if (key.contains("_")) {
                    //因为java字段名与网络返回的名称不一样，所以这里做特殊处理，如：defunct_ind会被转换成DefunctInd
                    String[] fieldNames = key.split("_");
                    StringBuilder buff = new StringBuilder("");

                    for (String fieldName : fieldNames) {
                        buff.append(StringUtils.capitalize(fieldName.toLowerCase()));
                    }

                    reflectUtils.setFieldValue(buff.toString().trim(), new Object[]{bodyNode.get(key).getTextValue()});
                } else {
                    reflectUtils.setFieldValue(key.toLowerCase(), new Object[]{bodyNode.get(key).getTextValue()});
                }
            }

            objList.add(reflectUtils.getObj());
        }

        //设置需要保持的是对象
        defineBean.setObjList(objList);

    }

//    private void fillDefinebean(SyncDefineBean defineBean, String jsonStr) {
//
//        //如果result存在值，不做处理，表示处理数据失败
//        if (null != defineBean.getResult()) {
//            return;
//        }
//
//        JSONArray jsonArray = JSONArray.fromObject(jsonStr);
//
//        int count = jsonArray.size();
//
//        //判断是否存在版本号
//        if (count < 1) {
//            return;
//        }
//
//        //设置版本号
//        JSONObject headerObject = jsonArray.getJSONObject(0);
//        Object ts = headerObject.get("ts");
//        if (null != ts) {
//            defineBean.setInd(ts.toString().trim());
//        }
//
//        //如果不存在具体数据则返回
//        if (count < 2) {
//            return;
//        }
//
//        //将json转换成具体对象数据
//        List<Object> objList = new ArrayList<Object>();
//        for (int i = 1; i < count; i++) {
//
//            //bodyObject里面的结构为名值对，这里key表示属性名，value表示需要设置的值
//            JSONObject bodyObject = jsonArray.getJSONObject(i);
//
//            Set<String> keySet = bodyObject.keySet();
//            ReflectUtils reflectUtils = new ReflectUtils(PACKAGE + defineBean.getTableName());
//
//            for (String key : keySet) {
//                //设置对象field属性，有可能抛出异常，表示不设置属性
//
//                if (key.contains("_")) {
//                    //因为java字段名与网络返回的名称不一样，所以这里做特殊处理，如：defunct_ind会被转换成DefunctInd
//                    String[] fieldNames = key.split("_");
//                    StringBuilder buff = new StringBuilder("");
//
//                    for (String fieldName : fieldNames) {
//                        buff.append(StringUtils.capitalize(fieldName.toLowerCase()));
//                    }
//
//                    reflectUtils.setFieldValue(buff.toString().trim(), new Object[]{bodyObject.get(key)});
//                } else {
//                    reflectUtils.setFieldValue(key.toLowerCase(), new Object[]{bodyObject.get(key)});
//                }
//            }
//
//            objList.add(reflectUtils.getObj());
//        }
//
//        //设置需要保持的是对象
//        defineBean.setObjList(objList);
//
//    }

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
    private List<Object> objList;

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

    public List<Object> getObjList() {
        return objList;
    }

    public void setObjList(List<Object> objList) {
        this.objList = objList;
    }

    public SyncJsonService.ProcessResult getResult() {
        return result;
    }

    public void setResult(SyncJsonService.ProcessResult result) {
        this.result = result;
    }
}
