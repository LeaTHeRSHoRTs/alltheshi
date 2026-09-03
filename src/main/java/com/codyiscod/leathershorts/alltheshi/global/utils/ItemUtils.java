package com.codyiscod.leathershorts.alltheshi.global.utils;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

public final class ItemUtils {
    public static DeferredRegister<Item> registerItemBus() {
        return DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    }

    public static RegistryObject<Item> registerItem(DeferredRegister<Item> itemBus, String itemName, Supplier<Item> item) {
        return itemBus.register(itemName, item);
    }

    public static RegistryObject<Item> registerItem(DeferredRegister<Item> itemBus, String itemName) {
        return itemBus.register(itemName, () -> new Item(new Item.Properties()));
    }
}
