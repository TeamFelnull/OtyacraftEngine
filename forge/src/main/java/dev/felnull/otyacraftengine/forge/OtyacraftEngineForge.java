package dev.felnull.otyacraftengine.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.felnull.otyacraftengine.OtyacraftEngine;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(OtyacraftEngine.MODID)
public class OtyacraftEngineForge {
    public OtyacraftEngineForge() {
        EventBuses.registerModEventBus(OtyacraftEngine.MODID, FMLJavaModLoadingContext.get().getModEventBus());
        OtyacraftEngine.init();
    }
}
