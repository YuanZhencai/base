/** * SmsSender.java 
* Created on 2014年5月8日 下午1:07:14 
*/

package com.wcs.demo.goc.factory;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: MailSender.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class SmsSender implements Sender {

	@Override
	public void send() {
		System.out.println("SmsSender.Send()");
	}

}
