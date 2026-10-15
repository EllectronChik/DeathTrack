package dev.ellectronchik.deathtrack.item;

import dev.ellectronchik.deathtrack.Constants;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeoForgeItems {
  public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Constants.MOD_ID);

  public static final DeferredItem<Item> DEATH_COMPASS =
      ITEMS.registerItem(ModItems.DEATH_COMPASS.name(), ModItems.DEATH_COMPASS::createItem);

  public static void addCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey().equals(ModCreativeTabs.TOOLS_AND_UTILITIES)) {
      event.insertAfter(
          new ItemStack(Items.RECOVERY_COMPASS),
          new ItemStack(DEATH_COMPASS.get()),
          CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
  }
}
