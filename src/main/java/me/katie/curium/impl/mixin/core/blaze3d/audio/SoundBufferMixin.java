package me.katie.curium.impl.mixin.core.blaze3d.audio;

import com.mojang.blaze3d.audio.SoundBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.OptionalInt;

@Mixin(SoundBuffer.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class SoundBufferMixin {
    @Shadow private boolean hasAlBuffer;

    @Overwrite
    public OptionalInt getAlBuffer() {
        return OptionalInt.empty();
    }

    @Overwrite
    public void discardAlBuffer() {
        this.hasAlBuffer = false;
    }
}
