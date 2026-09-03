package com.codyiscod.leathershorts.alltheshi.magicstick;

import com.codyiscod.leathershorts.alltheshi.global.interfaces.ModAdapter;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

public final class MagicStickItems implements ModAdapter {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> STICK1 = ITEMS.register("stick1", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICK2 = ITEMS.register("stick2", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICK3 = ITEMS.register("stick3", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICK4 = ITEMS.register("stick4", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICK5 = ITEMS.register("stick5", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICK6 = ITEMS.register("stick6", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICK7 = ITEMS.register("stick7", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICK8 = ITEMS.register("stick8", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICK9 = ITEMS.register("stick9", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_STICK = ITEMS.register("magic_stick", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICK10 = ITEMS.register("stick10", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICK11 = ITEMS.register("stick11", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICK12 = ITEMS.register("stick12", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICK13 = ITEMS.register("stick13", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICK14 = ITEMS.register("stick14", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OVERPOWERED_STICK = ITEMS.register("overpowered_stick", () -> new Item(new Item.Properties()));

    @Override
    public void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
