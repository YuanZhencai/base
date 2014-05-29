package com.wcs.demo.audio;

import java.io.File;

public class Mp3PlayerTest {

	public static void main(String[] args) {
		AudioPlayer player = new Mp3Player();
		player.play(new File("C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3"));
	}
}