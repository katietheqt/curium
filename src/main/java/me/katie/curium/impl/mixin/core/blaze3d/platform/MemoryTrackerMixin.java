package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.MemoryTracker;
import me.katie.curium.impl.asm.annotations.Erase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.nio.ByteBuffer;

@Mixin(MemoryTracker.class)
@Erase(methods = "<clinit>")
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class MemoryTrackerMixin {
    @Overwrite
    public static ByteBuffer create(int size) {
        return ByteBuffer.allocate(size);
    }

    @Overwrite
    public static ByteBuffer resize(ByteBuffer buffer, int size) {
        ByteBuffer newBuffer = ByteBuffer.allocate(size);

        assert buffer.hasArray();
        assert newBuffer.hasArray();
        System.arraycopy(buffer.array(), 0, newBuffer.array(), 0, buffer.capacity());

        return newBuffer;
    }
}
