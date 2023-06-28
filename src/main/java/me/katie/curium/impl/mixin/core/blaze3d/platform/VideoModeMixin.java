package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.VideoMode;
import me.katie.curium.impl.asm.mixin.annotations.Erase;
import me.katie.curium.impl.asm.mixin.annotations.StubClass;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(VideoMode.class)
@StubClass(isThrowing = true)
@Erase(methods = {
        "<init>(Lorg/lwjgl/glfw/GLFWVidMode$Buffer;)V",
        "<init>(Lorg/lwjgl/glfw/GLFWVidMode;)V"
})
public class VideoModeMixin {

}
