package com.wibex.visuals.mixin;

import com.wibex.visuals.module.Esp;
import com.wibex.visuals.module.ModuleManager;
import com.wibex.visuals.module.TargetEsp;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    // ESP / TargetESP: glow outline (visible through walls)
    @Inject(method = "isGlowing", at = @At("HEAD"), cancellable = true)
    private void wibex$glow(CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity) (Object) this;
        if (self == MinecraftClient.getInstance().player) return;
        boolean glow = (ModuleManager.on(Esp.class) && self instanceof PlayerEntity)
                || (ModuleManager.on(TargetEsp.class) && self == TargetEsp.target);
        if (glow) cir.setReturnValue(true);
    }
}
