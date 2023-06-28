package me.katie.curium.impl.asm;

import me.katie.curium.impl.CuriumConstants;
import me.katie.curium.impl.asm.util.KnotLoaderHacks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Because sometimes Mixins just aren't enough...
 */
public class CuriumASM {
    private CuriumASM() {
        throw new UnsupportedOperationException("Cannot instantiate CuriumASM");
    }

    public static final Logger LOGGER = LoggerFactory.getLogger("Curium/ASM");

    public static void init() {
        // Define stubbed LWJGL callback interfaces.
        KnotLoaderHacks.defineReplacerClasses(
                "org/lwjgl/glfw",
                CuriumConstants.PACKAGE + "/stub/lwjgl",
                new String[] {
                        "GLFWErrorCallbackI"
                }
        );
    }
}
