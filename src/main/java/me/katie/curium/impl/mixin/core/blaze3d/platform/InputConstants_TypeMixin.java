package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = InputConstants.Type.class, remap = false)
@SuppressWarnings("overwrite")
public class InputConstants_TypeMixin {
    @Overwrite
    @SuppressWarnings("target")
    private static Component method_27450(Integer i, String s) {
        return Component.translatable(s);
    }

    @Overwrite
    @SuppressWarnings("target")
    private static Component method_27449(Integer i, String s) {
        return Component.translatable(s);
    }

    @Overwrite
    @SuppressWarnings("target")
    private static Component method_27447(Integer i, String s) {
        return Component.translatable(s);
    }
}
