/** * TelevisionTest.java 
 * Created on 2014年5月8日 下午2:41:44 
 */

package com.wcs.demo.goc.command.television;

import org.junit.Test;


/** 
* <p>Project: btcbase</p> 
* <p>Title: TelevisionTest.java</p> 
* <p>Description: </p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
public class TelevisionTest {

	@Test
	public void testOpen() {
		Television tv = new Television();
		OperateCommand cmd = new OpenTelevisionCommand(tv);
		RemoteControl control = new RemoteControl(cmd);
		control.excute();
	}

	@Test
	public void testClose() {
		Television tv = new Television();
		OperateCommand cmd = new CloseTelevisionCommand(tv);
		RemoteControl control = new RemoteControl(cmd);
		control.excute();
	}

	@Test
	public void testChangeChanle() {
		Television tv = new Television();
		OperateCommand cmd = new ChangeChanelCommand(tv, "湖南卫视");
		RemoteControl control = new RemoteControl(cmd);
		control.excute();
	}
}
