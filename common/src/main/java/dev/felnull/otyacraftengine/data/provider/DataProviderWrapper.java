package dev.felnull.otyacraftengine.data.provider;

import dev.felnull.otyacraftengine.data.CrossDataGeneratorAccess;
import dev.felnull.otyacraftengine.data.DataGeneratorType;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;

public abstract class DataProviderWrapper<T extends DataProvider> {
    private final CrossDataGeneratorAccess crossDataGeneratorAccess;

    public DataProviderWrapper(CrossDataGeneratorAccess crossDataGeneratorAccess) {
        this.crossDataGeneratorAccess = crossDataGeneratorAccess;
    }

    public CrossDataGeneratorAccess getCrossGeneratorAccess() {
        return crossDataGeneratorAccess;
    }

    public abstract T getProvider();

    public abstract DataGeneratorType getGeneratorType();

    protected DataGenerator getGenerator() {
        return getCrossGeneratorAccess().getVanillaGenerator();
    }
}