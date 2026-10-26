package com.codyiscod.leathershorts.alltheshi.totemofdying;

import com.codyiscod.leathershorts.alltheshi.global.interfaces.ModAdapter;
import com.codyiscod.leathershorts.alltheshi.totemofdying.items.CookedDeathMeat;
import com.codyiscod.leathershorts.alltheshi.totemofdying.items.DeathMeat;
import com.codyiscod.leathershorts.alltheshi.totemofdying.items.TotemOfDying;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

public final class TotemOfDyingItems implements ModAdapter {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<TotemOfDying> TOTEM_OF_DYING = ITEMS.register("totem_of_dying", TotemOfDying::new);

    public static final RegistryObject<Item> DEATH_CHICKEN = ITEMS.register("death_chicken", () -> new DeathMeat(2, 0.3F));
    public static final RegistryObject<Item> DEATH_BEEF = ITEMS.register("death_beef", () -> new DeathMeat(3, 0.3F));
    public static final RegistryObject<Item> DEATH_MUTTON = ITEMS.register("death_mutton", () -> new DeathMeat(2, 0.3F));
    public static final RegistryObject<Item> DEATH_PORKCHOP = ITEMS.register("death_porkchop", () -> new DeathMeat(3, 0.3F));
    public static final RegistryObject<Item> DEATH_RABBIT = ITEMS.register("death_rabbit", () -> new DeathMeat(3, 0.3F));

    public static final RegistryObject<Item> COOKED_DEATH_CHICKEN = ITEMS.register("cooked_death_chicken", CookedDeathMeat::new);
    public static final RegistryObject<Item> COOKED_DEATH_BEEF = ITEMS.register("cooked_death_beef", CookedDeathMeat::new);
    public static final RegistryObject<Item> COOKED_DEATH_MUTTON = ITEMS.register("cooked_death_mutton", CookedDeathMeat::new);
    public static final RegistryObject<Item> COOKED_DEATH_PORKCHOP = ITEMS.register("cooked_death_porkchop", CookedDeathMeat::new);
    public static final RegistryObject<Item> COOKED_DEATH_RABBIT = ITEMS.register("cooked_death_rabbit", CookedDeathMeat::new);

    @Override
    public void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
