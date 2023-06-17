package me.katie.curium.impl.mixin.core.blaze3d.audio;

import com.mojang.blaze3d.audio.OggAudioStream;
import me.katie.curium.impl.asm.annotations.StubClass;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = OggAudioStream.class, remap = false)
@StubClass(
        isThrowing = true,
        skip = "<init>"
)
public class OggAudioStreamMixin {

}
