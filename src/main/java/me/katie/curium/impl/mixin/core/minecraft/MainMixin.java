package me.katie.curium.impl.mixin.core.minecraft;

import net.minecraft.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Main.class)
public class MainMixin {
    @Redirect(
            method = "main",
            remap = false,
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Thread;setName(Ljava/lang/String;)V",
                    remap = false
            )
    )
    private static void curium_overwriteThreadName(Thread instance, String name) {
        instance.setName("Client Thread");
    }
}