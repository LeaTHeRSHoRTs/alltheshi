package com.codyiscod.leathershorts.alltheshi.spiketato;

import com.codyiscod.leathershorts.alltheshi.global.interfaces.ModAdapter;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

public final class SpiketatoParticles implements ModAdapter {
    private static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MOD_ID);

    public static final RegistryObject<ParticleType<ItemParticleOption>> ROTTEN_PARTICLE = PARTICLES.register("rotten_particle", () -> new ParticleType<>(false, ItemParticleOption.DESERIALIZER) {
        @Override
        public com.mojang.serialization.@NotNull Codec<ItemParticleOption> codec() {
            return ItemParticleOption.codec(this);
        }
    });

    @Override
    public void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}
