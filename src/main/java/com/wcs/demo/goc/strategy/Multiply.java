/** * Multiply.java 
 * Created on 2014年5月8日 下午4:27:35 
 */

package com.wcs.demo.goc.strategy;


public class Multiply extends AbstractCalculator implements ICalculator {
	
	public Multiply() {
		System.out.println("Multiply.Multiply()");
	}

	@Override
	public int calculate(String exp) {
		int arrayInt[] = split(exp, "\\*");
		int result = arrayInt[0] * arrayInt[1];
		System.out.println("Multiply.calculate() " + exp + " = " + result);
		return result;
	}

}
