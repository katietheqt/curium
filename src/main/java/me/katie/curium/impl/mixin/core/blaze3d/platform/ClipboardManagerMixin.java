package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.ClipboardManager;
import me.katie.curium.impl.asm.annotations.OverwriteClass;
import me.katie.curium.impl.stub.ClipboardManagerStub;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ClipboardManager.class, remap = false)
@OverwriteClass(value = ClipboardManagerStub.class, verify = false)
public class ClipboardManagerMixin {

}
