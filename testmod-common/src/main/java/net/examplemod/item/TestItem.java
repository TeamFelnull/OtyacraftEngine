package net.examplemod.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TestItem extends Item {
    public TestItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (level.isClientSide) {
            /*ClientPlayerInfoManager.getInstance().getLackProfileAsync("MoriMori_0317_jp", n -> {
                player.displayClientMessage(Component.literal(n.getId().toString()), false);
            });*/
        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}