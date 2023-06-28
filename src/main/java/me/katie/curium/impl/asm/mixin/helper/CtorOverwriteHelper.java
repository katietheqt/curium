package me.katie.curium.impl.asm.mixin.helper;

import me.katie.curium.impl.asm.mixin.annotations.OverwriteCtor;

/**
 * Helper class for {@link OverwriteCtor}.
 */
public class CtorOverwriteHelper {
    private CtorOverwriteHelper() {
        throw new UnsupportedOperationException("Cannot instantiate CtorOverwriteHelper");
    }

    /**
     * Stub method that is translated to a call to the target super constructor by the annotation handler.
     */
    @SuppressWarnings("unused")
    public static void super_(Object... args) {
        throw new IllegalStateException("super constructor was not transformed out");
    }
}
