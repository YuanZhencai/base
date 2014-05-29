package com.wcs.demo.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Mp3Player implements AudioPlayer {
	private static final Log log = LogFactory.getLog(Mp3Player.class);

	private static final byte[] AUDIO_BUFER = new byte[4096];
	private AudioInputStream audioStream;
	private AudioFormat decodedFormat;
	private AudioInputStream decodedAudioStream;

	public void play(InputStream input) {
		if (input == null) {
			log.warn("input stream is null");
			return;
		}
		try {
			init(input);
			play();
		} catch (Exception e) {
			log.error("play error:", e);
		}
	}

	public void play(File file) {
		if (file == null) {
			log.warn("audio file is null");
			return;
		}
		try {
			play(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			log.error("file to inputStream error:", e);
		}
	}

	public void play(URL url) {
		if (url == null) {
			log.warn("url is null");
			return;
		}
		try {
			play(url.openStream());
		} catch (IOException e) {
			log.error("url open inputStream error:", e);
		}
	}

	protected void init(InputStream input) throws UnsupportedAudioFileException, IOException {
		initAudioStream(input);
		initDecodedFormat();
		initDecodedAudioStream();
	}

	protected void initAudioStream(InputStream input) throws UnsupportedAudioFileException, IOException {
		this.audioStream = AudioSystem.getAudioInputStream(input);
	}

	protected void initDecodedFormat() throws UnsupportedAudioFileException, IOException {
		AudioFormat baseFormat = this.audioStream.getFormat();
		this.decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
				baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
	}

	protected void initDecodedAudioStream() {
		this.decodedAudioStream = AudioSystem.getAudioInputStream(this.decodedFormat, this.audioStream);
	}

	protected SourceDataLine getSourceDataLine() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, this.decodedFormat);
		SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
		line.open(this.decodedFormat);
		return line;
	}

	protected void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		SourceDataLine line = getSourceDataLine();
		line.start();
		int readLenth = 0;
		while (readLenth != -1) {
			readLenth = this.decodedAudioStream.read(AUDIO_BUFER, 0, AUDIO_BUFER.length);
			if (readLenth != -1) {
				line.write(AUDIO_BUFER, 0, readLenth);
			}
		}
		line.drain();
		line.stop();
		line.close();
		this.decodedAudioStream.close();
		this.audioStream.close();
	}
}