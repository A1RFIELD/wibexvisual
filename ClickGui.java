package com.wibex.visuals.gui;

import com.wibex.visuals.module.AspectRatio;
import com.wibex.visuals.module.Module;
import com.wibex.visuals.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ClickGui extends Screen {
    private final Screen parent;

    public ClickGui(Screen parent) {
        super(Text.literal("WibexVisuals"));
        this.parent = parent;
    }

    private static Text label(Module m) {
        return Text.literal(m.name + ": " + (m.enabled ? "ON" : "OFF"));
    }

    @Override
    protected void init() {
        int w = 150, h = 20, gap = 4, cols = 2;
        int startX = this.width / 2 - (cols * (w + gap) - gap) / 2;
        int i = 0;
        for (Module m : ModuleManager.all()) {
            int x = startX + (i % cols) * (w + gap);
            int y = 40 + (i / cols) * (h + gap);
            this.addDrawableChild(ButtonWidget.builder(label(m), b -> {
                m.toggle();
                b.setMessage(label(m));
            }).dimensions(x, y, w, h).build());
            i++;
        }

        int rows = (i + cols - 1) / cols;
        int y = 40 + rows * (h + gap) + 6;
        AspectRatio ar = ModuleManager.get(AspectRatio.class);
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Aspect ratio: " + ar.label()), b -> {
            ar.next();
            b.setMessage(Text.literal("Aspect ratio: " + ar.label()));
        }).dimensions(this.width / 2 - w - gap / 2, y, w * 2 + gap, h).build());
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        super.render(ctx, mouseX, mouseY, delta);
        ctx.drawCenteredTextWithShadow(this.textRenderer, "WibexVisuals  |  Right Shift - close  |  Hold C - zoom",
                this.width / 2, 16, 0xFF9B30FF);
    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(parent);
    }

    @Override
    public boolean shouldPause() { return false; }
}
