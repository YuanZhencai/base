/** * RemoteControl.java 
* Created on 2014年5月8日 下午2:23:40 
*/

package com.wcs.demo.goc.command.television;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: RemoteControl.java</p> 
 * <p>Description: 遥控器</p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class RemoteControl {
	
	private OperateCommand cmd;

	public RemoteControl(OperateCommand cmd) {
		System.out.println("RemoteControl.RemoteControl()");
		this.cmd = cmd;
	}
	
	public void excute() {
		System.out.println("RemoteControl.excute() " + cmd.getClass().getSimpleName());
		cmd.sendOperate();
	}
}
