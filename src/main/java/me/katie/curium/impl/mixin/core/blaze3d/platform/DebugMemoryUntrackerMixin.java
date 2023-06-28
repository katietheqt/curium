package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.DebugMemoryUntracker;
import me.katie.curium.impl.asm.mixin.annotations.Erase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.lang.invoke.MethodHandle;

@Mixin(DebugMemoryUntracker.class)
@Erase(
        methods = { "untrack(Lorg/lwjgl/system/Pointer;)V" }
)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class DebugMemoryUntrackerMixin {
    @Overwrite
    @SuppressWarnings("target")
    private static MethodHandle method_1408() {
        return null;
    }
}
