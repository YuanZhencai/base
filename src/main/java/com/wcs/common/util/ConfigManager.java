package com.wcs.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

/**
 * <p>Project: BTC</p>
 * <p>Description: 配置文件读取工具</p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:@wcs-global.com">颜松</a>
 */
public final class ConfigManager {


    /**
     * The CONFI g_ file.
     */
    private static final String CONFIG_FILE = "default.properties";

    private static final String P_STR = "\\{(.+?)\\}";

    /**
     * The config props.
     */
    private static Properties configProps = new Properties();

    /**
     * Instantiates a new propertie utils.
     */
    private ConfigManager() {

    }

    static {
        try {
            configProps.load(ConfigManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getConfigValueMapByFilter(String configKeyFilter) {
        Map<String, String> attrIds = new HashMap<String, String>();
        Iterator itr = configProps.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry e = (Map.Entry) itr.next();
            if (String.valueOf(e.getKey()).indexOf(configKeyFilter) == 0) {
                attrIds.put(String.valueOf(e.getKey()).replace(configKeyFilter, "").trim(), replaceRealValue(String.valueOf(e.getValue()).trim()));
            }
        }
        return attrIds;
    }

    public static String[] getConfigValueArrayByFilter(String configKeyFilter) {
        String[] attrIds = null;
        Map<String, String> attrMap = ConfigManager.getConfigValueMapByFilter(configKeyFilter);
        Iterator itr = attrMap.entrySet().iterator();
        int i = 0;
        if (attrMap.size() > 0) {
            attrIds = new String[attrMap.size()];
            while (itr.hasNext()) {
                Map.Entry e = (Map.Entry) itr.next();
                attrIds[i] = replaceRealValue(String.valueOf(e.getValue()).trim());
                i++;
            }
        }
        return attrIds;
    }

    /**
     * <p>这里利用了递归方式</p>
     * @param key
     * @return
     */
    public static String getValue(String key) {
        return replaceRealValue(configProps.getProperty(key));
    }

    /**
     * <p>同上方法，利用了递归方式获取参数值,这里的{}内的参数必须在配置文件中。</p>
     * @param srcStr
     * @return
     */
    private static String replaceRealValue(String srcStr) {

        String finalStr = new String(srcStr);
        Matcher m = Pattern.compile(P_STR).matcher(srcStr);

        while (m.find()) {
            int i = m.start();
            int j = m.end();

            String replaceStr = srcStr.substring(i + 1, j - 1);

            finalStr = finalStr.replace("{" + replaceStr + "}", getValue(replaceStr));

        }

        return finalStr;

    }

}