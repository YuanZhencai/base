/** * Computer.java 
 * Created on 2014年5月8日 下午1:42:27 
 */

package com.wcs.demo.goc.facade;

public class Computer {

	private CPU cpu;
	private Memory memory;
	private Disk disk;

	public Computer() {
		cpu = new CPU();
		memory = new Memory();
		disk = new Disk();
		System.out.println("Computer.Computer() " + "外观模式");
	}

	public void startup() {
		System.out.println("start the computer!");
		cpu.startup();
		memory.startup();
		disk.startup();
		System.out.println("start computer finished!");
	}

	public void shutdown() {
		System.out.println("begin to close the computer!");
		cpu.shutdown();
		memory.shutdown();
		disk.shutdown();
		System.out.println("computer closed!");
	}

}
