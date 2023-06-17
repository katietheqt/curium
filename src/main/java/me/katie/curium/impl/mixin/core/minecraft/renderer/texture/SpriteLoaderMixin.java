package me.katie.curium.impl.mixin.core.minecraft.renderer.texture;

import net.minecraft.client.renderer.texture.SpriteLoader;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SpriteLoader.class)
public class SpriteLoaderMixin {
    @Redirect(
            method = "stitch",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;[Ljava/lang/Object;)V",
                    remap = false
            )
    )
    private void curium_removeUselessLogMessage(Logger instance, String message, Object[] objects) {

    }

    @Redirect(
            method = "loadSprite",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V",
                    remap = false
            )
    )
    private static void curium_removeUselessLogMessage2(Logger instance, String message, Object o1, Object o2) {

    }

    @Redirect(
            method = "loadSprite",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;error(Ljava/lang/String;[Ljava/lang/Object;)V",
                    remap = false
            )
    )
    private static void curium_removeUselessLogMessage3(Logger instance, String message, Object[] objects) {

    }
}
