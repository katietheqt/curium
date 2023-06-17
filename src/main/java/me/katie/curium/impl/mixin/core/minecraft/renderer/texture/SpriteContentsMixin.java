package me.katie.curium.impl.mixin.core.minecraft.renderer.texture;

import net.minecraft.client.renderer.texture.SpriteContents;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SpriteContents.class)
public class SpriteContentsMixin {
    @Redirect(
            method = "createAnimatedTexture",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V",
                    remap = false
            )
    )
    private void curium_removeUselessLogMessage(Logger instance, String message, Object o1, Object o2) {

    }

    @Redirect(
            method = "createAnimatedTexture",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;[Ljava/lang/Object;)V",
                    remap = false
            )
    )
    private void curium_removeUselessLogMessage2(Logger instance, String message, Object[] objects) {

    }
}
