package com.codyiscod.leathershorts.alltheshi.shaddle;

import com.codyiscod.leathershorts.alltheshi.global.interfaces.ModAdapter;
import com.codyiscod.leathershorts.alltheshi.shaddle.items.Shaddle;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

public final class ShaddleItems implements ModAdapter {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> SHADDLE = ITEMS.register("shaddle", Shaddle::new);

    @Override
    public void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
