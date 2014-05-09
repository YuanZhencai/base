package com.wcs.demo.goc.bridge;

public class Light implements IElectricalEquipment {

	@Override
	public void powerOn() {
		System.out.println("电灯打开");
	}

	@Override
	public void powerOff() {
		System.out.println("电灯关闭");
	}

}
