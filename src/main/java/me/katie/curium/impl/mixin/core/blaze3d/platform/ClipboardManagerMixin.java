package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.ClipboardManager;
import me.katie.curium.impl.asm.annotations.OverwriteCtor;
import me.katie.curium.impl.asm.annotations.StubClass;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClipboardManager.class)
@StubClass(
        isThrowing = true,
        skip = "<init>"
)
public class ClipboardManagerMixin {
    @OverwriteCtor
    private void curium_overwriteCtor() {

    }
}
