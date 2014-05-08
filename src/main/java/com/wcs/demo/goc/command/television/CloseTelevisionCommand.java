/** * CloseTelevisionCommand.java 
* Created on 2014年5月8日 下午2:38:22 
*/

package com.wcs.demo.goc.command.television;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: CloseTelevisionCommand.java</p> 
 * <p>Description: 关闭电视机操作</p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class CloseTelevisionCommand implements OperateCommand {
	
	private Television tv;
	
	public CloseTelevisionCommand(Television tv) {
		System.out.println("CloseTelevisionCommand.CloseTelevisionCommand()");
		this.tv = tv;
	}
	
	@Override
	public void sendOperate() {
		tv.close();
	}

}
