package me.katie.curium.impl.mixin.reduce_threads;

import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * DFU fails when running on a direct executor.
 * <p>
 * Copied from <a href="https://github.com/astei/lazydfu/blob/d3612253d46d1cba39b22f9af0226fb7af3a584d/src/main/java/me/steinborn/lazydfu/mixin/SharedConstantsMixin.java">LazyDFU</a>,
 * licensed under MIT.
 */
@Mixin(SharedConstants.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class SharedConstantsMixin {
    @Overwrite
    public static void enableDataFixerOptimizations() {

    }
}
