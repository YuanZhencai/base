/** * IteratorTest.java 
* Created on 2014年5月8日 下午5:35:37 
*/

package com.wcs.demo.goc.iterator;

import org.junit.BeforeClass;
import org.junit.Test;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: IteratorTest.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class IteratorTest {
	
	@BeforeClass
	public static void init() {
		System.out.println("迭代器模式");
	}

	@Test
	public void testIterator() {
		Aggregate ag = new ConcreteAggregate();
		ag.add("david");
		ag.add("lisa");
		ag.add("shun");
		Iterator it = ag.iterator();
		while (it.hasNext()) {
			String str = (String) it.next();
			System.out.println(str);
		}
	}

}
