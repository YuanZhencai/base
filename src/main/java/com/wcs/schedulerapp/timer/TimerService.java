/** * TimerService.java 
* Created on 2014年5月28日 下午3:31:06 
*/

package com.wcs.schedulerapp.timer;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import com.wcs.schedulerapp.common.JobInfo;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: TimerService.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public final class TimerService {
	
	private static final Timer timer = new Timer(false);
	private static Map<String, Task> tasks = new HashMap<String, Task>();
	
	private TimerService() {
	}

	public static void createJob(JobInfo jobInfo) {
		Task task = new Task();
		task.setJobInfo(jobInfo);
		timer.scheduleAtFixedRate(task, 1000, 2000);
		tasks.put(jobInfo.getJobName(), task);
	}

	public static void deleteJob(JobInfo jobInfo) {
		Task task = tasks.get(jobInfo.getJobName());
		task.cancel();
		tasks.remove(task);
	}

	public static void updateJob(JobInfo jobInfo) {
		deleteJob(jobInfo);
		createJob(jobInfo);
	}

	public static JobInfo getJob(JobInfo jobInfo) {
		Task task = tasks.get(jobInfo.getJobName());
		return task == null ? null : task.getJobInfo();
	}

	public static void main(String[] args) {
		JobInfo jobInfo = new JobInfo();
		jobInfo.setJobName("job1");
		TimerService.createJob(jobInfo );
	}
}
