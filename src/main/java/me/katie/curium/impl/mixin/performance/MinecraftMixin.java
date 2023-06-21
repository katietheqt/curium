package me.katie.curium.impl.mixin.performance;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.main.GameConfig;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.profiling.ProfileResults;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void curium_onInit(GameConfig gameConfig, CallbackInfo ci) {
        System.gc();
    }

    @Redirect(
            method = "runTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Minecraft;renderFpsMeter(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/util/profiling/ProfileResults;)V"
            )
    )
    private void curium_neverRenderFpsMeter(Minecraft instance, GuiGraphics guiGraphics, ProfileResults profileResults) {

    }

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/ClientLevel;animateTick(III)V"
            )
    )
    private void curium_skipAnimateTick(ClientLevel instance, int i, int j, int k) {

    }

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/GameRenderer;tick()V"
            )
    )
    private void curium_neverTickGameRenderer(GameRenderer instance) {

    }
}
