package com.wcs.demo.goc.bridge.vehicle;

public class Vehicle {
	
	private IEngine engine;

	public void start() {
		System.out.println("Vehicle.start()");
		getEngine().open();
	}
	
	public void stop() {
		System.out.println("Vehicle.stop()");
		getEngine().close();
	}

	public IEngine getEngine() {
		return engine;
	}

	public void setEngine(IEngine engine) {
		this.engine = engine;
	}

}
