package com.wcs.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Project: BTC</p>
 * <p>Description: 配置文件读取工具</p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:@wcs-global.com">颜松</a>
 */
public final class ConfigManager {

	
	/** The CONFI g_ file. */
	private static String CONFIG_FILE = "default.properties";

	/** The config props. */
	private static Properties configProps = new Properties();
	
	/**
	 * Instantiates a new propertie utils.
	 */
	private ConfigManager(){
		
	}
	
	static{
		try {
			configProps.load(ConfigManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE));
		} catch (Exception e) {
		}
	}
	

	/**
	 * Gets the config value.
	 *
	 * @param configKey the config key
	 * @return the config value
	 */
	public static String getConfigValue(String configKey) {
		String configValue = (String) configProps.getProperty(configKey).trim();
		return configValue;
	}
	
	public static Map<String, String> getConfigValueMapByFilter(String configKeyFilter) {
		Map<String, String> attrIds = new HashMap<String, String>();
		String attrId = "";
		Iterator itr = configProps.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry e = (Map.Entry) itr.next();
			attrId = String.valueOf(e.getValue()).trim();
			if(String.valueOf(e.getKey()).indexOf(configKeyFilter) == 0){
				attrIds.put(String.valueOf(e.getKey()).replace(configKeyFilter, "").trim(), String.valueOf(e.getValue()).trim());
			}
		}
		return attrIds;
	}
	
	public static String[] getConfigValueArrayByFilter(String configKeyFilter) {
		String[] attrIds = null;
		Map<String, String> attrMap = ConfigManager.getConfigValueMapByFilter(configKeyFilter);
		Iterator itr = attrMap.entrySet().iterator();
		int i=0;
		if(attrMap.size() > 0){
			attrIds = new String[attrMap.size()];
			while (itr.hasNext()) {
				Map.Entry e = (Map.Entry) itr.next();
				attrIds[i] = String.valueOf(e.getValue()).trim();
				i++;
			}
		}
		return attrIds;
	}
}