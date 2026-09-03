package com.codyiscod.leathershorts.alltheshi.global.interfaces;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface ModAdapter {
    void register(IEventBus eventBus);
    static <V> V create(DeferredRegister<V> registry, String name, Supplier<V> value) {
        return registry.register(name, value).get();
    }
}
