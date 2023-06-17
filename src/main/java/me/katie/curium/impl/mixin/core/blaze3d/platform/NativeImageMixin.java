package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.NativeImage;
import me.katie.curium.impl.asm.annotations.Erase;
import me.katie.curium.impl.asm.annotations.OverwriteCtor;
import me.katie.curium.impl.asm.annotations.StubClass;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.function.IntUnaryOperator;

@Mixin(NativeImage.class)
@StubClass
@Erase(methods = "writeToChannel(Ljava/nio/channels/WritableByteChannel;)Z")
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
    public static NativeImage read(@Nullable NativeImage.Format format, InputStream inputStream) {
        if (format == null) {
            format = NativeImage.Format.RGBA;
        }

        return new NativeImage(format, -1, -1, false);
    }

    @Overwrite
    public static NativeImage read(ByteBuffer byteBuffer) {
        return new NativeImage(-1, -1, false);
    }

    @Overwrite
    public static NativeImage read(byte[] bs) {
        return new NativeImage(-1, -1, false);
    }

    @Overwrite
    public static NativeImage read(@Nullable NativeImage.Format format, ByteBuffer byteBuffer) {
        if (format == null) {
            format = NativeImage.Format.RGBA;
        }

        return new NativeImage(format, -1, -1, false);
    }

    @Overwrite
    public int getPixelRGBA(int i, int j) {
        return 0;
    }

    @Overwrite
    public NativeImage mappedCopy(IntUnaryOperator intUnaryOperator) {
        return (NativeImage) (Object) this;
    }

    @Overwrite
    public int[] getPixelsRGBA() {
        return new int[] {};
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
}
