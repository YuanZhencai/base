package com.wcs.demo.goc.singleton;

import org.junit.BeforeClass;
import org.junit.Test;

public class SingletonTest {

	@BeforeClass
	public static void init() {
		System.out.println("单例模式");
	}
	
	@Test
	public void testMode() {
		Singleton.getInstance().mode();
		Singleton1.getInstance().mode();
		Singleton2.getInstance().mode();
		Singleton3.getInstance().mode();
		Singleton4.getInstance().mode();
	}

}
