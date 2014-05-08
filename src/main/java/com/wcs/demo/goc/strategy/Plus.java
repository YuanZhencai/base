/** * Plus.java 
 * Created on 2014年5月8日 下午4:27:35 
 */

package com.wcs.demo.goc.strategy;


public class Plus extends AbstractCalculator implements ICalculator {
	
	public Plus() {
		System.out.println("Plus.Plus()");
	}

	@Override
	public int calculate(String exp) {
		int arrayInt[] = split(exp, "\\+");
		int result = arrayInt[0] + arrayInt[1];
		System.out.println("Plus.calculate() " + exp + " = " + result);
		return result;
	}

}
