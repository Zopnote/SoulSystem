package eu.smpmc.soul.zopnote.magic.spells.fire;

import eu.smpmc.soul.Soul;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class FIRE_DEFENSE {
  private int COUNT;
  public void spawn(Player player, boolean pvp) {
    Collection<Player> players = player.getLocation().getNearbyPlayers(14);
    for (Player player1 : players) {
      player1.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 0.7f, 0.8f);
      player1.playSound(player.getLocation(), Sound.ENTITY_BLAZE_AMBIENT, 0.2f, 0.3f);
    }
    Location location = player.getLocation();
    int particleRadius = 4;
    COUNT = 0;
    new BukkitRunnable() {
      public void run() {
        if (COUNT < 17) {
          COUNT++;
          for (int DEGREE = 0; DEGREE < 360; DEGREE++) {
            DEGREE++;
            double radians = Math.toRadians(DEGREE);
            double x = particleRadius * Math.cos(radians);
            double z = particleRadius * Math.sin(radians);
            location.add(x, 0, z);
            location.getWorld().spawnParticle(Particle.DRIP_LAVA, location, 1);
            location.subtract(x, 0, z);
          }
          for (Player player1 : location.getNearbyPlayers(4)) {
            if (pvp) {
              if (player1 == player) {
                player1.spawnParticle(Particle.LAVA, player1.getLocation(), 3);
                double currentHealth = player1.getHealth();
                double newHealth = currentHealth + 1;
                player1.setHealth(Math.min(newHealth, player1.getHealthScale()));
              }
            }
            else {
              player1.spawnParticle(Particle.LAVA, player1.getLocation(), 3);
              double currentHealth = player1.getHealth();
              double newHealth = currentHealth + 1;
              player1.setHealth(Math.min(newHealth, player1.getHealthScale()));
            }
          }
        } else {
          cancel();
        }
      }
    }.runTaskTimer(Soul.getInstance(), 5, 30);
  }
}
