package com.wcs.demo.goc.bridge.vehicle;

public class Bus extends Vehicle {

	@Override
	public void start() {
		System.out.println("Bus.start()");
		getEngine().open();
	}

	@Override
	public void stop() {
		System.out.println("Bus.stop()");
		getEngine().close();
	}

}
