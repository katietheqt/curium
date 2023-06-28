package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.MacosUtil;
import me.katie.curium.impl.asm.mixin.annotations.StubClass;
import net.minecraft.server.packs.resources.IoSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.io.InputStream;

@Mixin(MacosUtil.class)
@StubClass(isThrowing = true)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class MacosUtilMixin {
    @Overwrite
    public static void toggleFullscreen(long l) {

    }

    @Overwrite
    public static void loadIcon(IoSupplier<InputStream> ioSupplier) {

    }
}
