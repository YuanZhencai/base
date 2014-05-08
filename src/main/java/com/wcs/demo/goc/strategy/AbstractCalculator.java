/** * AbstractCalculator.java 
 * Created on 2014年5月8日 下午4:15:22 
 */

package com.wcs.demo.goc.strategy;


/** 
* <p>Project: btcbase</p> 
* <p>Title: AbstractCalculator.java</p> 
* <p>Description: </p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
public abstract class AbstractCalculator {
	
	public int[] split(String exp, String opt) {
		String array[] = exp.split(opt);
		int arrayInt[] = new int[2];
		arrayInt[0] = Integer.parseInt(array[0].trim());
		arrayInt[1] = Integer.parseInt(array[1].trim());
		return arrayInt;
	}

}
