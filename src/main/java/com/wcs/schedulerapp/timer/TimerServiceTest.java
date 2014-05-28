/** * TimerServiceTest.java 
* Created on 2014年5月28日 下午3:49:21 
*/

package com.wcs.schedulerapp.timer;

import static org.junit.Assert.*;

import org.junit.Test;

import com.wcs.schedulerapp.common.JobInfo;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: TimerServiceTest.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class TimerServiceTest {

	@Test
	public void testCreateJob() {
		JobInfo jobInfo = new JobInfo();
		jobInfo.setJobName("job1");
		TimerService.createJob(jobInfo);
	}

	@Test
	public void testDeleteJob() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateJob() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetJob() {
		fail("Not yet implemented");
	}

}
