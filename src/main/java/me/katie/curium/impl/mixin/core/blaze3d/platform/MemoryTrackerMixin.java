package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.MemoryTracker;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.nio.ByteBuffer;

@Mixin(value = MemoryTracker.class, remap = false)
@SuppressWarnings("overwrite")
public class MemoryTrackerMixin {
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/system/MemoryUtil;getAllocator(Z)Lorg/lwjgl/system/MemoryUtil$MemoryAllocator;",
                    remap = false
            )
    )
    private static MemoryUtil.MemoryAllocator curium_noAllocate(boolean tracked) {
        return null;
    }

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
