package com.codyiscod.leathershorts.alltheshi.shaddle;

import com.codyiscod.leathershorts.alltheshi.ModuleEntrypoint;
import net.minecraftforge.eventbus.api.IEventBus;

public final class ShaddleEntrypoint extends ModuleEntrypoint {
    public ShaddleEntrypoint(IEventBus eventBus) {
        super(eventBus);
        this.load(ShaddleKeybinds.class);
        this.load(ShaddleItems.class);
    }
}
