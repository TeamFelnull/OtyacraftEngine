package dev.felnull.otyacraftengine.networking;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;

public interface PacketMessage {
    default FriendlyByteBuf toFBB() {
        return toFBB(new FriendlyByteBuf(Unpooled.buffer()));
    }

    FriendlyByteBuf toFBB(FriendlyByteBuf buf);
}
