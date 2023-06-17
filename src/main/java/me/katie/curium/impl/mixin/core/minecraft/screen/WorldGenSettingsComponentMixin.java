package me.katie.curium.impl.mixin.core.minecraft.screen;

import me.katie.curium.impl.util.Util;
import net.minecraft.client.gui.screens.worldselection.WorldGenSettingsComponent;
import org.lwjgl.PointerBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldGenSettingsComponent.class)
public class WorldGenSettingsComponentMixin {
    @Redirect(
            method = "method_29071",
            remap = false,
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/util/tinyfd/TinyFileDialogs;tinyfd_openFileDialog(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Lorg/lwjgl/PointerBuffer;Ljava/lang/CharSequence;Z)Ljava/lang/String;",
                    remap = false
            )
    )
    private String curium_nopOpenFileDialog(CharSequence aTitleEncoded, CharSequence aDefaultPathAndFileEncoded, PointerBuffer aSingleFilterDescriptionEncoded, CharSequence __result, boolean aTitle) {
        return Util.stubbed();
    }
}
