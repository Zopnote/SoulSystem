package eu.smpmc.soul.zopnote.magic;

import eu.smpmc.soul.zopnote.magic.spells.fire.FIRE_ATTACK;
import eu.smpmc.soul.zopnote.magic.spells.fire.FIRE_DEFENSE;
import eu.smpmc.soul.zopnote.magic.spells.fire.FIRE_SUPER;
import eu.smpmc.soul.zopnote.magic.spells.gravitation.GRAVITATION_ATTACK;
import eu.smpmc.soul.zopnote.magic.spells.gravitation.GRAVITATION_DEFENSE;
import eu.smpmc.soul.zopnote.magic.spells.gravitation.GRAVITATION_SUPER;
import eu.smpmc.soul.zopnote.magic.spells.weather.WEATHER_ATTACK;
import eu.smpmc.soul.zopnote.magic.spells.weather.WEATHER_DEFENSE;
import eu.smpmc.soul.zopnote.magic.spells.weather.WEATHER_SUPER;
import org.bukkit.entity.Player;


/*
 *  This code fall under the MIT License.
 *  Created for the SMPmc.eu network as a helper.
 *  ~https://github.com/zopnote
 */

public interface Spells {
  static void FIRE_ATTACK(Player player, boolean pvp) {
    new FIRE_ATTACK().spawn(player, pvp);
  }
  static void FIRE_DEFENSE(Player player, boolean pvp) {
    new FIRE_DEFENSE().spawn(player, pvp);
  }
  static void FIRE_SUPER(Player player, boolean pvp) {
    new FIRE_SUPER().spawn(player, pvp);
  }
  static void WEATHER_ATTACK(Player player, boolean pvp) {
    new WEATHER_ATTACK().spawn(player, pvp);
  }
  static void WEATHER_DEFENSE(Player player, boolean pvp) {
    new WEATHER_DEFENSE().spawn(player, pvp);
  }
  static void WEATHER_SUPER(Player player) {
    new WEATHER_SUPER().spawn(player);
  }
  static void GRAVITATION_ATTACK(Player player, boolean pvp) {
    new GRAVITATION_ATTACK().spawn(player, pvp);
  }
  static void GRAVITATION_DEFENSE(Player player, boolean pvp) {
    new GRAVITATION_DEFENSE(player, pvp);
  }
  static void GRAVITATION_SUPER(Player player, boolean pvp) {
    new GRAVITATION_SUPER().spawn(player, pvp);
  }
}
