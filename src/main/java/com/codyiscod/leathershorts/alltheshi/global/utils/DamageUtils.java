package com.codyiscod.leathershorts.alltheshi.global.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DamageUtils {
    /**
     * Gets a DamageType object from the minecraft registries based on a ResourceKey
     * (Only use if level is not available in the current scope)
     * @param type The ResourceKey that references the DamageType
     */
    public static Holder<DamageType> getDamageType(@NotNull ResourceKey<DamageType> type) {
        Level level = null;
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();

        if (server != null) {
            level = server.overworld();
        } else if (FMLEnvironment.dist.isClient()) {
            level = Minecraft.getInstance().level;
        }

        if (level == null) {
            throw new IllegalStateException("Cannot retrieve DamageSource: No active level context found.");
        }

        return level.registryAccess()
            .registryOrThrow(Registries.DAMAGE_TYPE)
            .getHolder(type)
            .orElseThrow(() -> new IllegalArgumentException("Unknown DamageType registry key: " + type.location()));
    }

    /**
     * Gets a DamageType from the minecraft registries based on a ResourceKey, and uses the level to access the registries
     * (more efficient than the other getDamageType)
     * @param level The level used to get the registries
     * @param type The ResourceKey that references the DamageType
     */
    public static Holder<DamageType> getDamageType(@NotNull Level level, @NotNull ResourceKey<DamageType> type) {
        return level.registryAccess()
            .registryOrThrow(Registries.DAMAGE_TYPE)
            .getHolder(type)
            .orElseThrow(() -> new IllegalArgumentException("Unknown DamageType registry key: " + type.location()));
    }

    /**
     * Creates a new DamageSource object from a ResourceKey for a DamageType, with no cause or provider
     * (Do NOT use with the %x$s in en_us.json, as they will not be set)
     * @param type The ResourceKey that references the DamageType
     */
    public static DamageSource getDamageSource(@NotNull Level level, @NotNull ResourceKey<DamageType> type) {
        return new DamageSource(getDamageType(level, type));
    }

    /**
     * Creates a new DamageSource object from a ResourceKey with a receiver and a provider
     * @param type     The ResourceKey that references the DamageType
     * @param provider The entity that is to blame for the damage (%2$s)
     */
    public static DamageSource getDamageSource(@NotNull ResourceKey<DamageType> type, @NotNull Entity provider) {
        return getDamageSource(type, provider, null);
    }

    /**
     * Creates a new DamageSource object from a ResourceKey with only a receiver
     * @param type     The ResourceKey that references the DamageType
     * @param provider The entity that caused the damage (%2$s)
     * @param causingEntity The entity that caused the damage (%3$s)
     */
    public static DamageSource getDamageSource(@NotNull ResourceKey<DamageType> type, @NotNull Entity provider, @Nullable Entity causingEntity) {
        return new DamageSource(getDamageType(provider.level(), type), provider, causingEntity);
    }
}
