package eu.smpmc.soul.zopnote.api.components;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class FlightBlocked implements Listener {
  private static HashMap<Player, Float> FLIGHT_BLOCKED = new HashMap<>();

  public static void set(Player player, boolean blocked) {
    if (blocked) {
      if (!(FLIGHT_BLOCKED.containsKey(player))) {
        FLIGHT_BLOCKED.put(player, 1f);
      }
    } else {
      FLIGHT_BLOCKED.remove(player);
    }
  }

  public static boolean get(Player player) {
    return FLIGHT_BLOCKED.containsKey(player);
  }

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event) {
    Player player = event.getPlayer();
    if (get(player) && player.isFlying()) {
      event.setCancelled(true);
    }
  }
}
