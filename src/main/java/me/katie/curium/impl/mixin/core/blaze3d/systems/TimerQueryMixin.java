package me.katie.curium.impl.mixin.core.blaze3d.systems;

import com.mojang.blaze3d.systems.TimerQuery;
import me.katie.curium.impl.asm.annotations.StubClass;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TimerQuery.class)
@StubClass(
        isThrowing = true,
        skip = "getInstance"
)
public class TimerQueryMixin {

}
