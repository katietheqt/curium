package me.katie.curium.impl.mixin.performance;

import me.katie.curium.impl.asm.mixin.annotations.StubClass;
import net.minecraft.client.particle.ParticleEngine;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ParticleEngine.class)
@StubClass(skip = { "<init>", "<clinit>" })
public class ParticleEngineMixin {

}
