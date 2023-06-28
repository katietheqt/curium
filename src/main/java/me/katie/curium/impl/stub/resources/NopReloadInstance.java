package me.katie.curium.impl.stub.resources;

import net.minecraft.server.packs.resources.ReloadInstance;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class NopReloadInstance implements ReloadInstance {
    public static final ReloadInstance INSTANCE = new NopReloadInstance();

    private NopReloadInstance() {

    }

    @Override
    public @NotNull CompletableFuture<?> done() {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public float getActualProgress() {
        return 1.0f;
    }
}
