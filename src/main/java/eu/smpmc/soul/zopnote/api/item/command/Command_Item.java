package eu.smpmc.soul.zopnote.api.item.command;

import eu.smpmc.soul.Soul;
import eu.smpmc.soul.zopnote.api.components.ComplexItem;
import eu.smpmc.soul.zopnote.api.item.complex.Complex;
import eu.smpmc.soul.zopnote.message.Notification;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Command_Item {
  public void syntax(CommandSender sender) {
    if (sender instanceof Player player) {
      player.sendMessage(new Notification("Syntax: /thezepser <item> <give|get>").getComponent());
    }
    else {
      Bukkit.getServer().getLogger().info(Soul.getPrefix()+"Syntax§8: /§7thezepser §8<§7item§8> <§7give§8|§7get§8>");
    }
  }
  public void syntax_give(CommandSender sender) {
    if (sender instanceof Player player) {
      player.sendMessage(new Notification("Syntax: /thezepser <item> <give> <item> <amount> (Optional: <Spieler>)").getComponent());
    }
    else {
      Bukkit.getServer().getLogger().info(Soul.getPrefix()+"Syntax§8: /§7thezepser §8<§7item§8> <§7give§8> <§7item§8> <§7amount§8> §8<§7Spieler§8>");
    }
  }
  public void syntax_not_valid(CommandSender sender) {
      List<String> list = new ArrayList<>(ComplexItem.SAVED.keySet());
    if (sender instanceof Player player) {
      player.sendMessage(new Notification("Liste: \"+ list + \" Das angegebende Item existiert nicht.").getComponent());
    }
    else {
      Bukkit.getServer().getLogger().info("§7Liste§8: §7"+ list + " " +
        Soul.getPrefix()+"§7Das angegebende Item existiert nicht." );
    }
  }
  public void syntax_player_not_valid(CommandSender sender) {
    if (sender instanceof Player player) {
      player.sendMessage(new Notification("Dieser Spieler existiert nicht.").getComponent());
    }
    else {
      Bukkit.getServer().getLogger().info(Soul.getPrefix()+"§7Dieser Spieler existiert nicht.");
    }
  }
}
