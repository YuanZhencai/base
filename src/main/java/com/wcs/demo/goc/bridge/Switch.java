package com.wcs.demo.goc.bridge;

public class Switch {
	
	private IElectricalEquipment equipment;
	
	public void On() {
		System.out.println("打开开关");
		equipment.powerOn();
	}

	public void Off() {
		System.out.println("关闭开关");
		equipment.powerOff();
	}

	public IElectricalEquipment getEquipment() {
		return equipment;
	}

	public void setEquipment(IElectricalEquipment equipment) {
		this.equipment = equipment;
	}
}
