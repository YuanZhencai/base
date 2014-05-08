/** * StrategyTest.java 
 * Created on 2014年5月8日 下午4:30:06 
 */

package com.wcs.demo.goc.strategy;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;


public class StrategyTest {

	@BeforeClass
	public static void init() {
		System.out.println("策略模式");
	}
	
	@Test
	public void testCalculatePlus() {
		String exp = "3 + 7";
		ICalculator cal = new Plus();
		int result = cal.calculate(exp);
		assertEquals(10, result);
	}

	@Test
	public void testCalculateMinus() {
		String exp = "3 - 7";
		ICalculator cal = new Minus();
		int result = cal.calculate(exp);
		assertEquals(-4, result);
	}
	
	@Test
	public void testCalculateMultiply() {
		String exp = "3 * 7";
		ICalculator cal = new Multiply();
		int result = cal.calculate(exp);
		assertEquals(21, result);
	}
	
}
