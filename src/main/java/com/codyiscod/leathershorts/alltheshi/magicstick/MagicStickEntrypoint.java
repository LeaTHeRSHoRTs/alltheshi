package com.codyiscod.leathershorts.alltheshi.magicstick;

import com.codyiscod.leathershorts.alltheshi.ModuleEntrypoint;
import net.minecraftforge.eventbus.api.IEventBus;

public final class MagicStickEntrypoint extends ModuleEntrypoint {
    public MagicStickEntrypoint(IEventBus eventBus) {
        super(eventBus);
        this.load(MagicStickItems.class);
    }
}
