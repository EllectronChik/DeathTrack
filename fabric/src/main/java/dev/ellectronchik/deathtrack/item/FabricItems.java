package dev.ellectronchik.deathtrack.item;

import dev.ellectronchik.deathtrack.model.ItemEntry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public final class FabricItems {
  public static void register() {
    addItem(ModItems.DEATH_COMPASS);
  }

  private static <T extends Item> void addItem(ItemEntry<T> entry) {
    T item = entry.createItem(p -> p.setId(entry.key()));

    Registry.register(BuiltInRegistries.ITEM, entry.key(), item);
  }
}
