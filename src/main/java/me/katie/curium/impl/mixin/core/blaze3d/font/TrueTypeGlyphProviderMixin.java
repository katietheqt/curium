package me.katie.curium.impl.mixin.core.blaze3d.font;

import com.mojang.blaze3d.font.TrueTypeGlyphProvider;
import me.katie.curium.impl.asm.annotations.Erase;
import me.katie.curium.impl.asm.annotations.StubClass;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = TrueTypeGlyphProvider.class, remap = false)
@StubClass(isThrowing = true)
@Erase(
        methods = {
                "<init>(Ljava/nio/ByteBuffer;Lorg/lwjgl/stb/STBTTFontinfo;FFFFLjava/lang/String;)V",
        },
        fields = {
                "font"
        }
)
public class TrueTypeGlyphProviderMixin {

}
