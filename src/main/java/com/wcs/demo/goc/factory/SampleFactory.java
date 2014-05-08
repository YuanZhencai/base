/** * SampleFactory.java 
 * Created on 2014年5月8日 下午1:09:01 
 */

package com.wcs.demo.goc.factory;


/** 
* <p>Project: btcbase</p> 
* <p>Title: SampleFactory.java</p> 
* <p>Description: </p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
public class SampleFactory {
	
	public SampleFactory() {
		System.out.println("SampleFactory.SampleFactory() " + "普通工厂模式");
	}
	
	public Sender produce(String type) {
		if ("mail".equals(type)) {
			return new MailSender();
		} else if ("sms".equals(type)) {
			return new SmsSender();
		} else {
			System.out.println("请输入正确的类型!");
			return null;
		}
	}

}
