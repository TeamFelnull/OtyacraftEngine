package dev.felnull.otyacraftengine.client.handler;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientRawInputEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import dev.felnull.otyacraftengine.OtyacraftEngine;
import dev.felnull.otyacraftengine.api.event.client.FabricOBJLoaderEvent;
import dev.felnull.otyacraftengine.api.event.client.RenderPlayerEvent;
import dev.felnull.otyacraftengine.client.gui.screen.TestScreen;
import dev.felnull.otyacraftengine.impl.client.OEClientExpectPlatform;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public class TestClientHandler {
    public static final KeyMapping TEST_KEY = new KeyMapping("key.otyacraftengine.test", GLFW.GLFW_KEY_J, "key.categories.otyacraftengine");

    public static void init() {
        ClientRawInputEvent.KEY_PRESSED.register(TestClientHandler::onKeyPressed);
        FabricOBJLoaderEvent.LOAD.register(TestClientHandler::onFabricOBJLoad);
        RenderPlayerEvent.RENDER_HAND.register(TestClientHandler::onRenderHand);
        KeyMappingRegistry.register(TEST_KEY);
    }

    public static EventResult onKeyPressed(Minecraft client, int keyCode, int scanCode, int action, int modifiers) {
        //   System.out.println(OEClientUtil.isKeyInput(OEClientExpectPlatform.getKey(TEST_KEY).getValue()));
        if (keyCode == OEClientExpectPlatform.getKey(TEST_KEY).getValue()) {
            client.setScreen(new TestScreen());
        }
        return EventResult.interruptDefault();
    }

    public static EventResult onFabricOBJLoad(ResourceLocation location) {
        if (location.getNamespace().equals(OtyacraftEngine.MODID))
            return EventResult.interruptTrue();
        return EventResult.pass();
    }

    public static EventResult onRenderHand(PoseStack poseStack, MultiBufferSource multiBufferSource, InteractionHand hand, int packedLight, float partialTicks, float interpolatedPitch, float swingProgress, float equipProgress, ItemStack stack) {
        //   return stack.getItem() instanceof EnderEyeItem ? EventResult.interruptFalse() : EventResult.interruptTrue();
        return EventResult.pass();
    }
}
