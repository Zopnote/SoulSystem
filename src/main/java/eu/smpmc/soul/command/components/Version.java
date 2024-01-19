package eu.smpmc.soul.command.components;

import eu.smpmc.soul.zopnote.message.Notification;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Version {
  public Version(CommandSender sender) {
    if (sender instanceof Player player) {
      player.sendMessage(new Notification("Plugin von github.com/elia1802(Boss, Utilities...) und github.com/zopnote(Magiesystem, Soulschwert...). Version: 2.0").getComponent());
    }
  }
}
