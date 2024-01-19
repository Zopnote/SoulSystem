package eu.smpmc.soul.zopnote.api.components;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;

public class DamageBlocked implements Listener {
  private static HashMap<Player, Float> DAMAGE_BLOCKED = new HashMap<>();

  public void set(Player player, boolean blocked) {
    if (blocked) {
      if (!(DAMAGE_BLOCKED.containsKey(player))) {
        DAMAGE_BLOCKED.put(player, 1f);
      }
    }
    else {
      DAMAGE_BLOCKED.remove(player, 1f);
    }
  }

  public boolean get(Player player) {
    if (DAMAGE_BLOCKED.containsKey(player)) {
      return true;
    }
    else {
      return false;
    }
  }

  @EventHandler
  public void onEvent(EntityDamageByEntityEvent event) {
    if (event.getEntity() instanceof Player player) {
      if (DAMAGE_BLOCKED.containsKey(player)) {
        event.setCancelled(true);
      }
    }
  }
}
