package com.wcs.common.service;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-6-12
 * Time: 下午4:25
 * To change this template use File | Settings | File Templates.
 */
public class newTestServlet extends HttpServlet {
    @EJB
    private ScheduService scheduService;

    static{

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Map<String, String> uriMap = new HashMap<String, String>();
//
//        uriMap.put("P", "11111111");
//        uriMap.put("O", "2222222");
//
//        syncService.setUriMap(uriMap);
//
//
//        Map<String, Map<String, String>> paramMap = new HashMap<String, Map<String, String>>();
//
//        Map<String,String> params1 = new HashMap<String,String>();
//        params1.put("coll","12s");
//
//        Map<String,String> params2 = new HashMap<String,String>();
//        params2.put("chen","12s");
//
//        paramMap.put("P",params1);
//        paramMap.put("O",params2);
//
//        syncService.setParamMap(paramMap);

        scheduService.SyncTask();;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
