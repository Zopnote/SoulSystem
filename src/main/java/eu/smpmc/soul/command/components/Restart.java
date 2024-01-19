package eu.smpmc.soul.command.components;

import eu.smpmc.soul.Soul;
import eu.smpmc.soul.zopnote.message.Notification;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class Restart {
  private int COUNT;

  public Restart(CommandSender sender) {
    if (sender instanceof Player player) {
      Collection<Player> playerCollection = (Collection<Player>) Bukkit.getOnlinePlayers();
      if (!playerCollection.isEmpty()) {
        COUNT = 11;
        player.sendMessage(new Notification("Der Server wird in 10 Sekunden neu gestartet.").getComponent());
        new BukkitRunnable() {
          public void run() {
            COUNT--;
            if (COUNT < 1) {
              for (Player allPlayer : playerCollection) {
                allPlayer.sendMessage(new Notification("Der Server startet neu.").getComponent());
              }
              Bukkit.shutdown();
            } else {
              for (Player allPlayer : playerCollection) {
                allPlayer.sendMessage(new Notification("Der Server startet in "+COUNT+" Sekunden neu").getComponent());
              }
            }
          }
        }.runTaskTimer(Soul.getInstance(), 0, 20);
      }
    } else {
      Collection<Player> playerCollection = (Collection<Player>) Bukkit.getOnlinePlayers();
      if (!playerCollection.isEmpty()) {
        COUNT = 11;
        Bukkit.getServer().getLogger().info(Soul.getPrefix() + "Der Server wird in 10 Sekunden neu gestartet.");
        new BukkitRunnable() {
          public void run() {
            COUNT--;
            if (COUNT < 1) {
              for (Player allPlayer : playerCollection) {
                allPlayer.sendMessage(new Notification("Der Server startet neu.").getComponent());
              }
              Bukkit.shutdown();
            } else {
              for (Player allPlayer : playerCollection) {
                allPlayer.sendMessage(new Notification("Der Server startet in "+COUNT+" Sekunden neu").getComponent());
              }
            }
          }
        }.runTaskTimer(Soul.getInstance(), 0, 20);
      }
    }
  }
}
