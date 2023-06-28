package me.katie.curium.impl.mixin.core.blaze3d.platform;

import com.mojang.blaze3d.platform.InputConstants;
import me.katie.curium.impl.api.CuriumImpl;
import me.katie.curium.impl.asm.mixin.annotations.CustomTransformer;
import me.katie.curium.impl.asm.mixin.annotations.Erase;
import me.katie.curium.impl.asm.mixin.transformers.StripLwjglLdcTransformer;
import me.katie.curium.impl.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

@Mixin(InputConstants.class)
@Erase(methods = {
        "setupKeyboardCallbacks(JLorg/lwjgl/glfw/GLFWKeyCallbackI;Lorg/lwjgl/glfw/GLFWCharModsCallbackI;)V",
        "setupMouseCallbacks(JLorg/lwjgl/glfw/GLFWCursorPosCallbackI;Lorg/lwjgl/glfw/GLFWMouseButtonCallbackI;Lorg/lwjgl/glfw/GLFWScrollCallbackI;Lorg/lwjgl/glfw/GLFWDropCallbackI;)V",
})
@CustomTransformer(StripLwjglLdcTransformer.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class InputConstantsMixin {
    @Overwrite
    public static boolean isKeyDown(long window, int key) {
        return CuriumImpl.get().fakeKeyboard.isPressed(key);
    }

    @Overwrite
    public static void grabOrReleaseMouse(long l, int i, double d, double e) {

    }

    @Overwrite
    public static boolean isRawMouseInputSupported() {
        return false;
    }

    @Overwrite
    public static void updateRawMouseInput(long l, boolean bl) {
        Util.stubbed();
    }

    // MCDev isn't happy with this injector, but it works.
    @SuppressWarnings({"MixinAnnotationTarget", "InvalidInjectorMethodSignature", "UnresolvedMixinReference"})
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/invoke/MethodHandles$Lookup;findStatic(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/MethodHandle;",
                    remap = false
            )
    )
    private static MethodHandle curium_dontFindGlfwMethodHandle(MethodHandles.Lookup instance, Class<?> clazz, String name, MethodType type) throws NoSuchMethodException {
        throw new NoSuchMethodException(name);
    }
}
