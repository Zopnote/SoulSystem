package eu.smpmc.soul.zopnote.api.item.complex.magic;

import eu.smpmc.soul.zopnote.api.components.ComplexItem;
import eu.smpmc.soul.zopnote.api.item.complex.Complex;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.List;


public class Staff_Fire extends Staff {
  public Staff_Fire() {

      List<Component> list = new ArrayList<>();
      list.add(
        Component
          .text("Ein Stab um die Energie")
          .color(TextColor.color(0xB8B8B8))
          .decoration(TextDecoration.ITALIC, false));
      list.add(
        Component
          .text("eines Elements zu fesseln.")
          .color(TextColor.color(0xB8B8B8))
          .decoration(TextDecoration.ITALIC, false));


      new ComplexItem(Complex.MAGIC_STAFF_FIRE.name(), Material.IRON_SHOVEL,

        Component
          .text("Stab des")
        .color(TextColor.color(0xB8B8B8))
        .decoration(TextDecoration.ITALIC, false)
        .append(
          Component.text(" Feuers")
            .color(TextColor.color(0xDD5828))
            .decoration(TextDecoration.ITALIC, false)
        ), list)

        .setCustomModelData(1)
        .generateKey()
        .addFlag(ItemFlag.HIDE_ATTRIBUTES)
        .addFlag(ItemFlag.HIDE_UNBREAKABLE)
        .addFlag(ItemFlag.HIDE_ENCHANTS)
        .setAmount(1)
        .setUnbreakable(true)
        .save();
  }

}
