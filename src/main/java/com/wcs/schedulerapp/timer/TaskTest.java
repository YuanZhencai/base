/** * TaskTest.java 
 * Created on 2014年5月28日 上午11:06:28 
 */

package com.wcs.schedulerapp.timer;

import java.util.Timer;

import org.junit.Test;

public class TaskTest {

	@Test
	public void testRun() {
		Timer timer = new Timer();
		Task task1 = new Task();
		Task task2 = new Task();
		timer.schedule(task1, 1000, 2000);
		timer.scheduleAtFixedRate(task2, 2000, 3000);
		while (true) {
			try {
				byte[] info = new byte[1024];
				int len = System.in.read(info);
				String strInfo = new String(info, 0, len, "GBK");// 从控制台读出信息 
				System.out.println("input " + strInfo);
				if (strInfo.charAt(strInfo.length() - 1) == " ".charAt(0)) {
					strInfo = strInfo.substring(0, strInfo.length() - 2);
				}
				if (strInfo.startsWith("cancel-1")) {
					task1.cancel();
					break;
				}
				if (strInfo.startsWith("cancel-2")) {
					task2.cancel();
					break;
				}
				if (strInfo.startsWith("cancel-all")) {
					timer.cancel();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
