package com.wcs.demo.goc.bridge.vehicle;

public class Truck extends Vehicle {

	@Override
	public void start() {
		System.out.println("Truck.start()");
		getEngine().open();
	}

	@Override
	public void stop() {
		System.out.println("Truck.stop()");
		getEngine().close();
	}

}
