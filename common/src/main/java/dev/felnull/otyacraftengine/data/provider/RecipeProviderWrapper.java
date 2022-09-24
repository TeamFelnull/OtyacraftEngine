package dev.felnull.otyacraftengine.data.provider;

import dev.felnull.otyacraftengine.data.CrossDataGeneratorAccess;
import dev.felnull.otyacraftengine.data.DataGeneratorType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public abstract class RecipeProviderWrapper extends DataProviderWrapper<RecipeProvider> {
    private final RecipeProvider recipeProvider;

    public RecipeProviderWrapper(CrossDataGeneratorAccess crossDataGeneratorAccess) {
        super(crossDataGeneratorAccess);
        this.recipeProvider = crossDataGeneratorAccess.createRecipeProvider(this);
    }

    @Override
    public RecipeProvider getProvider() {
        return this.recipeProvider;
    }

    @Override
    public DataGeneratorType getGeneratorType() {
        return DataGeneratorType.SERVER;
    }

    public abstract void generateRecipe(Consumer<FinishedRecipe> exporter, RecipeProviderAccess providerAccess);

    public static interface RecipeProviderAccess {
        InventoryChangeTrigger.TriggerInstance has(MinMaxBounds.Ints ints, ItemLike itemLike);

        InventoryChangeTrigger.TriggerInstance has(ItemLike itemLike);

        InventoryChangeTrigger.TriggerInstance has(TagKey<Item> tagKey);

        String getHasName(ItemLike itemLike);

        String getItemName(ItemLike itemLike);
    }
}
