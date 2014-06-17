/** * ComparatorTest.java 
 * Created on 2014年6月17日 上午11:09:02 
 */

package com.wcs.demo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * <p>
 * Project: btcbase
 * </p>
 * <p>
 * Title: ComparatorTest.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright (c) 2014 Wilmar Consultancy Services
 * </p>
 * <p>
 * All Rights Reserved.
 * </p>
 * 
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class ComparatorTest {

	@Test
	public void test() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(4);
		list.add(2);
		list.add(1);
		list.add(3);
		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer i1, Integer i2) {
				if( i1 > i2) return -1;
				if( i1 == i2) return 0;
				if( i1 < i2) return 1;
				return 0;
			}
		});
		
		for (Integer i : list) {
			System.out.println("[i]" + i);
		}
	}

}
