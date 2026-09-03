package com.codyiscod.leathershorts.alltheshi.spiketato;

import net.minecraft.client.particle.BreakingItemParticle;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = MOD_ID)
public final class SpiketatoClientEvents {
    /* Register a global event listener to register the thrown model correctly */
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SpiketatoEntityTypes.ROTTEN_SPIKETATO_PROJECTILE.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpecial(
            SpiketatoParticles.ROTTEN_PARTICLE.get(),
            new BreakingItemParticle.Provider()
        );
    }
}