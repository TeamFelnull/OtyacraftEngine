package dev.felnull.otyacraftengine.forge.data.provider;

import dev.felnull.otyacraftengine.data.provider.IntrinsicHolderTagsProviderWrapper;
import dev.felnull.otyacraftengine.data.provider.ItemTagProviderWrapper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class WrappedItemTagsProvider extends ItemTagsProvider {
    private final ItemTagProviderWrapper tagProviderWrapper;

    public WrappedItemTagsProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, TagsProvider<Block> arg2, String modId, @Nullable ExistingFileHelper existingFileHelper, ItemTagProviderWrapper tagProviderWrapper) {
        super(arg, completableFuture, arg2, modId, existingFileHelper);
        this.tagProviderWrapper = tagProviderWrapper;
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        this.tagProviderWrapper.generateTag(new ItemTagProviderAccessImpl());
    }

    private class ItemTagProviderAccessImpl implements ItemTagProviderWrapper.ItemTagProviderAccess {
        @Override
        public void copy(TagKey<Block> blockTag, TagKey<Item> itemTag) {
            WrappedItemTagsProvider.this.copy(blockTag, itemTag);
        }

        @Override
        public IntrinsicHolderTagsProviderWrapper.IntrinsicTagAppenderWrapper<Item> tag(TagKey<Item> tagKey) {
            return new WrappedIntrinsicHolderTagsProvider.IntrinsicHolderTagAppenderWrapperImpl<>(WrappedItemTagsProvider.this.tag(tagKey));
        }
    }
}
