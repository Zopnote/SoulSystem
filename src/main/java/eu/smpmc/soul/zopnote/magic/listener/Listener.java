package eu.smpmc.soul.zopnote.magic.listener;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.item.ItemType;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import eu.smpmc.soul.Soul;
import eu.smpmc.soul.zopnote.api.components.ComplexItem;
import eu.smpmc.soul.zopnote.api.item.Item;
import eu.smpmc.soul.zopnote.api.item.complex.Complex;
import eu.smpmc.soul.zopnote.file.MagicData;
import eu.smpmc.soul.zopnote.magic.Spells;
import eu.smpmc.soul.zopnote.magic.spells.MagicType;
import eu.smpmc.soul.zopnote.message.Notification;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


public class Listener implements org.bukkit.event.Listener {
  private final static HashMap<UUID, Float> FIRE_DEFENSE = new HashMap<>();
  private final static HashMap<UUID, Float> FIRE_ATTACK = new HashMap<>();
  private final static HashMap<UUID, Float> FIRE_SUPER = new HashMap<>();
  private final static HashMap<UUID, Float> WEATHER_DEFENSE = new HashMap<>();
  private final static HashMap<UUID, Float> WEATHER_ATTACK = new HashMap<>();
  private final static HashMap<UUID, Float> WEATHER_SUPER = new HashMap<>();
  private final static HashMap<UUID, Float> GRAVITATION_DEFENSE = new HashMap<>();
  private final static HashMap<UUID, Float> GRAVITATION_ATTACK = new HashMap<>();
  private final static HashMap<UUID, Float> GRAVITATION_SUPER = new HashMap<>();



  /*
  *
  * Old code. Please don't judge me. :)
  *
  */
  @EventHandler
  public void onEvent(PlayerInteractEvent event) {
    boolean isStaff = false;
    if (event.hasItem()) {
      Material itemType = event.getItem().getType();
      if (
        itemType == Material.WOODEN_HOE ||
          itemType == Material.GOLDEN_SHOVEL ||
          itemType == Material.IRON_SHOVEL
      ) {
        if (event.getItem().hasItemMeta()) {
          if (event.getItem().getItemMeta().hasCustomModelData()) {
            if (event.getItem().getItemMeta().getCustomModelData() == 1) {
              isStaff = true;
            }
          }
        }
      }
    }
    if (!isStaff) {
      return;
    }
    if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_SHOVEL) { //FIRE
      event.setCancelled(true);
      RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
      RegionQuery query = container.createQuery();
      ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(event.getPlayer().getLocation()));
      if (set.testState(null, (StateFlag) WorldGuard.getInstance().getFlagRegistry().get("tz-magic"))) {
        if (set.testState(null, (StateFlag) WorldGuard.getInstance().getFlagRegistry().get("tz-magic-pvp"))) {
          if (event.getAction().isRightClick()) {
            if (event.getPlayer().isSneaking()) {
              if (!FIRE_SUPER.containsKey(event.getPlayer().getUniqueId())) {
                FIRE_SUPER.put(event.getPlayer().getUniqueId(), 1f);
                Spells.FIRE_SUPER(event.getPlayer(), true);
                new BukkitRunnable() {
                  public void run() {
                    FIRE_SUPER.remove(event.getPlayer().getUniqueId());
                  }
                }.runTaskLater(Soul.getInstance(), 20 * 90);
              } else {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
              }
            } else {
              if (!FIRE_DEFENSE.containsKey(event.getPlayer().getUniqueId())) {
                FIRE_DEFENSE.put(event.getPlayer().getUniqueId(), 1f);
                Spells.FIRE_DEFENSE(event.getPlayer(), true);
                new BukkitRunnable() {
                  public void run() {
                    FIRE_DEFENSE.remove(event.getPlayer().getUniqueId());
                  }
                }.runTaskLater(Soul.getInstance(), 20 * 50);
              } else {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
              }
            }
          } else if (event.getAction().isLeftClick()) {
            if (!FIRE_ATTACK.containsKey(event.getPlayer().getUniqueId())) {
              FIRE_ATTACK.put(event.getPlayer().getUniqueId(), 1f);
              Spells.FIRE_ATTACK(event.getPlayer(), true);
              new BukkitRunnable() {
                public void run() {
                  FIRE_ATTACK.remove(event.getPlayer().getUniqueId());
                }
              }.runTaskLater(Soul.getInstance(), 20 * 2);
            } else {
              event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
            }
          }
        }
        else {
          if (event.getAction().isRightClick()) {
            if (event.getPlayer().isSneaking()) {
              if (!FIRE_SUPER.containsKey(event.getPlayer().getUniqueId())) {
                FIRE_SUPER.put(event.getPlayer().getUniqueId(), 1f);
                Spells.FIRE_SUPER(event.getPlayer(), false);
                new BukkitRunnable() {
                  public void run() {
                    FIRE_SUPER.remove(event.getPlayer().getUniqueId());
                  }
                }.runTaskLater(Soul.getInstance(), 20 * 90);
              } else {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
              }
            } else {
              if (!FIRE_DEFENSE.containsKey(event.getPlayer().getUniqueId())) {
                FIRE_DEFENSE.put(event.getPlayer().getUniqueId(), 1f);
                Spells.FIRE_DEFENSE(event.getPlayer(), false);
                new BukkitRunnable() {
                  public void run() {
                    FIRE_DEFENSE.remove(event.getPlayer().getUniqueId());
                  }
                }.runTaskLater(Soul.getInstance(), 20 * 50);
              } else {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
              }
            }
          } else if (event.getAction().isLeftClick()) {
            if (!FIRE_ATTACK.containsKey(event.getPlayer().getUniqueId())) {
              FIRE_ATTACK.put(event.getPlayer().getUniqueId(), 1f);
              Spells.FIRE_ATTACK(event.getPlayer(), false);
              new BukkitRunnable() {
                public void run() {
                  FIRE_ATTACK.remove(event.getPlayer().getUniqueId());
                }
              }.runTaskLater(Soul.getInstance(), 20 * 2);
            } else {
              event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
            }
          }
        }
      }
      else {
        event.getPlayer().sendMessage(new Notification("Es ist nicht möglich hier zu Zaubern.").getComponent());
      }
    }
    else if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.WOODEN_HOE) { //WEATHER
      event.setCancelled(true);
      RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
      RegionQuery query = container.createQuery();
      ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(event.getPlayer().getLocation()));
      if (set.testState(null, (StateFlag) WorldGuard.getInstance().getFlagRegistry().get("tz-magic"))) {
        if (set.testState(null, (StateFlag) WorldGuard.getInstance().getFlagRegistry().get("tz-magic-pvp"))) {
          if (event.getAction().isRightClick()) {
            if (event.getPlayer().isSneaking()) {
              if (!WEATHER_SUPER.containsKey(event.getPlayer().getUniqueId())) {
                WEATHER_SUPER.put(event.getPlayer().getUniqueId(), 1f);
                Spells.WEATHER_SUPER(event.getPlayer());
                new BukkitRunnable() {
                  public void run() {
                    WEATHER_SUPER.remove(event.getPlayer().getUniqueId());
                  }
                }.runTaskLater(Soul.getInstance(), 20 * 60);
              } else {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
              }
            }
            else {
              if (!WEATHER_DEFENSE.containsKey(event.getPlayer().getUniqueId())) {
                WEATHER_DEFENSE.put(event.getPlayer().getUniqueId(), 1f);
                Spells.WEATHER_DEFENSE(event.getPlayer(), true);
                new BukkitRunnable() {
                  public void run() {
                    WEATHER_DEFENSE.remove(event.getPlayer().getUniqueId());
                  }
                }.runTaskLater(Soul.getInstance(), 20 * 60);
              } else {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
              }
            }
          }
          else if (event.getAction().isLeftClick()) {
            if (!WEATHER_ATTACK.containsKey(event.getPlayer().getUniqueId())) {
              WEATHER_ATTACK.put(event.getPlayer().getUniqueId(), 1f);
              Spells.WEATHER_ATTACK(event.getPlayer(), true);
              new BukkitRunnable() {
                public void run() {
                  WEATHER_ATTACK.remove(event.getPlayer().getUniqueId());
                }
              }.runTaskLater(Soul.getInstance(), 20 * 3);
            } else {
              event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
            }
          }
        }
        else {
          if (event.getAction().isRightClick()) {
            if (event.getPlayer().isSneaking()) {
              if (!WEATHER_SUPER.containsKey(event.getPlayer().getUniqueId())) {
                WEATHER_SUPER.put(event.getPlayer().getUniqueId(), 1f);
                Spells.WEATHER_SUPER(event.getPlayer());
                new BukkitRunnable() {
                  public void run() {
                    WEATHER_SUPER.remove(event.getPlayer().getUniqueId());
                  }
                }.runTaskLater(Soul.getInstance(), 20 * 60);
              } else {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
              }
            }
            else {
              if (!WEATHER_DEFENSE.containsKey(event.getPlayer().getUniqueId())) {
                WEATHER_DEFENSE.put(event.getPlayer().getUniqueId(), 1f);
                Spells.WEATHER_DEFENSE(event.getPlayer(), false);
                new BukkitRunnable() {
                  public void run() {
                    WEATHER_DEFENSE.remove(event.getPlayer().getUniqueId());
                  }
                }.runTaskLater(Soul.getInstance(), 20 * 60);
              } else {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
              }
            }
          }
          else if (event.getAction().isLeftClick()) {
            if (!WEATHER_ATTACK.containsKey(event.getPlayer().getUniqueId())) {
              WEATHER_ATTACK.put(event.getPlayer().getUniqueId(), 1f);
              Spells.WEATHER_ATTACK(event.getPlayer(), false);
              new BukkitRunnable() {
                public void run() {
                  WEATHER_ATTACK.remove(event.getPlayer().getUniqueId());
                }
              }.runTaskLater(Soul.getInstance(), 20 * 3);
            } else {
              event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
            }
          }
        }
      }
      else {
        event.getPlayer().sendMessage(new Notification("Es ist nicht möglich hier zu Zaubern.").getComponent());
      }
    }
    else if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.GOLDEN_SHOVEL) { //GRAVITATION
      event.setCancelled(true);
      RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
      RegionQuery query = container.createQuery();
      ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(event.getPlayer().getLocation()));
      if (set.testState(null, (StateFlag) WorldGuard.getInstance().getFlagRegistry().get("tz-magic"))) {
        if (set.testState(null, (StateFlag) WorldGuard.getInstance().getFlagRegistry().get("tz-magic-pvp"))) {
          if (event.getAction().isLeftClick()) {
            if (!GRAVITATION_ATTACK.containsKey(event.getPlayer().getUniqueId())) {
              GRAVITATION_ATTACK.put(event.getPlayer().getUniqueId(), 1f);
              Spells.GRAVITATION_ATTACK(event.getPlayer(), true);
              new BukkitRunnable() {
                public void run() {
                  GRAVITATION_ATTACK.remove(event.getPlayer().getUniqueId());
                }
              }.runTaskLater(Soul.getInstance(), 20 * 2);
            } else {
              event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
            }
          }
          else if (event.getAction().isRightClick()) {
            if (event.getPlayer().isSneaking()) {
              if (!GRAVITATION_SUPER.containsKey(event.getPlayer().getUniqueId())) {
                GRAVITATION_SUPER.put(event.getPlayer().getUniqueId(), 1f);
                Spells.GRAVITATION_SUPER(event.getPlayer(), true);
                new BukkitRunnable() {
                  public void run() {
                    GRAVITATION_SUPER.remove(event.getPlayer().getUniqueId());
                  }
                }.runTaskLater(Soul.getInstance(), 20 * 95);
              } else {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
              }
            }
            else {
              if (!GRAVITATION_DEFENSE.containsKey(event.getPlayer().getUniqueId())) {
                GRAVITATION_DEFENSE.put(event.getPlayer().getUniqueId(), 1f);
                Spells.GRAVITATION_DEFENSE(event.getPlayer(), true);
                new BukkitRunnable() {
                  public void run() {
                    GRAVITATION_DEFENSE.remove(event.getPlayer().getUniqueId());
                  }
                }.runTaskLater(Soul.getInstance(), 20 * 45);
              } else {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
              }
            }
          }
        }
        else {
          if (event.getAction().isLeftClick()) {
            if (!GRAVITATION_ATTACK.containsKey(event.getPlayer().getUniqueId())) {
              GRAVITATION_ATTACK.put(event.getPlayer().getUniqueId(), 1f);
              Spells.GRAVITATION_ATTACK(event.getPlayer(), false);
              new BukkitRunnable() {
                public void run() {
                  GRAVITATION_ATTACK.remove(event.getPlayer().getUniqueId());
                }
              }.runTaskLater(Soul.getInstance(), 20 * 2);
            } else {
              event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
            }
          }
          else if (event.getAction().isRightClick()) {
            if (event.getPlayer().isSneaking()) {
              if (!GRAVITATION_SUPER.containsKey(event.getPlayer().getUniqueId())) {
                GRAVITATION_SUPER.put(event.getPlayer().getUniqueId(), 1f);
                Spells.GRAVITATION_SUPER(event.getPlayer(), false);
                new BukkitRunnable() {
                  public void run() {
                    GRAVITATION_SUPER.remove(event.getPlayer().getUniqueId());
                  }
                }.runTaskLater(Soul.getInstance(), 20 * 95);
              } else {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
              }
            }
            else {
              if (!GRAVITATION_DEFENSE.containsKey(event.getPlayer().getUniqueId())) {
                GRAVITATION_DEFENSE.put(event.getPlayer().getUniqueId(), 1f);
                Spells.GRAVITATION_DEFENSE(event.getPlayer(), false);
                new BukkitRunnable() {
                  public void run() {
                    GRAVITATION_DEFENSE.remove(event.getPlayer().getUniqueId());
                  }
                }.runTaskLater(Soul.getInstance(), 20 * 45);
              } else {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 0.7f, 0.5f);
              }
            }
          }
        }
      }
      else {
        event.getPlayer().sendMessage(new Notification("Es ist nicht möglich hier zu Zaubern.").getComponent());
      }
    }
  }
  @EventHandler
  public void onDrop(PlayerDropItemEvent event) {
    if (event.getItemDrop().getItemStack().getType() == Material.STONE_SHOVEL) {
      event.setCancelled(true);
      event.getPlayer().sendMessage(new Notification("Du kannst deinen Zauberstab nicht fallen lassen.").getComponent());
    }
    else if (event.getItemDrop().getItemStack().getType() == Material.IRON_SHOVEL) {
      event.setCancelled(true);
      event.getPlayer().sendMessage(new Notification("Du kannst deinen Zauberstab nicht fallen lassen.").getComponent());
    }
    else if (event.getItemDrop().getItemStack().getType() == Material.GOLDEN_SHOVEL) {
      event.setCancelled(true);
      event.getPlayer().sendMessage(new Notification("Du kannst deinen Zauberstab nicht fallen lassen.").getComponent());
    }
    else if (event.getItemDrop().getItemStack().getType() == Material.WOODEN_HOE) {
      event.setCancelled(true);
      event.getPlayer().sendMessage(new Notification("Du kannst deinen Zauberstab nicht fallen lassen.").getComponent());
    }
  }



  @EventHandler
  public void onHover(PlayerItemHeldEvent event) {
    Player player = event.getPlayer();

    if (player.getInventory().getItem(event.getPreviousSlot()) != null) {
      ItemStack previousItem = player.getInventory().getItem(event.getPreviousSlot());
      Material itemType = previousItem.getType();
      boolean isStaff = false;
      if (
        itemType == Material.WOODEN_HOE ||
        itemType == Material.GOLDEN_SHOVEL ||
          itemType == Material.IRON_SHOVEL ||
          itemType == Material.STONE_SHOVEL
      ) {
        if (previousItem.hasItemMeta()) {
          if (previousItem.getItemMeta().hasCustomModelData()) {
            if (previousItem.getItemMeta().getCustomModelData() == 1) {
              isStaff = true;
            }
          }
        }
      }
      if (isStaff) {
        player.getInventory().setItem(event.getPreviousSlot(), Item.get(Complex.MAGIC_STAFF_NONE.name()).getItem());
      }
    }
    if (player.getInventory().getItem(event.getNewSlot()) != null) {
      ItemStack item = event.getPlayer().getInventory().getItem(event.getNewSlot());
      Material itemType = item.getType();
      boolean isStaff = false;
      if (
        itemType == Material.WOODEN_HOE ||
          itemType == Material.GOLDEN_SHOVEL ||
          itemType == Material.IRON_SHOVEL ||
          itemType == Material.STONE_SHOVEL
      ) {
        if (item.hasItemMeta()) {
          if (item.getItemMeta().hasCustomModelData()) {
            if (item.getItemMeta().getCustomModelData() == 1) {
              isStaff = true;
            }
          }
        }
      }
      if (isStaff) {
        setActive(event.getNewSlot(), player);
      }
    }
  }


  private void setActive(int slot, Player player) {
    MagicType magicType = new MagicData().get(player.getUniqueId());
    if (magicType == MagicType.NONE) {
      return;
    }
    if (magicType == MagicType.FIRE) {
      player.getInventory().setItem(slot, Item.get(Complex.MAGIC_STAFF_FIRE.name()).getItem());
      return;
    }
    if (magicType == MagicType.GRAVITATION) {
      player.getInventory().setItem(slot, Item.get(Complex.MAGIC_STAFF_GRAVITATION.name()).getItem());
      return;
    }
    if (magicType == MagicType.WEATHER) {
      player.getInventory().setItem(slot, Item.get(Complex.MAGIC_STAFF_WEATHER.name()).getItem());
      return;
    }
  }
}

