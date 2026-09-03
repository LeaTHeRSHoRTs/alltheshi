package com.codyiscod.leathershorts.alltheshi.spiketato;

import com.codyiscod.leathershorts.alltheshi.ModuleEntrypoint;
import net.minecraftforge.eventbus.api.IEventBus;

public final class SpiketatoEntrypoint extends ModuleEntrypoint {
    public SpiketatoEntrypoint(IEventBus eventBus) {
        super(eventBus);
        this.load(SpiketatoEffects.class);
        this.load(SpiketatoItems.class);
        this.load(SpiketatoEntityTypes.class);
        this.load(SpiketatoParticles.class);
    }
}
