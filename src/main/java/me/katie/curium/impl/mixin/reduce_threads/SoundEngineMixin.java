package me.katie.curium.impl.mixin.reduce_threads;

import me.katie.curium.impl.CuriumConstants;
import me.katie.curium.impl.asm.annotations.OverwriteCtor;
import me.katie.curium.impl.asm.annotations.StubClass;
import net.minecraft.client.Options;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Collections;
import java.util.List;

@Mixin(SoundEngine.class)
@StubClass
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class SoundEngineMixin {
    @OverwriteCtor
    public void curium_overwriteCtor(SoundManager soundManager, Options options, ResourceManager resourceManager) {

    }

    @Overwrite
    public boolean isActive(SoundInstance soundInstance) {
        return false;
    }

    @Overwrite
    public String getDebugString() {
        return CuriumConstants.USING_CURIUM;
    }

    @Overwrite
    public List<String> getAvailableSoundDevices() {
        return Collections.emptyList();
    }
}
