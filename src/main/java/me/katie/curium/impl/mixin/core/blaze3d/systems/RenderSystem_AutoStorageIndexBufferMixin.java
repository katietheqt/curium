package me.katie.curium.impl.mixin.core.blaze3d.systems;

import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RenderSystem.AutoStorageIndexBuffer.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class RenderSystem_AutoStorageIndexBufferMixin {
    @Overwrite
    public boolean hasStorage(int i) {
        return true;
    }

    @Overwrite
    public void bind(int i) {

    }

    @Overwrite
    private void ensureStorage(int i) {
    }
}
