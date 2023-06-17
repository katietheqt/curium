package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.TextureUtil;
import me.katie.curium.impl.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.io.InputStream;
import java.nio.ByteBuffer;

@Mixin(value = TextureUtil.class, remap = false)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class TextureUtilMixin {
    @Overwrite
    public static ByteBuffer readResource(InputStream inputStream) {
        return Util.stubbed();
    }
}
