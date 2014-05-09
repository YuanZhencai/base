package com.wcs.demo.goc.bridge;

public class FancySwitch extends Switch {

	@Override
	public void On() {
		System.out.println("FancySwitch.On()");
		super.On();
	}

	@Override
	public void Off() {
		System.out.println("FancySwitch.Off()");
		super.Off();
	}

}
