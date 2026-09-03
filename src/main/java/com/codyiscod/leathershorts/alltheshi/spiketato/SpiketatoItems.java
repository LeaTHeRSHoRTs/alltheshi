package com.codyiscod.leathershorts.alltheshi.spiketato;

import com.codyiscod.leathershorts.alltheshi.global.interfaces.ModAdapter;
import com.codyiscod.leathershorts.alltheshi.global.utils.ItemUtils;
import com.codyiscod.leathershorts.alltheshi.spiketato.items.RottenSpiketato;
import com.codyiscod.leathershorts.alltheshi.spiketato.items.Spiketato;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

public final class SpiketatoItems implements ModAdapter {
    public static final DeferredRegister<Item> ITEMS = ItemUtils.registerItemBus();
    public static final RegistryObject<Item> SPIKETATO = ItemUtils.registerItem(ITEMS, "spiketato", () -> new Spiketato(
        5,
        Rarity.COMMON
    ));
    public static final RegistryObject<Item> ROTTEN_SPIKETATO = ItemUtils.registerItem(ITEMS, "rotten_spiketato", RottenSpiketato::new);
    public static final RegistryObject<Item> POISON_SPIKETATO = ItemUtils.registerItem(ITEMS, "poisonous_spiketato", () -> new Spiketato(
        10,
        new MobEffectInstance(MobEffects.POISON, 100, 0),
        Rarity.RARE
    ));
    public static final RegistryObject<Item> BAKED_SPIKETATO = ItemUtils.registerItem(ITEMS, "exploded_electrified_baked_spiketato", () -> new Spiketato(
        60,
        Rarity.EPIC
    ));
    @Override
    public void register(IEventBus eventBus) { ITEMS.register(eventBus); }
}
