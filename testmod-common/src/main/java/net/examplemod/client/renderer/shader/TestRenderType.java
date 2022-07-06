package net.examplemod.client.renderer.shader;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import dev.felnull.otyacraftengine.client.renderer.RenderTypeWrapper;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class TestRenderType extends RenderType {
    protected static final ShaderStateShard TEST_SHADER = new ShaderStateShard(TestShaders::getTestShader);

    public static final Function<ResourceLocation, RenderType> TEST = Util.memoize((resourceLocation) -> {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder().setShaderState(TEST_SHADER).setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false)).setTransparencyState(NO_TRANSPARENCY).setLightmapState(LIGHTMAP).setOverlayState(OVERLAY).createCompositeState(true);
        return RenderTypeWrapper.create("test", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, false, compositeState);
    });

    public TestRenderType(String string, VertexFormat vertexFormat, VertexFormat.Mode mode, int i, boolean bl, boolean bl2, Runnable runnable, Runnable runnable2) {
        super(string, vertexFormat, mode, i, bl, bl2, runnable, runnable2);
    }
}