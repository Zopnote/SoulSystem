package eu.smpmc.soul.zopnote.magic.spells.weather;

import eu.smpmc.soul.Soul;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.HashMap;

public class WEATHER_SUPER implements Listener {
  private int COUNT;
  public static HashMap<Player, Float> MORE_POWER = new HashMap<>();
  public void spawn(Player player) {

    Location location = player.getLocation();

    Collection<Player> players = player.getLocation().getNearbyPlayers(14);
    for (Player player1 : players) {
      player1.playSound(player.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.7f, 0.1f);
      player1.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.5f, 0.3f);
    }
    for (int x = -4; x < 4; x++) {
      for (int y = -4; y < 4; y++) {
        for (int z = -4; z < 4; z++) {
          for (Player player1 : location.getNearbyPlayers(20)) {
            player1.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, location, 7, 0, 0, 0);
          }
        }
      }
    }
    COUNT = 0;
    new BukkitRunnable() {
      public void run() {
        if (COUNT < 300) {
          COUNT++;
          Location locat1on = player.getLocation();
          locat1on.getWorld().spawnParticle(Particle.DRIP_WATER, locat1on, 1);
        } else {
          cancel();
        }
      }
    }.runTaskTimer(Soul.getInstance(), 5, 1);
    MORE_POWER.put(player, 1f);
    for (Player player1 : location.getNearbyPlayers(20)) {
      player1.setPlayerWeather(WeatherType.DOWNFALL);
      new BukkitRunnable() {
        public void run() {
          player1.resetPlayerWeather();
        }
      }.runTaskLater(Soul.getInstance(), 20 * 15);
    }
    new BukkitRunnable() {
      public void run() {
        MORE_POWER.remove(player);
      }
    }.runTaskLater(Soul.getInstance(), 20 * 15);
  }
  @EventHandler
  public void onEvent(EntityDamageByEntityEvent event) {
    if (event.getDamager() instanceof Player player) {
      if (MORE_POWER.containsKey(player)) {
        event.setDamage(event.getDamage() * 2);
        Collection<Player> players = event.getEntity().getLocation().getNearbyPlayers(19);
        for (Player player1 : players) {
          player1.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE,event.getEntity().getLocation(), 7);
          player1.playSound(event.getEntity().getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.8f, 0.5f);
        }
      }
    }
  }
}
