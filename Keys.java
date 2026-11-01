package com.wibex.visuals.module;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class Keys {
    public static boolean isDown(int glfwKey) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.getWindow() == null) return false;
        return GLFW.glfwGetKey(mc.getWindow().getHandle(), glfwKey) == GLFW.GLFW_PRESS;
    }
}
