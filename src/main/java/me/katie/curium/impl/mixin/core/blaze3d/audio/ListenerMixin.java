package me.katie.curium.impl.mixin.core.blaze3d.audio;

import com.mojang.blaze3d.audio.Listener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Listener.class, remap = false)
public class ListenerMixin {
    @Redirect(
            method = "setListenerPosition",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/openal/AL10;alListener3f(IFFF)V"
            )
    )
    private void curium_nopSetListenerPosition(int paramName, float value1, float value2, float value3) {

    }

    @Redirect(
            method = "setListenerOrientation",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/openal/AL10;alListenerfv(I[F)V"
            )
    )
    private void curium_nopSetListenerOrientation(int paramName, float[] values) {

    }

    @Redirect(
            method = "setGain",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/openal/AL10;alListenerf(IF)V"
            )
    )
    private void curium_nopSetGain(int paramName, float value) {

    }


}
