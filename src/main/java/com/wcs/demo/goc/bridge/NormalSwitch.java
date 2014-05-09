package com.wcs.demo.goc.bridge;

public class NormalSwitch extends Switch {

	@Override
	public void On() {
		System.out.println("NormalSwitch.On()");
		super.On();
	}

	@Override
	public void Off() {
		System.out.println("NormalSwitch.Off()");
		super.Off();
	}

}
