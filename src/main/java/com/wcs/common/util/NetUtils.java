package com.wcs.common.util;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: hujianguang
 * Date: 12-6-8
 * Time: 下午2:46
 * To change this template use File | Settings | File Templates.
 */
public class NetUtils {

    //连接最长时间
    private static final int CONNECT_TIMEOUT = 60000;

    //读取流最长时间
    private static final int READ_TIMEOUT = 30000;


    public static final String getURLData(String uriList) {
        return getURLData(uriList, null, null);
    }

    public static final String getURLDataLimitByConnectTime(String uriList, int connectTimeout) {
        return getURLData(uriList, connectTimeout, null);
    }

    public static final String getURLDataLimitByReadTime(String uriList, int readTimeout) {
        return getURLData(uriList, null, readTimeout);
    }


    public static final String getURLData(String uri, Integer connectTimeout, Integer readTimeout) {

        if (StringUtils.isEmpty(uri)) {
            return null;
        }

        HttpURLConnection conn = null;
        try {

            URL url = new URL(uri);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(null != connectTimeout ? connectTimeout : CONNECT_TIMEOUT);
            conn.setReadTimeout(null != readTimeout ? readTimeout : READ_TIMEOUT);
            conn.setRequestMethod("GET");

            //获取输入流
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            StringBuilder buffStr = new StringBuilder();
            String resultStr = null;

            while ((resultStr = buffReader.readLine()) != null) {
                buffStr.append(resultStr.trim());
            }

            return buffStr.toString();

        } catch (MalformedURLException urlEx) {
            urlEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            if (null != conn) {
                conn.disconnect();
            }
        }

        return null;

    }

}
