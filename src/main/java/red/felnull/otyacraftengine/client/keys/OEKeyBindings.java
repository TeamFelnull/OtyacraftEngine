package red.felnull.otyacraftengine.client.keys;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;
import red.felnull.otyacraftengine.OtyacraftEngine;

public class OEKeyBindings {
    public static KeyBinding TEST = createKeyBind("test", GLFW.GLFW_KEY_M);

    public static void init() {
        registerKey(TEST);
    }

    private static void registerKey(KeyBinding key) {
        ClientRegistry.registerKeyBinding(key);
    }

    private static KeyBinding createKeyBind(String name, int key) {
        return new KeyBinding("key." + OtyacraftEngine.MODID + "." + name, key, "key.categories." + OtyacraftEngine.MODID);
    }
}