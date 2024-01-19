package eu.smpmc.soul.zopnote.magic.spells.gravitation;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import eu.smpmc.soul.Soul;
import eu.smpmc.soul.zopnote.api.region.Region;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;

public class GRAVITATION_SUPER implements Listener {
  private final static Collection<Region> REGIONS = new ArrayList<>();
  private final static Collection<Player> MOVE_STOP = new ArrayList<>();
  private final static Collection<Player> SOUND_STOP = new ArrayList<>();
  private int COUNT;
  private Region REGION;

  public void spawn(Player player, boolean pvp) {
    Collection<Player> players = player.getLocation().getNearbyPlayers(14);
    for (Player player1 : players) {
      player1.playSound(player.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.7f, 0.1f);
    }
    REGION = new Region(player.getLocation(), 8, player, pvp);
    COUNT = 0;
    Region.spawnCircle(REGION, Particle.DRIPPING_OBSIDIAN_TEAR);
    new BukkitRunnable() {
      public void run() {
        if (COUNT > 3) {
          REGIONS.remove(REGION);
          cancel();
        }
        else {
          COUNT++;
          Region.spawnCircle(REGION, Particle.DRIPPING_OBSIDIAN_TEAR);
        }
      }
    }.runTaskTimer(Soul.getInstance(), 20*5, 20*5);
    REGIONS.add(REGION);
  }

  @EventHandler
  public void onJump(PlayerJumpEvent event) {
    if (!REGIONS.isEmpty()) {
      Player player = event.getPlayer();
      for (Region region : REGIONS) {
        if (Region.containsObject(player, region)) {
          Collection<Player> players = player.getLocation().getNearbyPlayers(14);
          for (Player player1 : players) {
            player1.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.7f, 0.2f);
          }
          if (region.getPvP()) {
            if (player == region.getOwner()) {
              player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 15, 4, false, false, false));
              player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20*5, 5, false, false, false));
            }
            else {
              player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 10, 4, false, false, false));
              player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20*3, 2, false, false, false));
            }
          }
          else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 15, 4, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20*5, 5, false, false, false));
          }
        }
      }
    }
  }
  @EventHandler
  public void onSneak(PlayerToggleSneakEvent event) {
    if (!REGIONS.isEmpty()) {
      for (Region region : REGIONS) {
        Player player = event.getPlayer();
        if (Region.containsObject(player, region)) {
          Collection<Player> players = player.getLocation().getNearbyPlayers(14);
          for (Player player1 : players) {
            player1.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.7f, 0.1f);
          }
          MOVE_STOP.add(player);
          new BukkitRunnable() {
            public void run() {
              MOVE_STOP.remove(player);
            }
          }.runTaskLater(Soul.getInstance(), 45);
          if (region.getPvP()) {
            if (player == region.getOwner()) {
              if (!player.isOnGround()) {
                if (!(player.hasPotionEffect(PotionEffectType.LEVITATION))) {
                  Vector unitVector = new Vector(player.getLocation().getDirection().getX() / 2, 0, player.getLocation().getDirection().getZ() / 2);
                  unitVector = unitVector.normalize();
                  player.setVelocity(unitVector.multiply(0.3).setY(-1));
                  player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20*2, 5, false, false, false));
                }
              }
            }
            else {
              if (!player.isOnGround()) {
                if (!(player.hasPotionEffect(PotionEffectType.LEVITATION))) {
                  Vector unitVector = new Vector(player.getLocation().getDirection().getX() / 2, 0, player.getLocation().getDirection().getZ() / 2);
                  unitVector = unitVector.normalize();
                  player.setVelocity(unitVector.multiply(0.3).setY(-1));
                  player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20*2, 5, false, false, false));
                }
              }
            }
          }
          else {
            if (!player.isOnGround()) {
              if (!(player.hasPotionEffect(PotionEffectType.LEVITATION))) {
                Vector unitVector = new Vector(player.getLocation().getDirection().getX() / 2, 0, player.getLocation().getDirection().getZ() / 2);
                unitVector = unitVector.normalize();
                player.setVelocity(unitVector.multiply(0.3).setY(-1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20*2, 5, false, false, false));
              }
            }
          }
        }
      }
    }
  }
  @EventHandler
  public void onMove(PlayerMoveEvent event) {
    for (Region region : REGIONS) {
      Player player = event.getPlayer();
      if (!MOVE_STOP.contains(player)) {
        if (Region.containsObject(player, region)) {
          if (!SOUND_STOP.contains(player)) {
            Collection<Player> players = player.getLocation().getNearbyPlayers(14);
            for (Player player1 : players) {
              player1.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.7f, 1.1f);
            }
            SOUND_STOP.add(player);
            new BukkitRunnable() {
              public void run() {
                SOUND_STOP.remove(player);
              }
            }.runTaskLater(Soul.getInstance(), 60);
          }
          if (region.getPvP()) {
            if (player == region.getOwner()) {
              if (!player.isOnGround()) {
                if (!(player.hasPotionEffect(PotionEffectType.LEVITATION))) {
                  Vector unitVector = new Vector(player.getLocation().getDirection().getX() / 3, 0, player.getLocation().getDirection().getZ() / 3);
                  unitVector = unitVector.normalize();
                  player.setVelocity(unitVector.multiply(0.1).setY(-0.01));
                }
              }
            } else {
              if (!player.isOnGround()) {
                if (!(player.hasPotionEffect(PotionEffectType.LEVITATION))) {
                  Vector unitVector = new Vector(player.getLocation().getDirection().getX() / 3, 0, player.getLocation().getDirection().getZ() / 3);
                  unitVector = unitVector.normalize();
                  player.setVelocity(unitVector.multiply(0.1).setY(-0.01));
                }
              }
            }
          } else {
            if (!player.isOnGround()) {
              if (!(player.hasPotionEffect(PotionEffectType.LEVITATION))) {
                Vector unitVector = new Vector(player.getLocation().getDirection().getX() / 3, 0, player.getLocation().getDirection().getZ() / 3);
                unitVector = unitVector.normalize();
                player.setVelocity(unitVector.multiply(0.1).setY(-0.01));
              }
            }
          }
        }
      }
    }
  }
}
