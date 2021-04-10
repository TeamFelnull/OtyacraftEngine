package red.felnull.otyacraftengine.item.storage;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import red.felnull.otyacraftengine.fluid.storage.FluidTank;
import red.felnull.otyacraftengine.util.IKSGContainerUtil;

import java.util.Optional;

public interface IContainerFluidTankItem extends IFluidTankItem {
    @Override
    default Optional<FluidTank> getFluidTank(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("BlockEntityTag") && stack.getTag().getCompound("BlockEntityTag").contains("Tanks")) {
            int tankCont = stack.getTag().getCompound("BlockEntityTag").getInt("TankCont");
            NonNullList<FluidTank> tanks = NonNullList.withSize(tankCont, FluidTank.createEmpty());
            tanks.set(getPriorityFluidTankNumber(), FluidTank.createEmpty(getCapacity(stack), n -> fluidFilter(stack, n)));
            IKSGContainerUtil.loadAllTanks(stack.getTag().getCompound("BlockEntityTag"), tanks);
            return Optional.of(tanks.get(getPriorityFluidTankNumber()));
        }
        return Optional.of(FluidTank.createEmpty(getCapacity(stack), n -> fluidFilter(stack, n)));
    }

    @Override
    default Optional<ItemStack> setFluidTank(ItemStack stack, FluidTank tank) {
        stack.getOrCreateTag().put("FluidTank", tank.save(new CompoundTag()));
        return Optional.of(stack);
    }

    @Override
    default boolean isFluidTankEmpty(ItemStack stack) {
        return getFluidTank(stack).map(FluidTank::isEmpty).orElse(false);
    }

    int getPriorityFluidTankNumber();
}
