package com.wcs.demo.goc.bridge.vehicle;

public class Engine1500CC implements IEngine {

	@Override
	public void open() {
		System.out.println("Engine1500CC.open()");
	}

	@Override
	public void close() {
		System.out.println("Engine1500CC.close()");
	}

}
