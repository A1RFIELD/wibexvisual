package com.wibex.visuals.module;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

/** Full brightness: keeps a local night-vision effect on you (client side only, no particles / icon). */
public class Ambient extends Module {
    public Ambient() { super("Ambient"); }

    @Override
    public void onTick() {
        var p = mc().player;
        StatusEffectInstance cur = p.getStatusEffect(StatusEffects.NIGHT_VISION);
        if (cur == null || cur.getDuration() < 300) {
            p.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 600, 0, false, false));
        }
    }

    @Override
    public void onDisable() {
        if (mc().player != null) mc().player.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }
}
