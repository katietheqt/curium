package me.katie.curium.impl.mixin.core.blaze3d.systems;

import com.mojang.blaze3d.pipeline.RenderCall;
import com.mojang.blaze3d.systems.RenderSystem;
import me.katie.curium.impl.CuriumConstants;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.concurrent.ConcurrentLinkedQueue;

@Mixin(value = RenderSystem.class, remap = false)
@SuppressWarnings("overwrite")
public class RenderSystemMixin {
    @Shadow
    @Final
    private static ConcurrentLinkedQueue<RenderCall> recordingQueue;

    @Overwrite
    public static void initRenderThread() {

    }

    @Overwrite
    public static boolean isOnRenderThread() {
        return true;
    }

    @Overwrite
    public static boolean isOnRenderThreadOrInit() {
        return true;
    }

    @Overwrite
    public static void initGameThread(boolean bl) {

    }

    @Overwrite
    public static boolean isOnGameThread() {
        return true;
    }

    @Overwrite
    public static void assertInInitPhase() {

    }

    @Overwrite
    public static void assertOnGameThreadOrInit() {

    }

    @Overwrite
    public static void assertOnRenderThreadOrInit() {

    }

    @Overwrite
    public static void assertOnRenderThread() {

    }

    @Overwrite
    public static void assertOnGameThread() {

    }

    @Overwrite
    public static boolean isInInitPhase() {
        return true;
    }

    @Overwrite
    public static void flipFrame(long l) {

    }

    @Overwrite
    public static void replayQueue() {
        assert recordingQueue.isEmpty() : "curium: rendersystem call queue non-empty!";
    }

    @Overwrite
    public static void limitDisplayFPS(int i) {

    }

    @Overwrite
    public static void beginInitialization() {

    }

    @Overwrite
    public static void finishInitialization() {

    }

    @Overwrite
    @Deprecated
    public static void runAsFancy(Runnable runnable) {
        runnable.run();
    }

    @Overwrite
    public static String getBackendDescription() {
        return CuriumConstants.DESCRIPTION;
    }

    @Overwrite
    public static String getCapsString() {
        return CuriumConstants.USING_CURIUM;
    }

    @Overwrite
    public static int maxSupportedTextureSize() {
        return 1024;
    }

    @Overwrite
    public static void recordRenderCall(RenderCall renderCall) {
        renderCall.execute();
    }
}
