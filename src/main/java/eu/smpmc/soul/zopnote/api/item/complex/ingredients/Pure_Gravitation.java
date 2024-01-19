package eu.smpmc.soul.zopnote.api.item.complex.ingredients;

import eu.smpmc.soul.Soul;
import eu.smpmc.soul.zopnote.api.components.ComplexItem;
import eu.smpmc.soul.zopnote.api.item.complex.Complex;
import eu.smpmc.soul.zopnote.file.MagicData;
import eu.smpmc.soul.zopnote.magic.spells.MagicType;
import eu.smpmc.soul.zopnote.message.Notification;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;

public class Pure_Gravitation implements Listener {
  private static ComplexItem PG;
  public Pure_Gravitation() {
    if (PG == null) {
      List<Component> list = new ArrayList<>();
      list.add(Component.text("Nutzbar um zu Zaubern")
        .color(TextColor.color(0xB8B8B8))
        .decoration(TextDecoration.ITALIC, false));
      list.add(Component.text("Mehr im Discord.")
        .color(TextColor.color(0xB8B8B8))
        .decoration(TextDecoration.ITALIC, false));
      PG =
        new ComplexItem(Complex.PURE_GRAVITATION.name(), Material.GUNPOWDER,
          Component
            .text("Essenz der Gravitation")
            .color(TextColor.color(0xB06CCB))
            .decoration(TextDecoration.ITALIC, false),
          list)

          .addFlag(ItemFlag.HIDE_ATTRIBUTES)
          .addFlag(ItemFlag.HIDE_ENCHANTS)
          .addFlag(ItemFlag.HIDE_UNBREAKABLE)
          .setCustomModelData(4)
          .setMaterialData(4);
      PG.save();
    }
  }
  public ComplexItem get() {
    return PG;
  }

  @EventHandler
  public void onEvent(PlayerInteractEvent event) {
    boolean isPureGravitation = false;
    if (event.getAction().isRightClick()) {
      if (event.hasItem()) {
        if (event.getItem().getType() == Material.GUNPOWDER) {
          if (event.getItem().hasItemMeta()) {
            if (event.getItem().getItemMeta().hasCustomModelData()) {
              if (event.getItem().getItemMeta().getCustomModelData() == 4) {
                isPureGravitation = true;
              }
            }
          }
        }
      }
    }
    if (!isPureGravitation) {
      return;
    }
    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.5f, 0.7f);
    new MagicData().set(event.getPlayer().getUniqueId(), MagicType.GRAVITATION);
    event.getPlayer().sendMessage(new Notification("Deine ausgewählte Magie wurde auf Gravitation gesetzt.").getComponent());
    if (event.getItem().getAmount() > 1) {
      event.getItem().setAmount(event.getItem().getAmount()-1);
    }
    else {
      event.getPlayer().getInventory().clear(event.getPlayer().getInventory().getHeldItemSlot());
    }
  }

  public void constructRecipe() {
    ItemStack itemStack = get().getItem();
    NamespacedKey key = new NamespacedKey(Soul.getInstance(), "3");
    ShapedRecipe recipe = new ShapedRecipe(key, itemStack);
    recipe.shape("AAA", "EBE", "GAG");
    recipe.setIngredient('A', Material.AMETHYST_SHARD);
    recipe.setIngredient('B', Material.BOWL);
    recipe.setIngredient('E', Material.ECHO_SHARD);
    recipe.setIngredient('G', Material.GLOW_INK_SAC);
    Bukkit.addRecipe(recipe);
  }
}
