package com.wcs.demo.audio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PlayerRun
{
  private static final Log log = LogFactory.getLog(PlayerRun.class);
  private static final String LOCAL_FILE_NAME = "./conf/local.txt";
  private static final String NET_FILE_NAME = "./conf/net.txt";
  private static PlayerRun run = new PlayerRun();

  private List<String> localList = new ArrayList();
  private List<String> netList = new ArrayList();

  private AudioPlayer player = new Mp3Player();

  private static PlayerRun getInstance() {
    return run;
  }

  private PlayerRun() {
    loadFromFile(this.localList, new File("./conf/local.txt"));
    loadFromFile(this.netList, new File("./conf/net.txt"));
  }

  public static void main(String[] args) {
    PlayerRun run = getInstance();
    run.play();
  }

  public void play() {
    for (String local : this.localList) {
      this.player.play(new File(local));
    }
    for (String net : this.netList)
      try {
        this.player.play(new URL(net));
      }
      catch (MalformedURLException localMalformedURLException)
      {
      }
  }

  private void loadFromFile(List<String> list, File file)
  {
    BufferedReader br = null;
    try {
      if (!(file.exists())) {
        file.createNewFile();
      }

      br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
      String line = null;
      while ((line = br.readLine()) != null)
        list.add(line.trim());
    }
    catch (Exception e) {
      log.error("read from mp3 file error:", e);
    } finally {
      if (br != null)
        try {
          br.close();
        }
        catch (IOException localIOException1)
        {
        }
    }
  }
}