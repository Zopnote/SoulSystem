package eu.smpmc.soul;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import eu.smpmc.soul.command.Command;
import eu.smpmc.soul.zopnote.api.components.DamageBlocked;
import eu.smpmc.soul.zopnote.api.components.FlightBlocked;
import eu.smpmc.soul.zopnote.api.item.Item;
import eu.smpmc.soul.zopnote.api.item.complex.ingredients.Pure_Fire;
import eu.smpmc.soul.zopnote.api.item.complex.ingredients.Pure_Gravitation;
import eu.smpmc.soul.zopnote.api.item.complex.ingredients.Pure_Weather;
import eu.smpmc.soul.zopnote.api.item.complex.ingredients.Soul_Blood;
import eu.smpmc.soul.zopnote.api.item.complex.magic.Staff_Fire;
import eu.smpmc.soul.zopnote.api.item.complex.magic.Staff_Gravitation;
import eu.smpmc.soul.zopnote.api.item.complex.magic.Staff_None;
import eu.smpmc.soul.zopnote.api.item.complex.magic.Staff_Weather;
import eu.smpmc.soul.zopnote.api.item.complex.weapons.Soul_Sword;
import eu.smpmc.soul.zopnote.file.DataFolder;
import eu.smpmc.soul.zopnote.file.MagicData;
import eu.smpmc.soul.zopnote.magic.listener.Listener;
import eu.smpmc.soul.zopnote.magic.spells.gravitation.GRAVITATION_SUPER;
import eu.smpmc.soul.zopnote.magic.spells.weather.WEATHER_DEFENSE;
import eu.smpmc.soul.zopnote.magic.spells.weather.WEATHER_SUPER;
import eu.smpmc.soul.zopnote.message.Notification;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class Soul extends JavaPlugin {
  public static StateFlag MAGIC;
  public static StateFlag MAGIC_PVP;
  public static StateFlag ABILITY;
  public static StateFlag ABILITY_PVP;
  static Soul instance;
  static TextComponent prefix = Component.text("Soul").color(TextColor.color(148, 56, 216)).append(Component.text(" • ").color(TextColor.color(TextColor.color(123, 123, 123))));

  @Override
  public void onEnable() {
    instance = this;
    this.getDataFolder().mkdirs();
    DataFolder.create();
    MagicData.createCustomConfig();
    PluginManager pluginManager = Bukkit.getPluginManager();
    registerMagic(pluginManager);
    registerItems();
    getCommand("soul").setExecutor(new Command());
    super.onEnable();
  }

  @Override
  public void onLoad() {
    registerWGFlags();
    super.onLoad();
  }

  @Override
  public void onDisable() {
    super.onDisable();
  }

  public static TextComponent getPrefix() {
    return prefix;
  }



  private void registerItems() {
    new Soul_Sword().constructRecipe();
    new Pure_Gravitation().constructRecipe();
    new Pure_Fire().constructRecipe();
    new Pure_Weather().constructRecipe();
    new Soul_Blood();
    new Staff_None().constructRecipe();
    new Staff_Fire();
    new Staff_Gravitation();
    new Staff_Weather();
  }


  private void registerMagic(PluginManager pluginManager) {
    pluginManager.registerEvents(new Listener(), this);
    pluginManager.registerEvents(new GRAVITATION_SUPER(), this);
    pluginManager.registerEvents(new WEATHER_SUPER(), this);
    pluginManager.registerEvents(new WEATHER_DEFENSE(), this);
    pluginManager.registerEvents(new DamageBlocked(), this);
    pluginManager.registerEvents(new FlightBlocked(), this);
    pluginManager.registerEvents(new Soul_Sword(), this);
    pluginManager.registerEvents(new Pure_Fire(), this);
    pluginManager.registerEvents(new Pure_Gravitation(), this);
    pluginManager.registerEvents(new Pure_Weather(), this);
  }


  private void registerWGFlags() {
    FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
    try {
      StateFlag flag = new StateFlag("tz-magic", false);
      registry.register(flag);
      MAGIC = flag;
    } catch (FlagConflictException e) {
      Flag<?> existing = registry.get("tz-magic");
      if (existing instanceof StateFlag) {
        MAGIC = (StateFlag) existing;
      } else {
        Bukkit.getServer().getLogger().warning("MAGIC-flag are overitten by unkown plugin.");
      }
    }
    try {
      StateFlag flag = new StateFlag("tz-magic-pvp", false);
      registry.register(flag);
      MAGIC_PVP = flag;
    } catch (FlagConflictException e) {
      Flag<?> existing = registry.get("tz-magic-pvp");
      if (existing instanceof StateFlag) {
        MAGIC_PVP = (StateFlag) existing;
      } else {
        Bukkit.getServer().getLogger().warning("MAGIC_PVP-flag are overitten by unkown plugin.");
      }
    }
    try {
      StateFlag flag = new StateFlag("tz-ability", false);
      registry.register(flag);
      ABILITY = flag;
    } catch (FlagConflictException e) {
      Flag<?> existing = registry.get("tz-ability");
      if (existing instanceof StateFlag) {
        ABILITY = (StateFlag) existing;
      } else {
        Bukkit.getServer().getLogger().warning("ABILITY-flag are overitten by unkown plugin.");
      }
    }
    try {
      StateFlag flag = new StateFlag("tz-ability-pvp", false);
      registry.register(flag);
      ABILITY_PVP = flag;
    } catch (FlagConflictException e) {
      Flag<?> existing = registry.get("tz-ability-pvp");
      if (existing instanceof StateFlag) {
        ABILITY_PVP = (StateFlag) existing;
      } else {
        Bukkit.getServer().getLogger().warning("ABILITY_PVP-flag are overitten by unkown plugin.");
      }
    }
  }

  @NonNull
  public static Soul getInstance() {
    if (instance != null) {
      return instance;
    }
    Bukkit.broadcast(new Notification("Please consider that you doesn't use getInstance before the instance could set by the plugin itself.").getComponent());
    return null;
  }

}

