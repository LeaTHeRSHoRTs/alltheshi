package com.codyiscod.leathershorts.alltheshi.spiketato;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

public final class SpiketatoDamageTypes {
    public static final ResourceKey<DamageType> BLEEDING = ResourceKey.create(
        Registries.DAMAGE_TYPE,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "bleeding")
    );

    public static final ResourceKey<DamageType> EXPLODE_DIE = ResourceKey.create(
        Registries.DAMAGE_TYPE,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "explode_die")
    );

    public static final ResourceKey<DamageType> SPIKED = ResourceKey.create(
        Registries.DAMAGE_TYPE,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "spiked")
    );
}
