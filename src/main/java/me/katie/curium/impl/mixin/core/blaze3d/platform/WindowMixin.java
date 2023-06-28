package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.DisplayData;
import com.mojang.blaze3d.platform.ScreenManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.platform.WindowEventHandler;
import me.katie.curium.impl.asm.mixin.annotations.OverwriteCtor;
import me.katie.curium.impl.asm.mixin.annotations.StubClass;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Window.class)
@StubClass
@SuppressWarnings({"overwrite"})
public class WindowMixin {
    @OverwriteCtor
    private void curium_overwriteCtor(WindowEventHandler windowEventHandler, ScreenManager screenManager, DisplayData displayData, @Nullable String string, String string2) {

    }
}
