package me.katie.curium.impl.mixin.core.blaze3d.vertex;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.VertexFormat;
import it.unimi.dsi.fastutil.ints.IntConsumer;
import me.katie.curium.impl.asm.annotations.OverwriteCtor;
import org.joml.Vector3f;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.nio.ByteBuffer;

@Mixin(value = BufferBuilder.class, remap = false)
@SuppressWarnings("overwrite")
public class BufferBuilderMixin {
    @Shadow
    private ByteBuffer buffer;

    @Shadow private boolean building;

    @OverwriteCtor
    public void curium_overwriteCtor(int i) {
        this.buffer = null;
        this.building = false;
    }

    @Overwrite
    private void ensureCapacity(int i) {

    }

    @Redirect(
            method = {
                    "restoreSortState", "begin"
            },
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/nio/ByteBuffer;rewind()Ljava/nio/ByteBuffer;"
            )
    )
    private ByteBuffer curium_rewindNop(ByteBuffer instance) {
        return instance;
    }


    @Redirect(
            method = "endVertex",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/nio/ByteBuffer;put(ILjava/nio/ByteBuffer;II)Ljava/nio/ByteBuffer;"
            )
    )
    private ByteBuffer curium_putNop(ByteBuffer instance, int index, ByteBuffer src, int offset, int length) {
        return instance;
    }

    @Redirect(
            method = "begin",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.GETFIELD,
                    target = "Lcom/mojang/blaze3d/vertex/BufferBuilder;building:Z"
            )
    )
    private boolean curium_neverThrowAlreadyBuilding(BufferBuilder instance) {
        return false;
    }

    @Overwrite
    private IntConsumer intConsumer(int i2, VertexFormat.IndexType indexType) {
        return _x -> {};
    }

    @Overwrite
    private Vector3f[] makeQuadSortingPoints() {
        return new Vector3f[] {};
    }

    @Overwrite
    public void putByte(int i, byte b) {

    }

    @Overwrite
    public void putShort(int i, short s) {

    }

    @Overwrite
    public void putFloat(int i, float f) {

    }

    @Overwrite
    public ByteBuffer bufferSlice(int i, int j) {
        return this.buffer;
    }
}
