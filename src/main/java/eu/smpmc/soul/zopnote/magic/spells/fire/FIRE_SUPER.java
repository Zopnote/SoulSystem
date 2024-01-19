package eu.smpmc.soul.zopnote.magic.spells.fire;

import eu.smpmc.soul.Soul;
import eu.smpmc.soul.zopnote.api.components.FlightBlocked;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.HashMap;

public class FIRE_SUPER {
  private float COUNT;
  private float COUNT0;
  private HashMap<Float, Location> PARTICLE_LOCATION;

  public void spawn(Player player, boolean pvp) {
    PARTICLE_LOCATION = new HashMap<>();
    Collection<LivingEntity> entityCollection = player.getLocation().getNearbyLivingEntities(15);
    Collection<Player> playerCollection = player.getLocation().getNearbyPlayers(15);
    for (Player player1 : playerCollection) {
      player1.playSound(player.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 0.1f);
      player1.spawnParticle(Particle.LAVA, player.getLocation(), 10);
    }
    COUNT = 0;
    Location location = player.getLocation();
    int particleRadius = 1;
    for (int degree = 0; degree < 360; degree++) {
      degree++;
      double radians = Math.toRadians(degree);
      double x2 = particleRadius * Math.cos(radians);
      double z2 = particleRadius * Math.sin(radians);
      location.add(x2, 0, z2);
      location.getWorld().spawnParticle(Particle.DRIP_LAVA, location, 2);
      location.subtract(x2, 0, z2);
    }
    player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20 * 4, 2, false, false, false));
    new BukkitRunnable() {
      public void run() {
        COUNT++;
        PARTICLE_LOCATION.put(COUNT, player.getLocation());
        if (COUNT > 7) {
          player.removePotionEffect(PotionEffectType.LEVITATION);
          player.setAllowFlight(true);
          player.setFlying(true);
          FlightBlocked.set(player, true);
          COUNT0 = 0;
          new BukkitRunnable() {
            public void run() {
              if (COUNT0 < 6) {
                COUNT0++;
                Location location1 = PARTICLE_LOCATION.get(COUNT0);
                for (Player player1 : playerCollection) {
                  player1.spawnParticle(Particle.DRIP_LAVA, location1, 4);
                  PARTICLE_LOCATION.remove(COUNT0);
                }
              }
              else {
                for (Player player1 : playerCollection) {
                  player1.spawnParticle(Particle.FLAME, player.getLocation(), 200);
                  player1.spawnParticle(Particle.LAVA, player.getLocation(), 50);
                  player1.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.7f, 0.5f);
                  player1.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0f, 0.4f);
                }
                double currentHealth = player.getHealth();
                double newHealth = currentHealth + 3;
                player.setHealth(Math.min(newHealth, player.getHealthScale()));
                for (LivingEntity entity : entityCollection) {
                  if (pvp) {
                    if (!(entity instanceof Illusioner)) {
                      if (!(entity == player)) {
                        entity.setFireTicks(20 * 4);
                        entity.damage(8);
                      }
                    }
                    else {
                      player.spawnParticle(Particle.LAVA, entity.getLocation(), 5);
                      player.playSound(entity.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM, 0.5f, 0.4f);
                    }
                  }
                  else {
                    if (!(entity instanceof Player)) {
                      if (!(entity instanceof Illusioner)) {
                        entity.setFireTicks(20 * 4);
                        entity.damage(10);
                      }
                      else {
                        player.spawnParticle(Particle.LAVA, entity.getLocation(), 5);
                        player.playSound(entity.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM, 0.5f, 0.4f);
                      }
                    }
                  }
                }
                new BukkitRunnable() {
                  public void run() {
                    player.removePotionEffect(PotionEffectType.LEVITATION);
                    player.setFlying(false);
                    player.setAllowFlight(false);
                    FlightBlocked.set(player, false);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20 * 5, 1, false, false, false));
                  }
                }.runTaskLater(Soul.getInstance(), 20);
                cancel();
              }
            }
          }.runTaskTimer(Soul.getInstance(), 40, 5);
          cancel();
        }
      }
    }.runTaskTimer(Soul.getInstance(), 0, 5);
  }
}
