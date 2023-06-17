package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.NativeImage;
import me.katie.curium.impl.asm.annotations.OverwriteClass;
import me.katie.curium.impl.stub.NativeImageStub;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = NativeImage.class, remap = false)
@OverwriteClass(NativeImageStub.class)
public class NativeImageMixin {

}
