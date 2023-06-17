package me.katie.curium.impl.mixin.core.blaze3d.vertex;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = VertexConsumer.class, remap = false)
@SuppressWarnings("overwrite")
public interface VertexConsumerMixin {
    @Overwrite
    default void putBulkData(PoseStack.Pose pose, BakedQuad bakedQuad, float[] fs, float f, float g, float h, int[] is, int i, boolean bl) {

    }
}
