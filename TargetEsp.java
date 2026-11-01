package com.wibex.visuals.module;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.EntityHitResult;

/** Highlights only your current target: the entity under the crosshair, else the nearest player within 8 blocks. */
public class TargetEsp extends Module {
    public static Entity target;

    public TargetEsp() { super("TargetESP"); }

    @Override
    public void onTick() {
        Entity t = null;
        if (mc().crosshairTarget instanceof EntityHitResult hit && hit.getEntity() instanceof LivingEntity) {
            t = hit.getEntity();
        }
        if (t == null) {
            double best = 64.0; // 8 blocks squared
            for (PlayerEntity p : mc().world.getPlayers()) {
                if (p == mc().player) continue;
                double d = mc().player.squaredDistanceTo(p);
                if (d < best) { best = d; t = p; }
            }
        }
        target = t;
    }

    @Override
    public void onDisable() { target = null; }
}
