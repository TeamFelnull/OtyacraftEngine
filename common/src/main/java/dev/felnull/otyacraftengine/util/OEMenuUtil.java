package dev.felnull.otyacraftengine.util;

import dev.architectury.registry.menu.MenuRegistry;
import dev.felnull.otyacraftengine.item.location.IPlayerItemLocation;
import dev.felnull.otyacraftengine.item.location.PlayerItemLocations;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class OEMenuUtil {
    public static void openItemMenu(ServerPlayer player, MenuProvider provider, IPlayerItemLocation location, ItemStack stack, int inventorySize) {
        MenuRegistry.openExtendedMenu(player, provider, n -> {
            n.writeBoolean(true);
            n.writeNbt(location.toTag());
            n.writeResourceLocation(location.getResourceLocation());
            n.writeItem(stack);
            n.writeInt(inventorySize);
        });
    }

    public static void openBlockMenu(ServerPlayer player, MenuProvider provider, BlockPos pos, int inventorySize) {
        MenuRegistry.openExtendedMenu(player, provider, n -> {
            n.writeBoolean(false);
            n.writeBlockPos(pos);
            n.writeInt(inventorySize);
        });
    }

    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(OEBlockMenuFactory<T> factory) {
        return MenuRegistry.ofExtended((id, inventory, buf) -> {
            buf.readBoolean();
            return factory.create(id, inventory, buf.readBlockPos(), new SimpleContainer(buf.readInt()));
        });
    }

    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(OEItemMenuFactory<T> factory) {
        return MenuRegistry.ofExtended((id, inventory, buf) -> {
            buf.readBoolean();
            var tag = buf.readNbt();
            IPlayerItemLocation location = PlayerItemLocations.create(buf.readResourceLocation(), tag);
            return factory.create(id, inventory, buf.readItem(), location, new SimpleContainer(buf.readInt()));
        });
    }

    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(OEBlockMenuFactory<T> factoryBlock, OEItemMenuFactory<T> factoryItem) {
        return MenuRegistry.ofExtended((id, inventory, buf) -> {
            if (buf.readBoolean()) {
                var tag = buf.readNbt();
                IPlayerItemLocation location = PlayerItemLocations.create(buf.readResourceLocation(), tag);
                return factoryItem.create(id, inventory, buf.readItem(), location, new SimpleContainer(buf.readInt()));
            } else {
                return factoryBlock.create(id, inventory, buf.readBlockPos(), new SimpleContainer(buf.readInt()));
            }
        });
    }

    public interface OEBlockMenuFactory<T extends AbstractContainerMenu> {
        T create(int id, Inventory inventory, BlockPos pos, Container container);
    }

    public interface OEItemMenuFactory<T extends AbstractContainerMenu> {
        T create(int id, Inventory inventory, ItemStack stack, IPlayerItemLocation location, Container container);
    }
}
