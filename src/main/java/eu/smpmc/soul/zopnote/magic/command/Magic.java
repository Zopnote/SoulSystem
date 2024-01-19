package eu.smpmc.soul.zopnote.magic.command;

import eu.smpmc.soul.Soul;
import eu.smpmc.soul.zopnote.file.MagicData;
import eu.smpmc.soul.zopnote.magic.Spells;
import eu.smpmc.soul.zopnote.magic.spells.MagicType;
import eu.smpmc.soul.zopnote.message.Notification;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Magic {
  public void syntax(CommandSender sender) {
    if (sender instanceof Player player) {
      player.sendMessage(new Notification("Syntax: /thezepser <magic> <set|cast>").getComponent());
    } else {
      Bukkit.getServer().getLogger().info(Soul.getPrefix() + "Syntax: /thezepser <magic> <set|cast>");
    }
  }
  public void syntax_set(CommandSender sender) {
    if (sender instanceof Player player) {
      player.sendMessage(new Notification("Syntax: /thezepser <magic> <set> <FIRE|GRAVITATION|WEATHER|NONE> <Spieler>").getComponent());
    } else {
      Bukkit.getServer().getLogger().info(Soul.getPrefix() + "Syntax: /thezepser <magic> <set> <FIRE|GRAVITATION|WEATHER|NONE> <Spieler>");
    }
  }
  public void syntax_cast(CommandSender sender) {
    if (sender instanceof Player player) {
      player.sendMessage(new Notification("Syntax: /thezepser <magic> <cast> <spell> <PvP: true|false>").getComponent());
    } else {
      Bukkit.getServer().getLogger().info(Soul.getPrefix() + "Syntax: Dieser Befehl muss als Spieler ausgeführt werden.");
    }
  }
  public void syntax_not_valid_player(CommandSender sender) {
    if (sender instanceof Player player) {
      player.sendMessage(new Notification("Syntax§8: §7Dieser Spieler existiert nicht.").getComponent());
    }
    else {
      Bukkit.getServer().getLogger().info(Soul.getPrefix() +"Syntax: Dieser Spieler existiert nicht.");
    }
  }
  public void syntax_not_valid_spell(CommandSender sender) {
    if (sender instanceof Player player) {
      player.sendMessage(new Notification("Dieser Zauber existiert nicht.").getComponent());
    }
  }
  public void syntax_not_valid_magic(CommandSender sender) {
    if (sender instanceof Player player) {
      player.sendMessage(new Notification("Diese Magie existiert nicht.").getComponent());
    } else {
      Bukkit.getServer().getLogger().info(Soul.getPrefix() + "Diese Magie existiert nicht.");
    }
  }
  public void castSpell(Player player, String spell, String pvp) {
    switch (spell) {
      case "WEATHER_ATTACK":
        if (pvp.equals("true")) {
          Spells.WEATHER_ATTACK(player, true);
        } else if (pvp.equals("false")) {
          Spells.WEATHER_ATTACK(player, false);
        }
        break;
      case "WEATHER_DEFENSE":
        if (pvp.equals("true")) {
          Spells.WEATHER_DEFENSE(player, true);
        } else if (pvp.equals("false")) {
          Spells.WEATHER_DEFENSE(player, false);
        }
        break;
      case "WEATHER_SUPER":
        if (pvp.equals("true")) {
          Spells.WEATHER_SUPER(player);
        } else if (pvp.equals("false")) {
          Spells.WEATHER_SUPER(player);
        }
        break;
      case "FIRE_ATTACK":
        if (pvp.equals("true")) {
          Spells.FIRE_ATTACK(player, true);
        } else if (pvp.equals("false")) {
          Spells.FIRE_ATTACK(player, false);
        }
        break;
      case "FIRE_DEFENSE":
        if (pvp.equals("true")) {
          Spells.FIRE_DEFENSE(player, true);
        } else if (pvp.equals("false")) {
          Spells.FIRE_DEFENSE(player, false);
        }
        break;
      case "FIRE_SUPER":
        if (pvp.equals("true")) {
          Spells.FIRE_SUPER(player, true);
        } else if (pvp.equals("false")) {
          Spells.FIRE_SUPER(player, false);
        }
        break;
      case "GRAVITATION_ATTACK":
        if (pvp.equals("true")) {
          Spells.GRAVITATION_ATTACK(player, true);
        } else if (pvp.equals("false")) {
          Spells.GRAVITATION_ATTACK(player, false);
        }
        break;
      case "GRAVITATION_DEFENSE":
        if (pvp.equals("true")) {
          Spells.GRAVITATION_DEFENSE(player, true);
        } else if (pvp.equals("false")) {
          Spells.GRAVITATION_DEFENSE(player, false);
        }
        break;
      case "GRAVITATION_SUPER":
        if (pvp.equals("true")) {
          Spells.GRAVITATION_SUPER(player, true);
        } else if (pvp.equals("false")) {
          Spells.GRAVITATION_SUPER(player, false);
        }
        break;
    }
  }

  public void set(Player player, MagicType magicType) {
    new MagicData().set(player.getUniqueId(), magicType);
  }
}
