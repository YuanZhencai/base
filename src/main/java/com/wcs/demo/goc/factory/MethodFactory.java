/** * MethodFactory.java 
 * Created on 2014年5月8日 下午1:09:01 
 */

package com.wcs.demo.goc.factory;


/** 
* <p>Project: btcbase</p> 
* <p>Title: MethodFactory.java</p> 
* <p>Description: </p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
public class MethodFactory {
	
	public MethodFactory() {
		System.out.println("MethodFactory.MethodFactory() " + "多个工厂方法模式");
	}
	
	public Sender produceMail() {
		return new MailSender();  
	}
	
	public Sender produceSms() {
		return new SmsSender(); 
	}
	

}
