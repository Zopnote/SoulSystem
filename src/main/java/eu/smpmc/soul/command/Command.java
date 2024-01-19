package eu.smpmc.soul.command;

import eu.smpmc.soul.Soul;
import eu.smpmc.soul.command.components.Restart;
import eu.smpmc.soul.command.components.Version;
import eu.smpmc.soul.zopnote.api.components.ComplexItem;
import eu.smpmc.soul.zopnote.api.item.Item;
import eu.smpmc.soul.zopnote.message.Notification;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender commandSender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] args) {
    if (args.length == 0) {
      if (!(commandSender instanceof Player player)) {
        Bukkit.getLogger().info(Soul.getPrefix() + "Syntax: /soul <boss|item>");
        return true;
      }
      new Version(commandSender);
      return true;
    }
    if (args.length == 1) {
      if (args[0].equals("restart")) {
        new Restart(commandSender);
        return true;
      }
      if (args[0].equals("item")) {
        if (commandSender instanceof Player player) {
          player.sendMessage(new Notification("Gebe bitte ein Item an.").getComponent());
          return true;
        }
        Bukkit.getServer().getLogger().info("Bitte führe dies als Spieler aus.");
        return true;
      }
      commandSender.sendMessage(new Notification("Fehlende oder fehlerhafte Argumente.").getComponent());
    }
    if (args.length == 2) {
      if (args[0].equals("item")) {
        if (commandSender instanceof Player player) {
          if (Item.give(player, args[1], 1)) {
            player.sendMessage(new Notification("Dir wurde erfolgreich das Item "+args[1]+" gegeben.").getComponent());
            return true;
          }
          player.sendMessage(new Notification("Gebe eines folgender Items an: "+ ComplexItem.SAVED.keySet()).getComponent());
          return true;
        }
        return true;
      }
    }
    if (args.length == 3) {
      if (args[0].equals("item")) {
        if (commandSender instanceof Player player) {
          try {
            Integer.parseInt(args[2]);
          }
          catch (NumberFormatException e) {
            player.sendMessage(new Notification("Bitte gebe eine gültige Zahl an.").getComponent());
            return true;
          }
          if (Item.give(player, args[1], Integer.parseInt(args[2]))) {
            player.sendMessage(new Notification("Dir wurde erfolgreich das Item "+args[1]+" mit der Anzahl"+args[2]+"gegeben.").getComponent());
            return true;
          }
          player.sendMessage(new Notification("Gebe eines folgender Items an: "+ ComplexItem.SAVED.keySet()).getComponent());
          return true;
        }
        return true;
      }
    }
    return true;
  }
}
