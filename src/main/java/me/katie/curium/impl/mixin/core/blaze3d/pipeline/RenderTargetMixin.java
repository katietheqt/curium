package me.katie.curium.impl.mixin.core.blaze3d.pipeline;

import com.mojang.blaze3d.pipeline.RenderTarget;
import me.katie.curium.impl.asm.annotations.StubClass;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = RenderTarget.class, remap = false)
@StubClass
public class RenderTargetMixin {

}
