/** * AbstractSubject.java 
 * Created on 2014年5月9日 下午4:47:22 
 */

package com.wcs.demo.goc.bserver;

import java.util.Enumeration;
import java.util.Vector;

/**
 * <p>
 * Project: btcbase
 * </p>
 * <p>
 * Title: AbstractSubject.java
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

public class AbstractSubject implements Subject {

	private Vector<Observer> vector = new Vector<Observer>();

	@Override
	public void add(Observer observer) {
		vector.add(observer);

	}

	@Override
	public void del(Observer observer) {
		vector.remove(observer);
	}

	@Override
	public void notifyObservers() {
		Enumeration<Observer> enumo = vector.elements();
		while (enumo.hasMoreElements()) {
			enumo.nextElement().update();
		}

	}

	@Override
	public void operation() {
		// TODO Auto-generated method stub

	}

}
