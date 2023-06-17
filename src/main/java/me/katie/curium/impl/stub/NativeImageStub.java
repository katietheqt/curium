package me.katie.curium.impl.stub;

import com.mojang.blaze3d.platform.NativeImage;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.stb.STBTTFontinfo;

import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Path;

@SuppressWarnings("unused")
public class NativeImageStub implements AutoCloseable {
    private static final int OFFSET_A = 24;
    private static final int OFFSET_B = 16;
    private static final int OFFSET_G = 8;
    private static final int OFFSET_R = 0;
    private final NativeImage.Format format;
    private final int width;
    private final int height;

    public NativeImageStub(int i, int j, boolean bl) {
        this(NativeImage.Format.RGBA, i, j, bl);
    }

    public NativeImageStub(NativeImage.Format format, int width, int height, boolean bl) {
        this.format = format;
        this.width = width;
        this.height = height;
    }

    public String toString() {
        return "CuriumNativeImage";
    }

    public static NativeImageStub read(InputStream inputStream) {
        return new NativeImageStub(-1, -1, false);
    }

    public static NativeImageStub read(@Nullable NativeImage.Format format, InputStream inputStream) {
        return new NativeImageStub(format, -1, -1, false);
    }

    public static NativeImageStub read(ByteBuffer byteBuffer) {
        return NativeImageStub.read(NativeImage.Format.RGBA, byteBuffer);
    }

    public static NativeImageStub read(@Nullable NativeImage.Format format, ByteBuffer byteBuffer) {
        return new NativeImageStub(format, -1, -1, false);
    }

    @Override
    public void close() {

    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public NativeImage.Format format() {
        return this.format;
    }

    public int getPixelRGBA(int i, int j) {
        return 0;
    }

    public void setPixelRGBA(int i, int j, int k) {

    }

    public void setPixelLuminance(int i, int j, byte b) {

    }

    public byte getRedOrLuminance(int i, int j) {
        return 0;
    }

    public byte getGreenOrLuminance(int i, int j) {
        return 0;
    }

    public byte getBlueOrLuminance(int i, int j) {
        return 0;
    }

    public byte getLuminanceOrAlpha(int i, int j) {
        return 0;
    }

    public void blendPixel(int i, int j, int k) {

    }

    @Deprecated
    public int[] makePixelArray() {
        return new int[] {};
    }

    public void upload(int i, int j, int k, boolean bl) {

    }

    public void upload(int i, int j, int k, int l, int m, int n, int o, boolean bl, boolean bl2) {

    }

    public void upload(int i, int j, int k, int l, int m, int n, int o, boolean bl, boolean bl2, boolean bl3, boolean bl4) {

    }

    public void downloadTexture(int i, boolean bl) {

    }

    public void downloadDepthBuffer(float f) {

    }

    public void drawPixels() {

    }

    public void writeToFile(File file) {

    }

    public void copyFromFont(STBTTFontinfo sTBTTFontinfo, int i, int j, int k, float f, float g, float h, float l, int m, int n) {

    }

    public void writeToFile(Path path) {

    }

    public byte[] asByteArray() {
        return new byte[] {};
    }

    public void copyFrom(NativeImageStub nativeImage) {

    }

    public void fillRect(int i, int j, int k, int l, int m) {

    }

    public void copyRect(int i, int j, int k, int l, int m, int n, boolean bl, boolean bl2) {

    }

    public void copyRect(NativeImageStub nativeImage, int i, int j, int k, int l, int m, int n, boolean bl, boolean bl2) {

    }

    public void flipY() {

    }

    public void resizeSubRectTo(int i, int j, int k, int l, NativeImageStub nativeImage) {

    }
    
    public void untrack() {
        
    }

    public static NativeImageStub fromBase64(String string) {
        return new NativeImageStub(NativeImage.Format.RGBA, -1, -1, false);
    }

    public static int getA(int i) {
        return i >> OFFSET_A & 0xFF;
    }

    public static int getR(int i) {
        return i >> OFFSET_R & 0xFF;
    }

    public static int getG(int i) {
        return i >> OFFSET_G & 0xFF;
    }

    public static int getB(int i) {
        return i >> OFFSET_B & 0xFF;
    }

    public static int combine(int i, int j, int k, int l) {
        return (i & 0xFF) << OFFSET_A | (j & 0xFF) << OFFSET_B | (k & 0xFF) << OFFSET_G | (l & 0xFF) << OFFSET_R;
    }
}
