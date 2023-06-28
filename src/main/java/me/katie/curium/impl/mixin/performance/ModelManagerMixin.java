package me.katie.curium.impl.mixin.performance;

import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ModelManager.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class ModelManagerMixin {
    @Overwrite
    public boolean requiresRender(BlockState blockState, BlockState blockState2) {
        return false;
    }
}
