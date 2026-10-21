package com.codyiscod.leathershorts.alltheshi.totemofdying.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

public class DeathBeef extends DeathMeat {
    @Override
    SoundEvent getSound() {
        return SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(MOD_ID, "sounds"), 0);
    }
}