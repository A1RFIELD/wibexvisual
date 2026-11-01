package com.wibex.visuals.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class Hud extends Module {
    public static final int PURPLE = 0xFF9B30FF;

    public Hud() { super("HUD"); }

    public void render(DrawContext ctx) {
        if (!enabled) return;
        MinecraftClient mc = mc();
        if (mc.player == null) return;
        TextRenderer tr = mc.textRenderer;

        String title = "WibexVisuals";
        ctx.fill(3, 3, 9 + tr.getWidth(title), 15, 0x90000000);
        ctx.fill(3, 3, 5, 15, PURPLE);
        ctx.drawTextWithShadow(tr, title, 8, 5, PURPLE);

        ctx.drawTextWithShadow(tr, mc.getCurrentFps() + " FPS", 5, 19, PURPLE);
        String xyz = String.format("XYZ: %.0f %.0f %.0f", mc.player.getX(), mc.player.getY(), mc.player.getZ());
        ctx.drawTextWithShadow(tr, xyz, 5, 30, PURPLE);

        int y = 5;
        int w = ctx.getScaledWindowWidth();
        for (Module m : ModuleManager.all()) {
            if (!m.enabled || m == this) continue;
            int tw = tr.getWidth(m.name);
            ctx.fill(w - tw - 9, y - 1, w - 3, y + 10, 0x90000000);
            ctx.fill(w - 5, y - 1, w - 3, y + 10, PURPLE);
            ctx.drawTextWithShadow(tr, m.name, w - tw - 7, y, PURPLE);
            y += 12;
        }
    }
}
