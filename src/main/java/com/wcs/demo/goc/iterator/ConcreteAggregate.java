/** * ConcreteAggregate.java 
* Created on 2014年5月8日 下午5:32:34 
*/

package com.wcs.demo.goc.iterator;

import java.util.ArrayList;
import java.util.List;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: ConcreteAggregate.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class ConcreteAggregate implements Aggregate {
	
	private List<Object> list = new ArrayList<Object>();

	@Override
	public void add(Object obj) {
		list.add(obj);
	}

	@Override
	public void remove(Object obj) {
		list.remove(obj);
	}

	@Override
	public Iterator iterator() {
		return new ConcreteIterator(list);
	}

}
