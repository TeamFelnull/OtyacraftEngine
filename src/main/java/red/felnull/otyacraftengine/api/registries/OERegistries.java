package red.felnull.otyacraftengine.api.registries;

import net.minecraft.util.ResourceLocation;
import red.felnull.otyacraftengine.OtyacraftEngine;
import red.felnull.otyacraftengine.data.PlayerWorldData;
import red.felnull.otyacraftengine.data.TestPlayerWorldData;

import java.util.HashMap;
import java.util.Map;

public class OERegistries {
    public static Map<String, Integer> MOD_COLOR = new HashMap<String, Integer>();
    public static Map<ResourceLocation, PlayerWorldData> PLAYER_DATA = new HashMap<ResourceLocation, PlayerWorldData>();

    public static void init() {
        registrierModColor("minecraft", 43520);
        registrierModColor("forge", 170);
        registrierModColor(OtyacraftEngine.MODID, 5635925);

        registrierPlayerData(new ResourceLocation(OtyacraftEngine.MODID, "test"), new TestPlayerWorldData());

    }

    public static void registrierModColor(String modid, int color) {
        OERegistries.MOD_COLOR.put(modid, color);
    }

    public static void registrierPlayerData(ResourceLocation location, PlayerWorldData data) {
        OERegistries.PLAYER_DATA.put(location, data);
    }

}
