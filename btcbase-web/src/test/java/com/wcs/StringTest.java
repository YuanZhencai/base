package com.wcs;

import org.junit.Test;

import com.sun.enterprise.universal.collections.CollectionUtils;

public class StringTest {

	@Test
	public void splitTest(){
		String s = "FROM User u, Person p, PU pu ";
		String[] fromQls =s.split("[,\\s]+");//多个空格
		for (String ss : fromQls){
			System.out.println( ss);
		}
	}
}
