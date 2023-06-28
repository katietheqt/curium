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

    /**
     * System property containing an IP or domain name to connect to.
     */
    public static String SERVER_HOST = System.getProperty(PROPERTY_BASE + ".server.host");

    /**
     * System property containing the port to connect to ({@link CuriumProperties#SERVER_HOST SERVER_HOST} must be set).
     */
    public static String SERVER_PORT = System.getProperty(PROPERTY_BASE + ".server.port");
}
