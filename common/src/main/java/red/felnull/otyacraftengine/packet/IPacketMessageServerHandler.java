package red.felnull.otyacraftengine.packet;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public interface IPacketMessageServerHandler<T> {
    boolean reversiveMessage(T message, ServerPlayer player, ServerGamePacketListenerImpl handler);
}
