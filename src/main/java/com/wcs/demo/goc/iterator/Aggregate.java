/** * Aggregate.java 
* Created on 2014年5月8日 下午5:25:32 
*/

package com.wcs.demo.goc.iterator;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: Aggregate.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public interface Aggregate {
	
	public void add(Object obj);

	public void remove(Object obj);

	public Iterator iterator();
}
