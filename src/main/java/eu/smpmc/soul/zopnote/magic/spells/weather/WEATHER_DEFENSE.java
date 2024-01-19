package eu.smpmc.soul.zopnote.magic.spells.weather;

import eu.smpmc.soul.Soul;
import eu.smpmc.soul.zopnote.api.region.Region;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;

public class WEATHER_DEFENSE implements Listener {
  private int COUNT;
  private static Collection<Region> REGIONS = new ArrayList<>();
  private Region REGION;

  public void spawn(Player player, boolean pvp) {
    Location location = player.getLocation();
    Region region = new Region(player.getLocation(), 6, player, pvp);
    REGIONS.add(region);
    Collection<Player> players = player.getLocation().getNearbyPlayers(14);
    for (Player player1 : players) {
      player1.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 0.7f, 0.8f);
      player1.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.2f, 0.3f);
    }
    COUNT = 0;
    new BukkitRunnable() {
      public void run() {
        if (COUNT < 17) {
          COUNT++;
          for (int degree = 0; degree < 360; degree++) {
            degree++;
            double radians = Math.toRadians(degree);
            double x = 6 * Math.cos(radians);
            double z = 6 * Math.sin(radians);
            location.add(x, 0, z);
            location.getWorld().spawnParticle(Particle.DRIP_WATER, location, 1);
            location.subtract(x, 0, z);
          }
        } else {
          REGIONS.remove(region);
          cancel();
        }
      }
    }.runTaskTimer(Soul.getInstance(), 5, 20);
  }
  @EventHandler
  public void onEvent(EntityDamageEvent event) {
    if (event.getEntity() instanceof Player player) {
      if (REGIONS != null) {
        for (Region region : REGIONS) {
          if (region.getPvP()) {
            if (event.getEntity() == region.getOwner()) {
              player.getLocation().getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, player.getLocation(), 4);
              event.setDamage(event.getDamage() / 2);
            }
          }
          else {
            if (Region.containsObject(player, region)) {
              player.getLocation().getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, player.getLocation(), 4);
              event.setDamage(event.getDamage() / 2);
            }
          }
        }
      }
    }

  }
}
