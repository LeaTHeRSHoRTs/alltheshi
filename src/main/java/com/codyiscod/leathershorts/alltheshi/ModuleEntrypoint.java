package com.codyiscod.leathershorts.alltheshi;

import com.codyiscod.leathershorts.alltheshi.global.interfaces.ModAdapter;
import net.minecraftforge.eventbus.api.IEventBus;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ModuleEntrypoint {
    private static final Set<Class<? extends ModuleEntrypoint>> LOADED_MODULES =
        Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final IEventBus eventBus;

    public ModuleEntrypoint(IEventBus eventBus) {
        this.eventBus = eventBus;

        Class<? extends ModuleEntrypoint> currentClass = this.getClass();

        if (!LOADED_MODULES.add(currentClass)) {
            throw new IllegalStateException("CRITICAL ARCHITECTURE ERROR: The module entrypoint ["
                + currentClass.getSimpleName()
                + "] has already been initialized once! You cannot instantiate this class twice.");
        }
    }

    public final void load(Class<? extends ModAdapter> registrableClass) {
        try {
            ModAdapter instance = registrableClass.getConstructor().newInstance();
            instance.register(this.eventBus);
            this.onLoadModule(eventBus);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("The class " + registrableClass.getName() + " must have a constructor with 0 arguments");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Could not initialize class " + registrableClass.getName());
        }
    }

    protected void onLoadModule(IEventBus eventBus) {}
}
