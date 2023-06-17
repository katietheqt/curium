package me.katie.curium.impl.mixin.core.blaze3d.systems;

import com.mojang.blaze3d.systems.TimerQuery;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = TimerQuery.TimerQueryLazyLoader.class, remap = false)
@SuppressWarnings("overwrite")
public class TimerQuery_TimerQueryLazyLoaderMixin {
    @Overwrite
    private static @Nullable TimerQuery instantiate() {
        return null;
    }
}
