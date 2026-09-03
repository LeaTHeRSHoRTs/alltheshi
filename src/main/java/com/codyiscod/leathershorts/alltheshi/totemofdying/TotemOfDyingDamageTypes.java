package com.codyiscod.leathershorts.alltheshi.totemofdying;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

public final class TotemOfDyingDamageTypes {
    public static final ResourceKey<DamageType> TOTEM_HARM = ResourceKey.create(
        Registries.DAMAGE_TYPE,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "totem_harm")
    );

    public static final ResourceKey<DamageType> TOTEM_HARM_ONCE = ResourceKey.create(
        Registries.DAMAGE_TYPE,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "totem_harm_once")
    );
}
