package me.katie.curium.impl.mixin.core.minecraft;

import net.minecraft.client.Options;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public class OptionsMixin {
    @Shadow public boolean onboardAccessibility;

    @Inject(
            method = "processOptions",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/client/Options;onboardAccessibility:Z",
                    shift = At.Shift.AFTER
            )
    )
    private void curium_disableAccessibilityOnboarding(Options.FieldAccess fieldAccess, CallbackInfo ci) {
        this.onboardAccessibility = false;
    }
}
