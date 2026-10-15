package dev.ellectronchik.deathtrack;

import dev.ellectronchik.deathtrack.item.FabricItems;
import net.fabricmc.api.ModInitializer;

public class Deathtrack implements ModInitializer {

  @Override
  public void onInitialize() {
    FabricItems.register();
    CommonClass.init();
  }
}
