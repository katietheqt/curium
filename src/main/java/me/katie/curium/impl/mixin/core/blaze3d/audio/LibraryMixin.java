package me.katie.curium.impl.mixin.core.blaze3d.audio;

import com.mojang.blaze3d.audio.Channel;
import com.mojang.blaze3d.audio.Library;
import me.katie.curium.impl.CuriumConstants;
import me.katie.curium.impl.asm.mixin.annotations.StubClass;
import me.katie.curium.impl.util.Util;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Collections;
import java.util.List;
import java.util.OptionalLong;

@Mixin(Library.class)
@StubClass
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class LibraryMixin {
    @Overwrite
    private int getChannelCount() {
        return 30;
    }

    @Overwrite
    public static @Nullable String getDefaultDeviceName() {
        return null;
    }

    @Overwrite
    public String getCurrentDeviceName() {
        return CuriumConstants.USING_CURIUM;
    }

    @Overwrite
    public synchronized boolean hasDefaultDeviceChanged() {
        return false;
    }

    @Overwrite
    private static long openDeviceOrFallback(@Nullable String string) {
        return Util.stubbed();
    }

    @Overwrite
    private static OptionalLong tryOpenDevice(@Nullable String string) {
        return Util.stubbed();
    }

    @Overwrite
    public @Nullable Channel acquireChannel(Library.Pool pool) {
        return new Channel(-1);
    }

    @Overwrite
    public String getDebugString() {
        return CuriumConstants.USING_CURIUM;
    }

    @Overwrite
    public List<String> getAvailableSoundDevices() {
        return Collections.emptyList();
    }

    @Overwrite
    public boolean isCurrentDeviceDisconnected() {
        return false;
    }


}
