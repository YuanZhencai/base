package com.wcs.demo.goc.bridge.vehicle;

public class Engine2000CC implements IEngine {

	@Override
	public void open() {
		System.out.println("Engine2000CC.open()");
	}

	@Override
	public void close() {
		System.out.println("Engine2000CC.close()");
	}

}
