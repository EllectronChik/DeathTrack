package dev.ellectronchik.deathtrack.item;

import dev.ellectronchik.deathtrack.model.ItemEntry;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public final class FabricItems {
  public static void register() {
    Item deathCompass = addItem(ModItems.DEATH_COMPASS);

    CreativeModeTabEvents.modifyOutputEvent(ModCreativeTabs.TOOLS_AND_UTILITIES)
        .register(output -> output.insertAfter(Items.RECOVERY_COMPASS, deathCompass));
  }

  private static <T extends Item> T addItem(ItemEntry<T> entry) {
    T item = entry.createItem(p -> p.setId(entry.key()));

    return Registry.register(BuiltInRegistries.ITEM, entry.key(), item);
  }
}
