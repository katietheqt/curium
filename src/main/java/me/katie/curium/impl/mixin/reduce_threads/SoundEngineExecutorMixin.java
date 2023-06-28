package me.katie.curium.impl.mixin.reduce_threads;

import me.katie.curium.impl.asm.mixin.annotations.Erase;
import me.katie.curium.impl.util.Util;
import net.minecraft.client.sounds.SoundEngineExecutor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SoundEngineExecutor.class)
@Erase(
        methods = { "createThread" }
)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class SoundEngineExecutorMixin {
    @Shadow private volatile boolean shutdown;

    @Redirect(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/sounds/SoundEngineExecutor;createThread()Ljava/lang/Thread;"
            )
    )
    private Thread cerium_removeSoundEngineThread(SoundEngineExecutor instance) {
        this.shutdown = true;
        return null;
    }

    @Overwrite
    public void flush() {

    }

    @Overwrite
    public void waitForTasks() {

    }

    @Overwrite
    private void run() {
        Util.stubbed();
    }
}
