package eu.smpmc.soul.zopnote.api.item;

import eu.smpmc.soul.Soul;
import eu.smpmc.soul.zopnote.api.components.ComplexItem;
import eu.smpmc.soul.zopnote.api.item.complex.Complex;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Item {
  public static ComplexItem get(String identification) {
    if (ComplexItem.SAVED.containsKey(identification)) {
      try {
        return (ComplexItem) ComplexItem.SAVED.get(identification).clone();
      }
      catch (Exception e) {
        Bukkit.getServer().getLogger().info(Soul.getPrefix()+"ERROR: "+e);
        return null;
      }
    }
    else {
      return null;
    }
  }

  public static boolean give(Player player, String identification, int amount) {
    if (ComplexItem.SAVED.containsKey(identification)) {
      ComplexItem.SAVED.get(identification).setAmount(amount).giveItem(player);
      return true;
    }
    return false;
  }

  public static void drop(Location location, Complex item, int amount) {
    if (ComplexItem.SAVED.containsKey(item)) {
      ComplexItem.SAVED.get(item).setAmount(amount).drop(location);
    }
  }
}
