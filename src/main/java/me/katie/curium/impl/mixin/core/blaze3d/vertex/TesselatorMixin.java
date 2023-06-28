package me.katie.curium.impl.mixin.core.blaze3d.vertex;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.Tesselator;
import me.katie.curium.impl.asm.mixin.annotations.OverwriteCtor;
import me.katie.curium.impl.asm.mixin.annotations.StubClass;
import org.spongepowered.asm.mixin.*;

@Mixin(Tesselator.class)
@StubClass(skip = "<clinit>")
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class TesselatorMixin {
    @Mutable
    @Shadow @Final private BufferBuilder builder;

    @OverwriteCtor
    public void curium_overwriteCtor(int i) {
        this.builder = new BufferBuilder(-1);
    }

    @Overwrite
    public BufferBuilder getBuilder() {
        return this.builder;
    }
}
