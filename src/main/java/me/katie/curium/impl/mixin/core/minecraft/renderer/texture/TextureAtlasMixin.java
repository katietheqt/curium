package me.katie.curium.impl.mixin.core.minecraft.renderer.texture;

import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Queue;

@Mixin(TextureAtlas.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class TextureAtlasMixin {
    @Overwrite
    private void method_18160(ResourceLocation location, ResourceManager manager, Queue<TextureAtlasSprite.Info> queue) {

    }
}
