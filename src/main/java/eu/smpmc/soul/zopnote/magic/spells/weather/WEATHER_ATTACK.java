package eu.smpmc.soul.zopnote.magic.spells.weather;

import eu.smpmc.soul.Soul;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Collection;

public class WEATHER_ATTACK {
  private int COUNT;
  private Location POSITION;
  private Vector DIRECTION;
public void spawn(Player player, boolean pvp) {
  Collection<Player> players = player.getLocation().getNearbyPlayers(14);
  for (Player player1 : players) {
    player1.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 0.7f, 0.8f);
  }
  new BukkitRunnable() {
    public void run() {
      COUNT = 0;
      World world = player.getWorld();
      double x = player.getLocation().x();
      double y = player.getLocation().y();
      double z = player.getLocation().z();
      float yaw = player.getLocation().getYaw();
      float pitch = player.getLocation().getPitch();
      POSITION = new Location(world, x, y + 1, z, yaw, pitch);
      DIRECTION = POSITION.getDirection();
      for (Player player1 : players) {
        player1.getLocation().getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, player.getLocation(), 4);
      }
      new BukkitRunnable() {

        public void run() {
          if (COUNT < 13) {
            POSITION.add(DIRECTION.multiply(1.0));
            POSITION.getWorld().spawnParticle(Particle.DRIPPING_DRIPSTONE_WATER, POSITION, 1);
            Collection<LivingEntity> entities = POSITION.getNearbyLivingEntities(1);
            for (LivingEntity entity : entities) {
              if (pvp) {
                if (entity != player) {
                  for (Player nearby : entity.getLocation().getNearbyPlayers(17)) {
                    nearby.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, entity.getLocation(), 3);
                    nearby.playSound(entity.getLocation(), Sound.BLOCK_ANVIL_STEP, 1.0f, 0.1f);
                  }
                  entity.damage(6);
                }
              }
              else {
                if (!(entity instanceof Player)) {
                  if (!(entity instanceof Illusioner)) {
                    entity.damage(6);
                  }
                  else {
                    for (Player nearby : entity.getLocation().getNearbyPlayers(17)) {
                      nearby.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, entity.getLocation(), 3);
                      nearby.playSound(entity.getLocation(), Sound.BLOCK_ANVIL_STEP, 1.0f, 0.1f);
                    }
                  }
                }
              }

            }
            COUNT++;
          }
          else {
            cancel();
          }
        }
      }.runTaskTimer(Soul.getInstance(), 0L, 0L);
    }
  }.runTaskLater(Soul.getInstance(), 4L);
}
  
}
