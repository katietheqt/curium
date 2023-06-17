package me.katie.curium.impl.mixin.core.blaze3d.audio;

import com.mojang.blaze3d.audio.OpenAlUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = OpenAlUtil.class, remap = false)
@SuppressWarnings("overwrite")
public class OpenAlUtilMixin {
    @Overwrite
    static boolean checkALError(String string) {
        return false;
    }

    @Overwrite
    static boolean checkALCError(long l, String string) {
        return false;
    }
}
