package dev.ellectronchik.deathtrack.item;

import dev.ellectronchik.deathtrack.Constants;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeoForgeItems {
  public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Constants.MOD_ID);

  public static final DeferredItem<Item> DEATH_COMPASS =
      ITEMS.registerItem(ModItems.DEATH_COMPASS.name(), ModItems.DEATH_COMPASS::createItem);
}
