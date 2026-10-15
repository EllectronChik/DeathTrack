package dev.ellectronchik.deathtrack.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public final class ModCreativeTabs {
  public static final ResourceKey<CreativeModeTab> TOOLS_AND_UTILITIES =
      ResourceKey.create(
          Registries.CREATIVE_MODE_TAB, Identifier.withDefaultNamespace("tools_and_utilities"));
}
