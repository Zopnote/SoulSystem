package eu.smpmc.soul.zopnote.api.item.complex.ingredients;

import eu.smpmc.soul.zopnote.api.components.ComplexItem;
import eu.smpmc.soul.zopnote.api.item.complex.Complex;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.List;

public class Soul_Blood {
    private static ComplexItem SB;
    public Soul_Blood() {
        if (SB == null) {
          List<Component> list = new ArrayList<>();
          list.add(Component.text("Nutzbar um Objekte")
            .color(TextColor.color(0xB8B8B8))
            .decoration(TextDecoration.ITALIC, false));
          list.add(Component.text("zu verbessern. Mehr im Discord.")
            .color(TextColor.color(0xB8B8B8))
            .decoration(TextDecoration.ITALIC, false));
            SB =
                    new ComplexItem(Complex.SOUL_BLOOD.name(), Material.GUNPOWDER,
                      Component
                        .text("Seelenblut")
                        .color(TextColor.color(0xB8B8B8))
                      .decoration(TextDecoration.ITALIC, false),
                      list)

                            .addFlag(ItemFlag.HIDE_ATTRIBUTES)
                            .addFlag(ItemFlag.HIDE_ENCHANTS)
                            .addFlag(ItemFlag.HIDE_UNBREAKABLE)
                            .setCustomModelData(1)
                            .setMaterialData(1);
            SB.save();
        }
    }
  public ComplexItem get() {
    return SB;
  }
}
