package com.wibex.visuals.module;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
    public final String name;
    public boolean enabled;

    protected Module(String name) { this.name = name; }

    protected static MinecraftClient mc() { return MinecraftClient.getInstance(); }

    public void onTick() {}
    public void onEnable() {}
    public void onDisable() {}

    public void toggle() {
        enabled = !enabled;
        if (enabled) onEnable(); else onDisable();
    }
}
