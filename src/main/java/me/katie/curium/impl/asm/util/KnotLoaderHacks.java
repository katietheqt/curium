package me.katie.curium.impl.asm.util;

import me.katie.curium.impl.asm.CuriumASM;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.tree.ClassNode;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.security.CodeSource;

/**
 * Reflective hacks on Knot.
 */
public class KnotLoaderHacks {
    private KnotLoaderHacks() {
        throw new UnsupportedOperationException("Cannot instantiate KnotLoaderHacks");
    }

    private static final ClassLoader KNOT;
    private static final MethodHandle KNOT_DEFINE_CLASS_FWD;

    static {
        // Find Knot (it should be our classloader).
        KNOT = KnotLoaderHacks.class.getClassLoader();

        if (!KNOT.getClass().getName().contains("Knot")) {
            throw new IllegalStateException("Curium was not loaded by Knot!");
        }

        // Reflect defineClassFwd method.
        try {
            Method method = KNOT.getClass().getDeclaredMethod("defineClassFwd", String.class, byte[].class, int.class, int.class, CodeSource.class);
            method.setAccessible(true);

            KNOT_DEFINE_CLASS_FWD = MethodHandles.lookup().unreflect(method);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new IllegalStateException("Knot missing defineClassFwd method", e);
        }
    }

    /**
     * Defines a classnode on the Knot classpath.
     */
    public static Class<?> defineClass(ClassNode clazz) {
        ClassWriter writer = new ClassWriter(0);
        clazz.accept(writer);
        byte[] b = writer.toByteArray();

        try {
            return (Class<?>) KNOT_DEFINE_CLASS_FWD.invoke(KNOT, clazz.name.replace('/', '.'), b, 0, b.length, null);
        } catch (Throwable e) {
            CuriumASM.LOGGER.error("Failed to define class on Knot", e);
            return null;
        }
    }

    /**
     * Defines multiple replacer classes where both packages are used as prefixes for each name in the {@code names}
     * parameter.
     */
    public static void defineReplacerClasses(String targetPackage, String replacerPackage, String[] names) {
        for (String name: names) {
            defineReplacerClass(targetPackage + "/" + name, replacerPackage + "/" + name);
        }

        CuriumASM.LOGGER.debug("Defined {} classes (from package {} - replacing {})", names.length, replacerPackage, targetPackage);
    }

    /**
     * Defines a replacer class by loading it from the current classloader as a resource.
     */
    public static Class<?> defineReplacerClass(String targetName, String replacerName) {
        // Read class and remap it.
        ClassNode clazz = new ClassNode();

        ClassRemapper remapper = new ClassRemapper(clazz, new Remapper() {
            @Override
            public String map(String internalName) {
                return this.mapType(internalName);
            }

            @Override
            public String mapType(String internalName) {
                if (internalName.equals(replacerName)) {
                    return targetName;
                }

                return internalName;
            }
        });

        ASMUtil.getClassNode(replacerName).accept(remapper);

        // Write back and convert to buffer.
        return KnotLoaderHacks.defineClass(clazz);
    }
}
