package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.GlStateManager;
import me.katie.curium.impl.asm.annotations.StubClass;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.nio.ByteBuffer;

@Mixin(value = GlStateManager.class, remap = false)
@StubClass
@SuppressWarnings("overwrite")
public class GlStateManagerMixin {
    @Overwrite
    public static int glGetProgrami(int i, int j) {
        return 1337;
    }

    @Overwrite
    public static int glCreateShader(int i) {
        return 1337;
    }

    @Overwrite
    public static int glGetShaderi(int i, int j) {
        return 1337;
    }

    @Overwrite
    public static int glCreateProgram() {
        return 1337;
    }

    @Overwrite
    public static int _glGetUniformLocation(int i, CharSequence charSequence) {
        return 1337;
    }

    @Overwrite
    public static int _glGetAttribLocation(int i, CharSequence charSequence) {
        return 1337;
    }

    @Overwrite
    public static int _glGenBuffers() {
        return 1337;
    }

    @Overwrite
    public static int _glGenVertexArrays() {
        return 1337;
    }

    @Overwrite
    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static ByteBuffer _glMapBuffer(int i, int j) {
        return ByteBuffer.allocate(1024);
    }

    @Overwrite
    public static int glGenFramebuffers() {
        return 1337;
    }

    @Overwrite
    public static int glGenRenderbuffers() {
        return 1337;
    }

    @Overwrite
    public static int glCheckFramebufferStatus(int i) {
        return 1337;
    }

    @Overwrite
    public static int getBoundFramebuffer() {
        return 1337;
    }

    @Overwrite
    public static String glGetShaderInfoLog(int i, int j) {
        return "";
    }

    @Overwrite
    public static String glGetProgramInfoLog(int i, int j) {
        return "";
    }

    @Overwrite
    public static int _getTexLevelParameter(int i, int j, int k) {
        return 1337;
    }

    @Overwrite
    public static int _genTexture() {
        return 1337;
    }

    @Overwrite
    public static int _getTextureId(int i) {
        return 1337;
    }

    @Overwrite
    public static int _getActiveTexture() {
        return 1337;
    }

    @Overwrite
    public static int _getError() {
        return 0;
    }

    @Overwrite
    public static String _getString(int i) {
        return "";
    }

    @Overwrite
    public static int _getInteger(int i) {
        return 1337;
    }
}
