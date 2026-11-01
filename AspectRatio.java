package com.wibex.visuals.module;

/** Stretches the picture to another aspect ratio (applied in GameRendererMixin). Cycle the value in the menu. */
public class AspectRatio extends Module {
    private static final float[] VALUES = {1.0f, 1.25f, 1.3333f, 1.6f, 2.3704f};
    private static final String[] LABELS = {"1:1", "5:4", "4:3", "16:10", "21:9"};
    private int index = 2;
    public float ratio = VALUES[index];

    public AspectRatio() { super("AspectRatio"); }

    public void next() {
        index = (index + 1) % VALUES.length;
        ratio = VALUES[index];
    }

    public String label() { return LABELS[index]; }
}
