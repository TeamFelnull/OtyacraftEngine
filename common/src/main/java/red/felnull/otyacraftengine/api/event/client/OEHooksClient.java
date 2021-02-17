package red.felnull.otyacraftengine.api.event.client;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.MouseHandler;
import net.minecraft.world.InteractionHand;
import red.felnull.otyacraftengine.api.event.OEEvent;
import red.felnull.otyacraftengine.api.event.OEEventBus;
import red.felnull.otyacraftengine.client.impl.OEClientExpectPlatform;

public class OEHooksClient {

    public static void fireMouseInput(int button, int action, int mods) {
        OEEventBus.post(new InputEvent.MouseInputEvent(button, action, mods));
    }

    public static void fireKeyInput(int key, int scanCode, int action, int modifiers) {
        OEEventBus.post(new InputEvent.KeyInputEvent(key, scanCode, action, modifiers));
    }

    public static boolean onMouseScroll(MouseHandler mouseHelper, double scrollDelta) {
        OEEvent event = new InputEvent.MouseScrollEvent(scrollDelta, mouseHelper.isLeftPressed(), OEClientExpectPlatform.isMiddlePressed(mouseHelper), mouseHelper.isRightPressed(), mouseHelper.xpos(), mouseHelper.ypos());
        return OEEventBus.post(event);
    }

    public static boolean onRawMouseClicked(int button, int action, int mods) {
        return OEEventBus.post(new InputEvent.RawMouseEvent(button, action, mods));
    }

    public static InputEvent.ClickInputEvent onClickInput(int button, KeyMapping keyBinding, InteractionHand hand) {
        InputEvent.ClickInputEvent event = new InputEvent.ClickInputEvent(button, keyBinding, hand);
        OEEventBus.post(event);
        return event;
    }

}
