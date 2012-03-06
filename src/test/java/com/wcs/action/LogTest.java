/** * LogTest.java 
* Created on 2011-10-9 下午3:36:45 
*/

package com.wcs.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* <p>Project: btcbase</p>  
* <p>Title: LogTest.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2005-2011.All rights reserved.</p> 
* <p>Company: millet.com</p> 
* @author <a href="mailto:yujingu828@163.com">Yu JinGu</a> 
*/

public class LogTest {
	static Logger logger = LoggerFactory.getLogger(LogTest.class);
	 
	public static void main(String[] args) {
		logger.info("slf4j test start");
	}
}
