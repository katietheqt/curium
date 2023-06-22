package me.katie.curium.impl.mixin.core.minecraft.renderer.culling;

import net.minecraft.client.renderer.culling.Frustum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Frustum.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class FrustumMixin {
    @Overwrite
    public Frustum offsetToFullyIncludeCameraCube(int i) {
        return (Frustum) (Object) this;
    }
}
