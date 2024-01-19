package eu.smpmc.soul.zopnote.file;

import eu.smpmc.soul.Soul;
import eu.smpmc.soul.zopnote.magic.spells.MagicType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class MagicData {


  private final static File configFile = new File(Soul.getInstance().getDataFolder(), "/data/magic.yml");
  public static void createCustomConfig() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    if (!configFile.exists()) {
      try {
        configFile.createNewFile();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  public void set(UUID playerUuid, MagicType magic){
    FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    config.set("uuid-magic." + playerUuid.toString(),String.valueOf(magic));
    try {
      config.save(configFile);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public MagicType get(UUID playerUuid) {
    FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    if (config.getString("uuid-magic." + playerUuid.toString()) == null) {
      set(playerUuid, MagicType.NONE);
      return MagicType.NONE;
    }
    else {
      if (
        config.getString("uuid-magic." + playerUuid.toString()).equals("NONE") ||
        config.getString("uuid-magic." + playerUuid.toString()).equals("FIRE") ||
          config.getString("uuid-magic." + playerUuid.toString()).equals("REALITY") ||
        config.getString("uuid-magic." + playerUuid.toString()).equals("WEATHER") ||
          config.getString("uuid-magic." + playerUuid.toString()).equals("GRAVITATION")) {
        return MagicType.valueOf(config.getString("uuid-magic." + playerUuid.toString()));
      }
      else {
        set(playerUuid, MagicType.NONE);
        return MagicType.NONE;
      }
    }
  }
}
