package com.codyiscod.leathershorts.alltheshi.scaryscale;

import com.codyiscod.leathershorts.alltheshi.ModuleEntrypoint;
import net.minecraftforge.eventbus.api.IEventBus;

public final class ScaryScaleEntrypoint extends ModuleEntrypoint {
    public ScaryScaleEntrypoint(IEventBus eventBus) {
        super(eventBus);
        this.load(ScaryScaleItems.class);
    }
}
