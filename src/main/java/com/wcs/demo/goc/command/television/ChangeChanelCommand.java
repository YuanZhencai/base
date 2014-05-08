/** * OpenTelevisionCommand.java 
 * Created on 2014年5月8日 下午2:38:22 
 */

package com.wcs.demo.goc.command.television;


/** 
* <p>Project: btcbase</p> 
* <p>Title: ChangeChanelCommand.java</p> 
* <p>Description: 切换频道操作</p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
public class ChangeChanelCommand implements OperateCommand {

	private Television tv;
	private String chanel;

	public ChangeChanelCommand(Television tv, String chanel) {
		System.out.println("ChangeChanelCommand.ChangeChanelCommand()");
		this.tv = tv;
		this.chanel = chanel;
	}

	@Override
	public void sendOperate() {
		tv.changeChanel(chanel);
	}

}
