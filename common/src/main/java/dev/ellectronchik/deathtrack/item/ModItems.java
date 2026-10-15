package dev.ellectronchik.deathtrack.item;

import dev.ellectronchik.deathtrack.model.ItemEntry;
import net.minecraft.world.item.Item;

public final class ModItems {
  public static final ItemEntry<Item> DEATH_COMPASS =
      new ItemEntry<>("death_compass", Item::new, p -> p.stacksTo(1));
}
