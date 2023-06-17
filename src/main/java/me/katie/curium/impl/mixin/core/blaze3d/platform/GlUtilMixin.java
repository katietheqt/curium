package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.GlUtil;
import me.katie.curium.impl.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.nio.Buffer;
import java.nio.ByteBuffer;

@Mixin(GlUtil.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class GlUtilMixin {
    @Overwrite
    public static ByteBuffer allocateMemory(int i) {
        return Util.stubbed();
    }

    @Overwrite
    public static void freeMemory(Buffer buffer) {

    }
}
