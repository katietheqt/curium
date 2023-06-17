package me.katie.curium.impl.mixin.core.minecraft;

import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MouseHandler.class)
public interface MouseHandlerAccessor {
    @Invoker
    void callOnPress(long l, int i, int j, int k);

    @Invoker
    void callOnScroll(long l, double d, double e);

    @Invoker
    void callOnMove(long l, double d, double e);
}
