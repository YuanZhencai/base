/** * StaticMethodFactory.java 
 * Created on 2014年5月8日 下午1:09:01 
 */

package com.wcs.demo.goc.factory;

/** 
* <p>Project: btcbase</p> 
* <p>Title: StaticMethodFactory.java</p> 
* <p>Description: </p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
public class StaticMethodFactory {

	private StaticMethodFactory() {
		System.out.println("StaticMethodFactory.StaticMethodFactory() " + "多个静态工厂方法模式");
	}

	public static Sender produceMail() {
		return new MailSender();
	}

	public static Sender produceSms() {
		return new SmsSender();
	}

}
