package me.morishima.udlede.api.integration;

import net.minecraftforge.fml.common.Loader;

public interface IIntegration {

    String getMODID();

    default boolean isLoaded() {
        return Loader.isModLoaded(getMODID());
    }

}
