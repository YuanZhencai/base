/** * OpenTelevisionCommand.java 
* Created on 2014年5月8日 下午2:38:22 
*/

package com.wcs.demo.goc.command.television;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: OpenTelevisionCommand.java</p> 
 * <p>Description: 打开电视机操作</p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class OpenTelevisionCommand implements OperateCommand {
	
	private Television tv;
	
	public OpenTelevisionCommand(Television tv) {
		System.out.println("OpenTelevisionCommand.OpenTelevisionCommand()");
		this.tv = tv;
	}
	
	@Override
	public void sendOperate() {
		tv.open();
	}

}
