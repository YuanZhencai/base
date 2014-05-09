package com.wcs.demo.goc.bridge;

public class Fan implements IElectricalEquipment {

	@Override
	public void powerOn() {

		System.out.println("风扇打开");
	}

	@Override
	public void powerOff() {

		System.out.println("风扇关闭");
	}

}
