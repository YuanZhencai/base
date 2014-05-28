/** * Task.java 
* Created on 2014年5月28日 下午5:04:22 
*/

package com.wcs.schedulerapp.timer;

import java.util.TimerTask;

import com.wcs.schedulerapp.common.JobInfo;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: Task.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class Task extends TimerTask {
	private JobInfo jobInfo;

	@Override
	public void run() {
		System.out.println("Task.run()");
	}

	public JobInfo getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(JobInfo jobInfo) {
		this.jobInfo = jobInfo;
	}

}
