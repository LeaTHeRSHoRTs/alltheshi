package com.codyiscod.leathershorts.alltheshi.shaddle;

import com.codyiscod.leathershorts.alltheshi.global.interfaces.ModAdapter;
import net.minecraftforge.eventbus.api.IEventBus;
import org.lwjgl.glfw.GLFW;

public final class ShaddleKeybinds implements ModAdapter {
    public static final String KEY_CATEGORY = "key.categories.alltheshi";
    public static final net.minecraftforge.client.settings.KeyConflictContext KEY_CONTEXT =
        net.minecraftforge.client.settings.KeyConflictContext.IN_GAME;

    public static final net.minecraft.client.KeyMapping ACTIVATE =
        new net.minecraft.client.KeyMapping(
            "key.alltheshi.shad",
            GLFW.GLFW_KEY_G,
            KEY_CATEGORY
        );

    @Override
    public void register(IEventBus eventBus) {
        eventBus.register(ACTIVATE);
    }
}
