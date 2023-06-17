package me.katie.curium.impl.mixin.core.blaze3d.systems;

import com.mojang.blaze3d.systems.TimerQuery;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TimerQuery.TimerQueryLazyLoader.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class TimerQuery_TimerQueryLazyLoaderMixin {
    @Overwrite
    private static @Nullable TimerQuery instantiate() {
        return null;
    }
}
