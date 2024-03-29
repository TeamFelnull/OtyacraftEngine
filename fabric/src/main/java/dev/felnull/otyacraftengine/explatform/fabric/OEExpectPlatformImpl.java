package dev.felnull.otyacraftengine.explatform.fabric;

import dev.felnull.otyacraftengine.fabric.mixin.MobBucketItemAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;

import java.util.List;
import java.util.stream.Stream;

public class OEExpectPlatformImpl {
    public static EntityType<?> getMobBucketEntity(MobBucketItem mobBucketItem) {
        return ((MobBucketItemAccessor) mobBucketItem).getType();
    }

    public static Stream<TagKey<EntityType<?>>> getTags(EntityType<?> entityType) {
        return entityType.builtInRegistryHolder().tags();
    }

    public static String getItemCreatorModId(ItemStack stack) {
       return BuiltInRegistries.ITEM.getKey(stack.getItem()).getNamespace();
    }

    public static <T> List<T> getCallPoints(String name, Class<?> annotationClass, Class<T> interfaceClass) {
        return FabricLoader.getInstance().getEntrypoints(name, interfaceClass);
    }

    public static FoodProperties getFoodProperties(ItemStack stack, LivingEntity livingEntity) {
        return stack.getItem().getFoodProperties();
    }
}
