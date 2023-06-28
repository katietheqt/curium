package me.katie.curium.impl.mixin.core.blaze3d.systems;

import com.mojang.blaze3d.systems.TimerQuery;
import me.katie.curium.impl.asm.mixin.annotations.StubClass;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TimerQuery.FrameProfile.class)
@StubClass(isThrowing = true)
public class TimerQuery_FrameProfileMixin {
}
