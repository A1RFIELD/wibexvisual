package com.wibex.visuals.module;

import org.lwjgl.glfw.GLFW;

/** Hold C to zoom in (FOV 30). */
public class Zoom extends Module {
    private int saved = -1;

    public Zoom() { super("Zoom"); }

    @Override
    public void onTick() {
        boolean want = mc().currentScreen == null && Keys.isDown(GLFW.GLFW_KEY_C);
        if (want) {
            if (saved < 0) saved = mc().options.getFov().getValue();
            mc().options.getFov().setValue(30);
        } else {
            restore();
        }
    }

    private void restore() {
        if (saved >= 0) {
            mc().options.getFov().setValue(saved);
            saved = -1;
        }
    }

    @Override
    public void onDisable() { restore(); }
}
