package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.PngInfo;
import me.katie.curium.impl.asm.annotations.StubClass;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PngInfo.StbReaderSeekableByteChannel.class)
@StubClass(isThrowing = true)
public class PngInfo_StbReaderSeekableByteChannelMixin {

}
