package red.felnull.otyacraftengine.client.impl;

import me.shedaniel.architectury.ExpectPlatform;
import net.minecraft.client.MouseHandler;

public class OEClientExpectPlatform {
    @ExpectPlatform
    public static boolean isMiddlePressed(MouseHandler mouseHelper) {
        throw new AssertionError();
    }
}
