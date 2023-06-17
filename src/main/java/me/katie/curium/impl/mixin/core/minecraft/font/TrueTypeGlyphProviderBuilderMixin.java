package me.katie.curium.impl.mixin.core.minecraft.font;

import com.mojang.blaze3d.font.GlyphProvider;
import net.minecraft.client.gui.font.providers.TrueTypeGlyphProviderBuilder;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TrueTypeGlyphProviderBuilder.class)
@SuppressWarnings("overwrite")
public class TrueTypeGlyphProviderBuilderMixin {
    @Overwrite
    public @Nullable GlyphProvider create(ResourceManager resourceManager) {
        return null;
    }
}
