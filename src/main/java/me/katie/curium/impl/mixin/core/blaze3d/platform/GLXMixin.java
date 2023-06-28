package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.Window;
import me.katie.curium.impl.CuriumConstants;
import me.katie.curium.impl.asm.mixin.annotations.StubClass;
import me.katie.curium.impl.api.CuriumImpl;
import me.katie.curium.impl.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.util.Locale;
import java.util.function.LongSupplier;

@Mixin(value = GLX.class, remap = false)
@StubClass
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class GLXMixin {
    @Shadow private static String cpuInfo;

    @Overwrite
    public static String getOpenGLVersionString() {
        return CuriumConstants.DESCRIPTION;
    }

    @Overwrite
    public static int _getRefreshRate(Window window) {
        return 60;
    }

    @Overwrite
    public static String _getLWJGLVersion() {
        return CuriumConstants.DESCRIPTION;
    }

    @Overwrite
    public static LongSupplier _initGlfw() {
        return System::nanoTime;
    }

    @Overwrite
    public static boolean _shouldClose(Window window) {
        return CuriumImpl.get().shouldClose;
    }

    @Overwrite
    public static void _init(int i, boolean bl) {
        try {
            CentralProcessor processor = new SystemInfo().getHardware().getProcessor();
            int coreCount = processor.getLogicalProcessorCount();
            String processorId = processor.getProcessorIdentifier().getName();

            cpuInfo = String.format(Locale.ROOT, "%dx %s", coreCount, processorId).replaceAll("\\s+", " ");
        } catch (Throwable ignored) {
        }
    }

    @Overwrite
    public static void _renderCrosshair(int i, boolean bl, boolean bl2, boolean bl3) {
        Util.stubbed();
    }
}
