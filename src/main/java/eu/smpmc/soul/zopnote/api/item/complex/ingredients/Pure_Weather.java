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

public class Pure_Weather implements Listener {
  private static ComplexItem PW;
  public Pure_Weather() {
    if (PW == null) {
      List<Component> list = new ArrayList<>();
      list.add(Component.text("Nutzbar um zu Zaubern")
        .color(TextColor.color(0xB8B8B8))
        .decoration(TextDecoration.ITALIC, false));
      list.add(Component.text("Mehr im Discord.")
        .color(TextColor.color(0xB8B8B8))
        .decoration(TextDecoration.ITALIC, false));
      PW =
        new ComplexItem(Complex.PURE_WEATHER.name(), Material.GUNPOWDER,
          Component
            .text("Essenz des Wetters")
            .color(TextColor.color(0x4C7AD2))
            .decoration(TextDecoration.ITALIC, false),
          list)

          .addFlag(ItemFlag.HIDE_ATTRIBUTES)
          .addFlag(ItemFlag.HIDE_ENCHANTS)
          .addFlag(ItemFlag.HIDE_UNBREAKABLE)
          .setCustomModelData(3)
          .setMaterialData(3);
      PW.save();
    }
  }
  public ComplexItem get() {
    return PW;
  }

  @EventHandler
  public void onEvent(PlayerInteractEvent event) {
    boolean isPureWeather = false;
    if (event.getAction().isRightClick()) {
      if (event.hasItem()) {
        if (event.getItem().getType() == Material.GUNPOWDER) {
          if (event.getItem().hasItemMeta()) {
            if (event.getItem().getItemMeta().hasCustomModelData()) {
              if (event.getItem().getItemMeta().getCustomModelData() == 3) {
                isPureWeather = true;
              }
            }
          }
        }
      }
    }
    if (!isPureWeather) {
      return;
    }
    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.5f, 0.7f);
    new MagicData().set(event.getPlayer().getUniqueId(), MagicType.WEATHER);
    event.getPlayer().sendMessage(new Notification("Deine ausgewählte Magie wurde auf Wetter gesetzt.").getComponent());
    if (event.getItem().getAmount() > 1) {
      event.getItem().setAmount(event.getItem().getAmount()-1);
    }
    else {
      event.getPlayer().getInventory().clear(event.getPlayer().getInventory().getHeldItemSlot());
    }
  }

  public void constructRecipe() {
    ItemStack itemStack = get().getItem();
    NamespacedKey key = new NamespacedKey(Soul.getInstance(), "4");
    ShapedRecipe recipe = new ShapedRecipe(key, itemStack);
    recipe.shape("GHG", "GBG", "PPP");
    recipe.setIngredient('H', Material.HEART_OF_THE_SEA);
    recipe.setIngredient('B', Material.BOWL);
    recipe.setIngredient('P', Material.PRISMARINE_CRYSTALS);
    recipe.setIngredient('G', Material.GLOW_INK_SAC);
    Bukkit.addRecipe(recipe);
  }
}
