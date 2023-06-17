package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.Monitor;
import com.mojang.blaze3d.platform.MonitorCreator;
import com.mojang.blaze3d.platform.ScreenManager;
import com.mojang.blaze3d.platform.Window;
import me.katie.curium.impl.asm.annotations.OverwriteCtor;
import me.katie.curium.impl.asm.annotations.StubClass;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;

@Mixin(ScreenManager.class)
@StubClass
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class ScreenManagerMixin {
    @OverwriteCtor
    private void curium_overwriteCtor(MonitorCreator monitorCreator) {

    }

    @Overwrite
    public @Nullable Monitor getMonitor(long l) {
        return null;
    }

    @Overwrite
    public @Nullable Monitor findBestMonitor(Window window) {
        return null;
    }
}
