package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.Monitor;
import com.mojang.blaze3d.platform.VideoMode;
import org.spongepowered.asm.mixin.*;

import java.util.List;

@Mixin(Monitor.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class MonitorMixin {
    @Mutable
    @Shadow @Final private List<VideoMode> videoModes;

    @Shadow private VideoMode currentMode;

    @Overwrite
    public void refreshVideoModes() {
        VideoMode mode = new VideoMode(1920, 1080, 8, 8, 8, 60);

        this.videoModes = List.of(mode);
        this.currentMode = mode;
    }
}
