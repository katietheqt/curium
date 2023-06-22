package me.katie.curium.impl.mixin.core.blaze3d.audio;

import com.mojang.blaze3d.audio.SoundBuffer;
import me.katie.curium.impl.asm.annotations.StubClass;
import me.katie.curium.impl.util.Util;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.sounds.AudioStream;
import net.minecraft.client.sounds.SoundBufferLibrary;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Mixin(SoundBufferLibrary.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
@StubClass
public class SoundBufferLibraryMixin {
    @Overwrite
    public CompletableFuture<SoundBuffer> getCompleteBuffer(ResourceLocation resourceLocation) {
        return Util.stubbedFuture();
    }

    @Overwrite
    public CompletableFuture<AudioStream> getStream(ResourceLocation resourceLocation, boolean bl) {
        return Util.stubbedFuture();
    }

    @Overwrite
    public CompletableFuture<?> preload(Collection<Sound> collection) {
        return CompletableFuture.completedFuture(null);
    }
}
