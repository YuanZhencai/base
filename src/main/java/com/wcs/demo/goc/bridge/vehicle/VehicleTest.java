package com.wcs.demo.goc.bridge.vehicle;

import org.junit.BeforeClass;
import org.junit.Test;

public class VehicleTest {
	
	@BeforeClass
	public static void init() {
		System.out.println("桥接模式：车辆和引擎");
	}
	

	@Test
	public void testVehicle() {
		// 1500马力的发动机
		IEngine engine1500cc = new Engine1500CC();
		// 2000马力的发动机
		IEngine engine2000CC = new Engine2000CC();
		
		// 公交车
		Vehicle bus = new Bus();
		// 卡车
		Vehicle truck = new Truck();
		
		// 公交车装了1500马力的发动机
		bus.setEngine(engine1500cc);
		// 公交车启动
		bus.start();
		// 公交车停止
		bus.stop();
		
		// 卡车装了2000马力的发动机
		truck.setEngine(engine2000CC);
		// 卡车启动
		truck.start();
		// 卡车停止
		truck.stop();
		
	}

}
