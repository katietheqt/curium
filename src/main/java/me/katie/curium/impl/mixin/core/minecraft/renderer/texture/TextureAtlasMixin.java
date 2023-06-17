package me.katie.curium.impl.mixin.core.minecraft.renderer.texture;

import net.minecraft.client.renderer.texture.TextureAtlas;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TextureAtlas.class)
public class TextureAtlasMixin {
    @Redirect(
            method = "upload",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;info(Ljava/lang/String;[Ljava/lang/Object;)V",
                    remap = false,
                    ordinal = 0
            )
    )
    private void curium_removeUselessLogMessage(Logger instance, String message, Object[] objects) {

    }
}
