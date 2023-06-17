package me.katie.curium.impl.mixin.core.realms;


import com.mojang.realmsclient.util.RealmsTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RealmsTextureManager.TextureData.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class RealmsTextureManager_TextureDataMixin {
    @Overwrite
    @SuppressWarnings("target")
    private static RealmsTextureManager.TextureData method_38509() {
        return null;
    }

    @Overwrite
    public static RealmsTextureManager.TextureData load(String string) {
        return null;
    }
}
