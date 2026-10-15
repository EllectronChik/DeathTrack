package dev.ellectronchik.deathtrack;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class Deathtrack {

    public Deathtrack(IEventBus eventBus) {
        CommonClass.init();
    }
}
