package me.katie.curium.impl.mixin.reduce_threads;

import com.google.common.util.concurrent.MoreExecutors;
import net.minecraft.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.concurrent.ExecutorService;

@Mixin(Util.class)
@SuppressWarnings("overwrite")
public class UtilMixin {
    @Overwrite
    private static ExecutorService makeExecutor(String name) {
        return MoreExecutors.newDirectExecutorService();
    }

    @Overwrite
    private static ExecutorService makeIoExecutor() {
        return MoreExecutors.newDirectExecutorService();
    }
}
