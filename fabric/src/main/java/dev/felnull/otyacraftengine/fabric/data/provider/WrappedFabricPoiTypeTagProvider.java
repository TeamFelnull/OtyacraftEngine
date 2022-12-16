package dev.felnull.otyacraftengine.fabric.data.provider;

import dev.felnull.otyacraftengine.data.provider.PoiTypeTagProviderWrapper;
import dev.felnull.otyacraftengine.data.provider.TagProviderWrapper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;

import java.util.concurrent.CompletableFuture;


public class WrappedFabricPoiTypeTagProvider extends FabricTagProvider<PoiType> {
    private final PoiTypeTagProviderWrapper tagProviderWrapper;

    public WrappedFabricPoiTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookup, PoiTypeTagProviderWrapper tagProviderWrapper) {
        super(output, Registries.POINT_OF_INTEREST_TYPE, lookup);
        this.tagProviderWrapper = tagProviderWrapper;
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        this.tagProviderWrapper.generateTag(new PoiTypeTagProviderAccessImpl());
    }

    private class PoiTypeTagProviderAccessImpl implements TagProviderWrapper.TagProviderAccess<PoiType, TagProviderWrapper.TagAppenderWrapper<PoiType>> {
        @Override
        public TagProviderWrapper.TagAppenderWrapper<PoiType> tag(TagKey<PoiType> tagKey) {
            return new WrappedFabricTagProvider.TagAppenderWrapperImpl<>(WrappedFabricPoiTypeTagProvider.this.tag(tagKey));
        }
    }
}

