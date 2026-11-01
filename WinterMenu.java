package com.wibex.visuals.module;

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;

/** Winter look for the main menu: icy tint and falling snow drawn over the title screen. */
public class WinterMenu extends Module {
    public WinterMenu() { super("WinterMenu"); }

    public static void register() {
        ScreenEvents.AFTER_INIT.register((client, screen, width, height) -> {
            if (screen instanceof TitleScreen) {
                ScreenEvents.afterRender(screen).register((s, ctx, mouseX, mouseY, tickDelta) -> {
                    if (ModuleManager.on(WinterMenu.class)) draw(ctx, s.width, s.height);
                });
            }
        });
    }

    private static void draw(DrawContext ctx, int w, int h) {
        ctx.fillGradient(0, 0, w, h, 0x502A5CA8, 0x60DCEBFF);

        double t = System.currentTimeMillis() / 1000.0;
        for (int i = 0; i < 140; i++) {
            double speed = 14 + (i % 5) * 9;
            int size = 1 + (i % 3);
            double baseX = Math.floorMod(i * 7919, Math.max(1, w));
            double baseY = Math.floorMod(i * 104729, Math.max(1, h));
            int x = (int) (baseX + Math.sin(t * 0.8 + i) * 10.0);
            int y = (int) ((baseY + t * speed) % h);
            ctx.fill(x, y, x + size, y + size, 0xCCFFFFFF);
        }
    }
}
