package me.katie.curium.impl;

/**
 * System properties for configuring Curium.
 */
public class CuriumProperties {
    private CuriumProperties() {
        throw new UnsupportedOperationException("Cannot instantiate CuriumConstants");
    }

    public static final String PROPERTY_BASE = "curium";

    /**
     * If set, the ASM package will pass output through a {@link org.objectweb.asm.util.CheckClassAdapter} before
     * returning to Mixin.
     */
    public static boolean ASM_CHECK = System.getProperty(PROPERTY_BASE + ".asm.check") != null;
}
