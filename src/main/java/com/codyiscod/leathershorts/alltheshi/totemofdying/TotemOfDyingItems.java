package com.codyiscod.leathershorts.alltheshi.totemofdying;

import com.codyiscod.leathershorts.alltheshi.global.interfaces.ModAdapter;
import com.codyiscod.leathershorts.alltheshi.totemofdying.items.CookedDeathMeat;
import com.codyiscod.leathershorts.alltheshi.totemofdying.items.DeathMeat;
import com.codyiscod.leathershorts.alltheshi.totemofdying.items.TotemOfDying;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

public final class TotemOfDyingItems implements ModAdapter {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<TotemOfDying> TOTEM_OF_DYING = ITEMS.register("totem_of_dying", TotemOfDying::new);

    public static final RegistryObject<Item> DEATH_CHICKEN = ITEMS.register("death_chicken", () -> new DeathMeat(2, 0.3F, SoundEvents.CHICKEN_HURT));
    public static final RegistryObject<Item> DEATH_BEEF = ITEMS.register("death_beef", () -> new DeathMeat(3, 0.3F, SoundEvents.COW_HURT));
    public static final RegistryObject<Item> DEATH_MUTTON = ITEMS.register("death_mutton", () -> new DeathMeat(2, 0.3F, SoundEvents.SHEEP_HURT));
    public static final RegistryObject<Item> DEATH_PORKCHOP = ITEMS.register("death_porkchop", () -> new DeathMeat(3, 0.3F, SoundEvents.PIG_HURT));
    public static final RegistryObject<Item> DEATH_RABBIT = ITEMS.register("death_rabbit", () -> new DeathMeat(3, 0.3F, SoundEvents.RABBIT_HURT));

    public static final RegistryObject<Item> COOKED_DEATH_CHICKEN = ITEMS.register("cooked_death_chicken", () -> new CookedDeathMeat(SoundEvents.CHICKEN_AMBIENT));
    public static final RegistryObject<Item> COOKED_DEATH_BEEF = ITEMS.register("cooked_death_beef", () -> new CookedDeathMeat(SoundEvents.COW_AMBIENT));
    public static final RegistryObject<Item> COOKED_DEATH_MUTTON = ITEMS.register("cooked_death_mutton", () -> new CookedDeathMeat(SoundEvents.SHEEP_AMBIENT));
    public static final RegistryObject<Item> COOKED_DEATH_PORKCHOP = ITEMS.register("cooked_death_porkchop", () -> new CookedDeathMeat(SoundEvents.PIG_AMBIENT));
    public static final RegistryObject<Item> COOKED_DEATH_RABBIT = ITEMS.register("cooked_death_rabbit", () -> new CookedDeathMeat(SoundEvents.RABBIT_AMBIENT));

    @Override
    public void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
