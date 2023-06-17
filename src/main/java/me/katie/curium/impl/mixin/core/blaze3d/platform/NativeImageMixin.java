package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.NativeImage;
import me.katie.curium.impl.asm.annotations.OverwriteCtor;
import me.katie.curium.impl.asm.annotations.StubClass;
import org.spongepowered.asm.mixin.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@Mixin(NativeImage.class)
@StubClass
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class NativeImageMixin {
    @Mutable
    @Shadow @Final private NativeImage.Format format;

    @Mutable
    @Shadow @Final private int width;

    @Mutable
    @Shadow @Final private int height;

    @OverwriteCtor
    private void curium_overwriteCtor(NativeImage.Format format, int i, int j, boolean bl) {
        this.format = format;
        this.width = i;
        this.height = j;
    }

    @OverwriteCtor
    private void curium_overwriteCtor(NativeImage.Format format, int i, int j, boolean bl, long l) {
        this.format = format;
        this.width = i;
        this.height = j;
    }

    @Overwrite(remap = false)
    public String toString() {
        return "CuriumNativeImage";
    }

    @Overwrite
    public static NativeImage read(InputStream inputStream) {
        return new NativeImage(-1, -1, false);
    }

    @Overwrite
    public static NativeImage read(NativeImage.Format format, InputStream inputStream) {
        return new NativeImage(format, -1, -1, false);
    }

    @Overwrite
    public static NativeImage read(ByteBuffer byteBuffer) throws IOException {
        return NativeImage.read(NativeImage.Format.RGBA, byteBuffer);
    }

    @Overwrite
    public static NativeImage read(NativeImage.Format format, ByteBuffer byteBuffer) {
        return new NativeImage(format, -1, -1, false);
    }

    @Overwrite
    public int getPixelRGBA(int i, int j) {
        return 0;
    }

    @Overwrite
    public byte getRedOrLuminance(int i, int j) {
        return 0;
    }

    @Overwrite
    public byte getGreenOrLuminance(int i, int j) {
        return 0;
    }

    @Overwrite
    public byte getBlueOrLuminance(int i, int j) {
        return 0;
    }

    @Overwrite
    public byte getLuminanceOrAlpha(int i, int j) {
        return 0;
    }

    @Deprecated
    @Overwrite
    public int[] makePixelArray() {
        return new int[] {};
    }

    @Overwrite
    public byte[] asByteArray() {
        return new byte[] {};
    }

    @Overwrite
    public static NativeImage fromBase64(String string) {
        return new NativeImage(NativeImage.Format.RGBA, -1, -1, false);
    }
}
