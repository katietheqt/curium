package me.katie.curium.impl.mixin.core.minecraft.font;

import com.mojang.blaze3d.font.GlyphProvider;
import net.minecraft.client.gui.font.providers.TrueTypeGlyphProviderDefinition;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TrueTypeGlyphProviderDefinition.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class TrueTypeGlyphProviderBuilderMixin {
    @Overwrite
    private @Nullable GlyphProvider load(ResourceManager resourceManager) {
        return null;
    }
}
