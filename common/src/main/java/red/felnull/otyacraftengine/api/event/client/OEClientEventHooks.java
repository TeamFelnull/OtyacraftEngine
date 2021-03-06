package red.felnull.otyacraftengine.api.event.client;

import net.minecraft.client.MouseHandler;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import red.felnull.otyacraftengine.api.OEHandlerBus;
import red.felnull.otyacraftengine.api.event.OEEvent;
import red.felnull.otyacraftengine.api.event.TickEvent;
import red.felnull.otyacraftengine.client.impl.OEClientExpectPlatform;

import java.util.List;

public class OEClientEventHooks {

    public static ItemTooltipEvent onItemTooltip(ItemStack itemStack, Player player, List<Component> list, TooltipFlag flags) {
        ItemTooltipEvent event = new ItemTooltipEvent(itemStack, player, list, flags);
        OEHandlerBus.post(event);
        return event;
    }

    public static void fireMouseInput(int button, int action, int mods) {
        OEHandlerBus.post(new InputEvent.MouseInputEvent(button, action, mods));
    }

    public static void fireKeyInput(int key, int scanCode, int action, int modifiers) {
        OEHandlerBus.post(new InputEvent.KeyInputEvent(key, scanCode, action, modifiers));
    }

    public static boolean onMouseScroll(MouseHandler mouseHelper, double scrollDelta) {
        OEEvent event = new InputEvent.MouseScrollEvent(scrollDelta, mouseHelper.isLeftPressed(), OEClientExpectPlatform.isMiddlePressed(mouseHelper), mouseHelper.isRightPressed(), mouseHelper.xpos(), mouseHelper.ypos());
        return OEHandlerBus.post(event);
    }

    public static boolean onRawMouseClicked(int button, int action, int mods) {
        return OEHandlerBus.post(new InputEvent.RawMouseEvent(button, action, mods));
    }

    public static void onBlockColorsInit(BlockColors blockColors) {
        OEHandlerBus.post(new ColorHandlerEvent.Block(blockColors));
    }

    public static void onItemColorsInit(ItemColors itemColors, BlockColors blockColors) {
        OEHandlerBus.post(new ColorHandlerEvent.Item(itemColors, blockColors));
    }

    public static boolean onRenderGuiItemDecorationss(ItemRenderer itemRenderer, Font font, ItemStack itemStack, int xPosition, int yPosition, String string) {
        return OEHandlerBus.post(new RenderGuiItemDecorationsEvent(itemRenderer, font, itemStack, xPosition, yPosition, string));
    }

    public static List<ResourceLocation> regisSprites() {
        SpriteRegiserEvent sre = new SpriteRegiserEvent();
        OEHandlerBus.post(sre);
        return sre.getRegistTextuers();
    }

    public static void onRenderTickStart(float timer) {
        // Animation.setClientPartialTickTime(timer);
        OEHandlerBus.post(new TickEvent.RenderTickEvent(TickEvent.Phase.START, timer));
    }

    public static void onRenderTickEnd(float timer) {
        OEHandlerBus.post(new TickEvent.RenderTickEvent(TickEvent.Phase.END, timer));
    }

    public static void onPreClientTick() {
        OEHandlerBus.post(new TickEvent.ClientTickEvent(TickEvent.Phase.START));
    }

    public static void onPostClientTick() {
        OEHandlerBus.post(new TickEvent.ClientTickEvent(TickEvent.Phase.END));
    }
}
