package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.GlDebug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Collections;
import java.util.List;

@Mixin(GlDebug.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class GlDebugMixin {
    @Overwrite
    private static void printDebugLog(int i, int j, int k, int l, int m, long n, long o) {

    }

    @Overwrite
    public static List<String> getLastOpenGlDebugMessages() {
        return Collections.emptyList();
    }

    @Overwrite
    public static void enableDebugCallback(int i, boolean bl) {

    }
}
