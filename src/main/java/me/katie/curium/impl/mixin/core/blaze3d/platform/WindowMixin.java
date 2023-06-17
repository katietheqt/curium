package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.DisplayData;
import com.mojang.blaze3d.platform.ScreenManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.platform.WindowEventHandler;
import me.katie.curium.impl.asm.annotations.OverwriteCtor;
import me.katie.curium.impl.asm.annotations.StubClass;
import me.katie.curium.impl.util.Util;
import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

@Mixin(Window.class)
@StubClass
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class WindowMixin {
    @OverwriteCtor
    private void curium_overwriteCtor(WindowEventHandler windowEventHandler, ScreenManager screenManager, DisplayData displayData, @Nullable String string, String string2) {

    }

    @Overwrite
    private @Nullable ByteBuffer readIconPixels(IoSupplier<InputStream> ioSupplier, IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3) {
        return Util.stubbed();
    }
}
