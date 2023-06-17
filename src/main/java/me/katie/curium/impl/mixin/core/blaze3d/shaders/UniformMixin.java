package me.katie.curium.impl.mixin.core.blaze3d.shaders;

import com.mojang.blaze3d.shaders.Shader;
import com.mojang.blaze3d.shaders.Uniform;
import me.katie.curium.impl.asm.annotations.OverwriteCtor;
import me.katie.curium.impl.asm.annotations.StubClass;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = Uniform.class, remap = false)
@StubClass
public abstract class UniformMixin {
    @Mutable
    @Shadow @Final private String name;

    @Mutable
    @Shadow @Final private int count;

    @Mutable
    @Shadow @Final private int type;

    @Mutable
    @Shadow @Final private Shader parent;

    @Shadow private int location;

    @Shadow protected abstract void markDirty();

    @OverwriteCtor
    public void curium_overwriteCtor(String name, int type, int count, Shader parent) {
        this.name = name;
        this.count = count;
        this.type = type;
        this.parent = parent;
        this.location = -1;
        this.markDirty();
    }
}
