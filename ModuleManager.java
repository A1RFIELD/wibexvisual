package com.wibex.visuals.module;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModuleManager {
    private static final Map<Class<? extends Module>, Module> MODULES = new LinkedHashMap<>();

    public static void init() {
        add(new Hud());
        add(new TargetEsp());
        add(new Esp());
        add(new Ambient());
        add(new Zoom());
        add(new AspectRatio());
        add(new WinterMenu());
        get(Hud.class).enabled = true;
        get(WinterMenu.class).enabled = true;
    }

    private static void add(Module m) { MODULES.put(m.getClass(), m); }

    public static Collection<Module> all() { return MODULES.values(); }

    @SuppressWarnings("unchecked")
    public static <T extends Module> T get(Class<T> c) { return (T) MODULES.get(c); }

    public static boolean on(Class<? extends Module> c) {
        Module m = MODULES.get(c);
        return m != null && m.enabled;
    }
}
