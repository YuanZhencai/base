package com.wcs.demo.audio;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public abstract interface AudioPlayer {
	public abstract void play(InputStream paramInputStream);

	public abstract void play(File paramFile);

	public abstract void play(URL paramURL);
}