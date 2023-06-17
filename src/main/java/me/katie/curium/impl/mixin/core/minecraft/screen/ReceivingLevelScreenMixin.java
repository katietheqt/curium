package me.katie.curium.impl.mixin.core.minecraft.screen;

import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ReceivingLevelScreen.class)
public class ReceivingLevelScreenMixin {
    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/System;currentTimeMillis()J"
            )
    )
    private long curium_skipReceivingLevelScreen() {
        return Long.MAX_VALUE;
    }
}
