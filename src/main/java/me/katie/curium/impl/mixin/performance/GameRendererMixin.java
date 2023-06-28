package me.katie.curium.impl.mixin.performance;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Minecraft;isWindowActive()Z"
            )
    )
    private boolean cerium_noPause(Minecraft instance) {
        return true;
    }

    @ModifyVariable(
            method = "render",
            at = @At("HEAD"),
            argsOnly = true,
            index = 4
    )
    private boolean cerium_skipMostRendering(boolean x) {
        return false;
    }

    @Redirect(
            method = "render",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/Minecraft;screen:Lnet/minecraft/client/gui/screens/Screen;"
            )
    )
    private Screen cerium_skipScreenRendering(Minecraft instance) {
        return null;
    }

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/toasts/ToastComponent;render(Lnet/minecraft/client/gui/GuiGraphics;)V"
            )
    )
    private void cerium_skipToastRendering(ToastComponent instance, GuiGraphics guiGraphics) {

    }
}
