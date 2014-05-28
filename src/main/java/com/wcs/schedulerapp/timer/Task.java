/** * Task.java 
* Created on 2014年5月28日 上午11:05:32 
*/

package com.wcs.schedulerapp.timer;

import java.util.TimerTask;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: Task.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class Task extends TimerTask {
	private String name;

	@Override
	public void run() {
		System.out.println(name + " Task.run()");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
