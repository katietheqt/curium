package me.katie.curium.impl.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Because sometimes ASM just isn't enough...
 */
public class UnsafeUtil {
    private UnsafeUtil() {
        throw new UnsupportedOperationException("Cannot instantiate UnsafeUtil");
    }

    public static final Unsafe UNSAFE;

    static {
        // Reflect the Unsafe instance out of the static field in the Unsafe class.
        Field unsafeInstance = null;

        for (Field field: Unsafe.class.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) && field.getType() == Unsafe.class) {
                unsafeInstance = field;
                break;
            }
        }

        if (unsafeInstance == null) {
            throw new IllegalStateException("couldn't find Unsafe instance reference");
        }

        try {
            unsafeInstance.setAccessible(true);
            UNSAFE = (Unsafe) unsafeInstance.get(null);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("failed to reflect unsafe instance", e);
        }
    }
}
