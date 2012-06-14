package com.wcs.common.service;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Stateless
public class ScheduService implements Serializable {

    private static final long serialVersionUID = -4531023608569097125L;

    private static final String P_URI = "http://10.228.191.203:9085/rs/data/SAP.HR.P?";
    private static final String O_URI = "http://10.228.191.203:9085/rs/data/SAP.HR.O?";
    private static final String S_URI = "http://10.228.191.203:9085/rs/data/SAP.HR.S?";
    private static final String PU_URI = "http://10.228.191.203:9085/rs/data/SAP.HR.PU?";
    private static final String PS_URI = "http://10.228.191.203:9085/rs/data/SAP.HR.PS?";

    private static final String P_TABLE = "P";
    private static final String O_TABLE = "O";
    private static final String S_TABLE = "S";
    private static final String PU_TABLE = "PU";
    private static final String PS_TABLE = "PS";

    private static final String MAX_VERSION_SQL = "SELECT MAX(s.version) FROM Synclog s WHERE s.syncType=:tableName AND UPPER(s.syncInd)= 'Y'";

    @PersistenceContext
    public EntityManager em;

    @Inject
    public SyncJsonService syncService;

    @Schedule(second = "", minute = "", hour = "")
    public void SyncTask() {

        //设置请求资源地址和表名
        Map<String, String> uriMap = new HashMap<String, String>();
        uriMap.put(P_TABLE, P_URI);
        uriMap.put(O_TABLE, O_URI);
        uriMap.put(S_TABLE, S_URI);
        uriMap.put(PU_TABLE, PU_URI);
        uriMap.put(PS_TABLE, PS_URI);

        //设置请求参数，可选。可以直接在请求地址后缀加上，Map<表名，Map<key,value>>
        Map<String, Map<String, String>> paramMap = new HashMap<String, Map<String, String>>();
        Set<String> keySet = new HashSet<String>();
        for (String key : keySet) {

            Query query = em.createQuery(MAX_VERSION_SQL);
            query.setParameter("tableName",key);
            Object version = query.getSingleResult();
            Map<String, String> params = new HashMap<String, String>();
            if (null != version) {
                params.put(key, version.toString().trim());
            }
            paramMap.put(key,params);
        }

        syncService.setUriMap(uriMap);
        syncService.setParamMap(paramMap);
        syncService.process();


    }

}
