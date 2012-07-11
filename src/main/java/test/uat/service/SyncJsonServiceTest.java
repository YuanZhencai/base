package test.uat.service;

import test.uat.DBHandler;

import com.wcs.common.service.SyncJsonService;
import com.wcs.common.util.NetUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Project: BTC</p>
 * <p>Description: 单元测试</p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:hujianguang@wcs-global.com">胡建光</a>
 */
public class SyncJsonServiceTest extends DBHandler {

    private SyncJsonService service = new SyncJsonService();

    @Test
    @Ignore
    public void testGetVersion() {

    }

    @Test
    @Ignore
    public void testAddQueryParam() {
        Map<String, String> uriMap = null;
        Map<String, Map<String, String>> paramMap = null;

//        service.addQueryParam(uriMap, paramMap);

        //测试uriMap或paramMap为空情况
        uriMap = new HashMap<String, String>();
        paramMap = new HashMap<String, Map<String, String>>();
//        service.addQueryParam(uriMap, paramMap);

        assert uriMap.size() ==0;

        //测试uriMap,paramMap存在值情况
        uriMap.put("O","http://10.228.191.203:9085/rs/data/SAP.HR.O?");

        Map<String,String> params = new HashMap<String, String>();
        params.put("query1","2");
        params.put("query2","23");
        paramMap.put("O",params);
//        service.addQueryParam(uriMap,paramMap);

        assert uriMap.get("O").contains("query1=2");
        assert uriMap.get("O").contains("query2=23");

    }

    @Test
    public void testMarshalData() {

//        String data = NetUtils.getURLData("http://10.228.191.203:9085/rs/data/SAP.HR.O?");
//
//
//        //组装参数为空的情况
//        Map<String,String> dataMap = null;
//
//        List<SyncDefineBean> defineBeanList = service.marshalData(dataMap);
//
//        assert  null == defineBeanList;
//
//        dataMap = new HashMap<String, String>();
//       defineBeanList = service.marshalData(dataMap);
//
//        assert 0 == defineBeanList.size();
//
//        //组装参数不为空情况
//        dataMap.put("O",data);
//        defineBeanList = service.marshalData(dataMap);
//
//        for(SyncDefineBean defineBean:defineBeanList){
//            assert defineBean.getTableName().length() > 0;
//            assert defineBean.getSqlList().size() > 0;
//        }

    }

    @Test
    @Ignore
    public void testUpdateInd() {

    }

    @Test
    @Ignore
    public void testUpdateOldSyncData() {

    }

    @Test
    @Ignore
    public void testUpdateNewSyncData() {

    }

    @Test
    @Ignore
    public void testLog() {

    }

}
