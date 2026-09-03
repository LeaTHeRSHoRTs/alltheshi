package com.codyiscod.leathershorts.alltheshi.spiketato;

import com.codyiscod.leathershorts.alltheshi.global.interfaces.ModAdapter;
import com.codyiscod.leathershorts.alltheshi.spiketato.effects.BleedingEffect;
import com.codyiscod.leathershorts.alltheshi.spiketato.effects.ExplodeEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

public final class SpiketatoEffects implements ModAdapter {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MOD_ID);

    public static final RegistryObject<BleedingEffect> BLEEDING = MOB_EFFECTS.register("bleeding",BleedingEffect::new);
    public static final RegistryObject<ExplodeEffect> DIE = MOB_EFFECTS.register("explode_die", ExplodeEffect::new);

    @Override
    public void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
