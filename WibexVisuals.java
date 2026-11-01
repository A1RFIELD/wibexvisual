package com.wibex.visuals;

import com.wibex.visuals.gui.ClickGui;
import com.wibex.visuals.module.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import org.lwjgl.glfw.GLFW;

public class WibexVisuals implements ClientModInitializer {
    private boolean shiftWasDown;

    @Override
    public void onInitializeClient() {
        ModuleManager.init();
        WinterMenu.register();

        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            // Right Shift opens / closes the menu
            boolean down = Keys.isDown(GLFW.GLFW_KEY_RIGHT_SHIFT);
            if (down && !shiftWasDown) {
                if (mc.currentScreen instanceof ClickGui gui) {
                    gui.close();
                } else if (mc.currentScreen == null || mc.currentScreen instanceof TitleScreen) {
                    mc.setScreen(new ClickGui(mc.currentScreen));
                }
            }
            shiftWasDown = down;

            if (mc.player == null || mc.world == null) return;
            for (Module m : ModuleManager.all()) {
                if (m.enabled) m.onTick();
            }
        });

        HudRenderCallback.EVENT.register((ctx, tickCounter) -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player == null) return;
            ModuleManager.get(Hud.class).render(ctx);
        });
    }
}
