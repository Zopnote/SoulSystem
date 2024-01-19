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

import static eu.smpmc.soul.zopnote.api.components.ComplexItem.createKey;

public class Staff_Weather extends Staff {
  public Staff_Weather() {
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

        new ComplexItem(Complex.MAGIC_STAFF_WEATHER.name(), Material.WOODEN_HOE,

          Component
          .text("Stab des")
            .color(TextColor.color(0xB8B8B8))
            .decoration(TextDecoration.ITALIC, false)
            .append(Component.text(" Wetters")
              .color(TextColor.color(0x4C7AD2))
              .decoration(TextDecoration.ITALIC, false)),
          list)

          .setCustomModelData(1)
          .generateKey()
          .addFlag(ItemFlag.HIDE_ATTRIBUTES)
          .setAmount(1)
          .addFlag(ItemFlag.HIDE_UNBREAKABLE)
          .addFlag(ItemFlag.HIDE_ENCHANTS)
          .setUnbreakable(true).save();


  }

}
