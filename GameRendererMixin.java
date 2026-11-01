package com.wibex.visuals.mixin;

import com.wibex.visuals.module.AspectRatio;
import com.wibex.visuals.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.Window;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    // AspectRatio: rescale the horizontal part of the projection matrix.
    // The config is "required": false, so if this does not fit the game build, only AspectRatio stops working.
    @Inject(method = "getBasicProjectionMatrix", at = @At("RETURN"), cancellable = true)
    private void wibex$aspect(float fov, CallbackInfoReturnable<Matrix4f> cir) {
        if (!ModuleManager.on(AspectRatio.class)) return;
        Window w = MinecraftClient.getInstance().getWindow();
        float current = (float) w.getFramebufferWidth() / (float) w.getFramebufferHeight();
        float target = ModuleManager.get(AspectRatio.class).ratio;
        cir.setReturnValue(new Matrix4f(cir.getReturnValue()).scale(current / target, 1.0f, 1.0f));
    }
}
