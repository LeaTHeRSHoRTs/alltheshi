package com.codyiscod.leathershorts.alltheshi.spiketato;

import com.codyiscod.leathershorts.alltheshi.global.interfaces.ModAdapter;
import com.codyiscod.leathershorts.alltheshi.spiketato.projectiles.RottenSpiketatoProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

public final class SpiketatoEntityTypes implements ModAdapter {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);

    public static final RegistryObject<EntityType<RottenSpiketatoProjectile>> ROTTEN_SPIKETATO_PROJECTILE =
        ENTITY_TYPES.register("rotten_spiketato", () -> EntityType.Builder.<RottenSpiketatoProjectile>of(
                RottenSpiketatoProjectile::new, MobCategory.MISC)
            .sized(0.25F, 0.25F)
            .clientTrackingRange(4)
            .updateInterval(10)
            .build("rotten_spiketato"));

    @Override
    public void register(IEventBus eventBus) { ENTITY_TYPES.register(eventBus); }
}
