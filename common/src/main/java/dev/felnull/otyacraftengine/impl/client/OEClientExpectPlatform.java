package dev.felnull.otyacraftengine.impl.client;

import com.mojang.blaze3d.platform.NativeImage;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.renderer.texture.DynamicTexture;

public class OEClientExpectPlatform {
    @ExpectPlatform
    public static void setNonClosePixels(DynamicTexture texture, NativeImage image) {
        throw new AssertionError();
    }
}