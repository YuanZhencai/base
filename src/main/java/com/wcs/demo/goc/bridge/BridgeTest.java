package com.wcs.demo.goc.bridge;

import org.junit.Test;

public class BridgeTest {

	@Test
	public void testBridge() {
		//构造电器设备:风扇,开关
		 IElectricalEquipment fan = new Fan();
		 IElectricalEquipment light = new Light();
		 
		 //构造开关
		 Switch fancySwitch = new FancySwitch();
		 Switch normalSwitch = new NormalSwitch();
		 
		 //把风扇连接到漂亮开关
		 fancySwitch.setEquipment(fan);
		 
		 //开关连接到电器,那么当开关打开或关闭时电器应该打开/关闭
		 fancySwitch.On();
		 fancySwitch.Off();
		 
		 //把电灯连接到漂亮开关
		 fancySwitch.setEquipment(light);
		 fancySwitch.On(); //打开电灯
		 fancySwitch.Off(); //关闭电灯
		 
		//把风扇连接到普通开关
		 normalSwitch.setEquipment(fan);
		 
		 //开关连接到电器,那么当开关打开或关闭时电器应该打开/关闭
		 normalSwitch.On();
		 normalSwitch.Off();
		 
		 //把电灯连接到普通开关
		 normalSwitch.setEquipment(light);
		 normalSwitch.On(); //打开电灯
		 normalSwitch.Off(); //关闭电灯
	}

}
