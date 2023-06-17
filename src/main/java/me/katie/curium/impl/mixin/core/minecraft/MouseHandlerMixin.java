package me.katie.curium.impl.mixin.core.minecraft;

import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MouseHandler.class)
@SuppressWarnings("overwrite")
public class MouseHandlerMixin {
    @Overwrite
    public void setup(long l2) {

    }
}
