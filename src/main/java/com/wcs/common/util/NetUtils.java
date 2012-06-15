package com.wcs.common.util;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>Project: BTC</p>
 * <p>Description: 用于通过http获取数据的工具类</p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:hujianguang@wcs-global.com">胡建光</a>
 */
public class NetUtils {

    //默认连接最长时间
    private static final int CONNECT_TIMEOUT = 60000;

    //默认读取流最长时间
    private static final int READ_TIMEOUT = 30000;


    /**
     * <p>Description: 取得网络返回的数据</p>
     *
     * @param uri 请求地址
     * @return 网络返回的数据
     */
    public static final String getURLData(String uri) {
        return getURLData(uri, null, null);
    }

    /**
     * <p>Description: 取得网络返回的数据</p>
     *
     * @param uri        请求地址
     * @param connectTimeout 连接超时时间
     * @return 网络返回的数据
     */
    public static final String getURLDataLimitByConnectTime(String uri, int connectTimeout) {
        return getURLData(uri, connectTimeout, null);
    }

    /**
     * <p>Description: 取得网络返回的数据</p>
     *
     * @param uri     请求地址
     * @param readTimeout 读取流超时时间
     * @return 网络返回的数据
     */
    public static final String getURLDataLimitByReadTime(String uri, int readTimeout) {
        return getURLData(uri, null, readTimeout);
    }


    /**
     * <p>Description: 取得网络返回的数据</p>
     *
     * @param uri            请求地址
     * @param connectTimeout 连接超时时间
     * @param readTimeout    读取流超时时间
     * @return 网络返回的数据
     */
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
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
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
