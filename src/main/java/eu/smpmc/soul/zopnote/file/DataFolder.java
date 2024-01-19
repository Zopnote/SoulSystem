package eu.smpmc.soul.zopnote.file;

import eu.smpmc.soul.Soul;

import java.io.File;

public class DataFolder {
  public static void create() {

    if (!exists()) {
      new File(Soul.getInstance().getDataFolder(), "/data/").mkdirs();
    }
  }

  public static boolean exists() {
    return new File(Soul.getInstance().getDataFolder(), "/data/").exists();
  }

  public static File get() {
    return new File(Soul.getInstance().getDataFolder(), "/data/");
  }

}
