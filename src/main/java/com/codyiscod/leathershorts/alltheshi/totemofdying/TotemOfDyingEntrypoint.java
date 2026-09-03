package com.codyiscod.leathershorts.alltheshi.totemofdying;

import com.codyiscod.leathershorts.alltheshi.ModuleEntrypoint;
import net.minecraftforge.eventbus.api.IEventBus;

public final class TotemOfDyingEntrypoint extends ModuleEntrypoint {
    public TotemOfDyingEntrypoint(IEventBus eventBus) {
        super(eventBus);
        this.load(TotemOfDyingItems.class);
    }
}
