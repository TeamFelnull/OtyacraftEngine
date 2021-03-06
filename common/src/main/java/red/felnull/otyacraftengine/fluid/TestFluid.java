package red.felnull.otyacraftengine.fluid;

import dev.architectury.registry.registries.DeferredRegister;;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import red.felnull.otyacraftengine.OtyacraftEngine;

public class TestFluid extends IkisugiFluid {
    public static TestFluid TEST_FLUID;
    public static FlowingFluid TEST_FLOWING_FLUID;
    public static Item TEST_FLUID_BUCKET;
    public static Block TEST_FLUID_BLOCK;

    public TestFluid(FluidProperties properties, FluidData fluidData) {
        super(properties, fluidData);
    }

    public TestFluid(FluidProperties properties, FluidData data, boolean isSource) {
        super(properties, data, isSource);
    }

    @Override
    public FlowingFluid createFlowingFluid() {
        return new TestFluid(getProperties(), getData(), false);
    }

    public static void init() {
        DeferredRegister<Fluid> MOD_FLUID_REGISTER = DeferredRegister.create(OtyacraftEngine.MODID, Registry.FLUID_REGISTRY);
        DeferredRegister<Item> MOD_ITEMS_REGISTER = DeferredRegister.create(OtyacraftEngine.MODID, Registry.ITEM_REGISTRY);
        DeferredRegister<Block> MOD_BLOCKS_REGISTER = DeferredRegister.create(OtyacraftEngine.MODID, Registry.BLOCK_REGISTRY);
        TEST_FLUID = new TestFluid(new FluidProperties().color(19419).tickDelay(-4)
                .stillTexture(new ResourceLocation(OtyacraftEngine.MODID,"block/lava_base_still"))
                .flowingTexture(new ResourceLocation(OtyacraftEngine.MODID,"block/lava_base_flow"))
                .overlayTexture(new ResourceLocation(OtyacraftEngine.MODID,"block/lava_base_overlay"))
                , new FluidData(() -> TEST_FLUID, () -> TEST_FLOWING_FLUID, () -> TEST_FLUID_BUCKET, () -> TEST_FLUID_BLOCK));
        TEST_FLOWING_FLUID = TEST_FLUID.createFlowingFluid();
        TEST_FLUID_BUCKET = TEST_FLUID.createBucketItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC));
        TEST_FLUID_BLOCK = TEST_FLUID.createLiquidBlock(BlockBehaviour.Properties.of(Material.WATER));

        MOD_FLUID_REGISTER.register("test_fluid", () -> TEST_FLUID);
        MOD_FLUID_REGISTER.register("test_flowing_fluid", () -> TEST_FLOWING_FLUID);
        MOD_FLUID_REGISTER.register();
        MOD_ITEMS_REGISTER.register("test_fluid_bucket", () -> TEST_FLUID_BUCKET);
        MOD_ITEMS_REGISTER.register();
        MOD_BLOCKS_REGISTER.register("test_fluid_block", () -> TEST_FLUID_BLOCK);
        MOD_BLOCKS_REGISTER.register();
    }
}
