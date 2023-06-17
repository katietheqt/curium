package me.katie.curium.impl.mixin.core.blaze3d;

import com.mojang.blaze3d.Blaze3D;
import me.katie.curium.impl.util.UnsafeUtil;
import net.minecraft.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = Blaze3D.class, remap = false)
@SuppressWarnings("overwrite")
public class Blaze3DMixin {
    @Overwrite
    public static void youJustLostTheGame() {
        // Replacement for the LWJGL call.
        UnsafeUtil.UNSAFE.putInt(0L, 0);
    }

    @Overwrite
    public static double getTime() {
        return Util.getNanos() / 1_000_000_000d;
    }
}
