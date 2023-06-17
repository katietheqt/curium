package me.katie.curium.impl.mixin.core.minecraft;

import me.katie.curium.Curium;
import me.katie.curium.impl.CuriumConstants;
import me.katie.curium.impl.duck.CuriumStateHolder;
import me.katie.curium.events.client.ClientStartedEvent;
import me.katie.curium.events.client.TickEvent;
import me.katie.curium.impl.api.CuriumImpl;
import me.katie.curium.impl.util.Util;
import net.minecraft.SystemReport;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements CuriumStateHolder {
    @Shadow public String fpsString;

    @Shadow private ProfilerFiller profiler;

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void curium_postInit(GameConfig gameConfig, CallbackInfo ci) {
        this.fpsString = CuriumConstants.USING_CURIUM;
    }

    @Redirect(
            method = "fillSystemReport",
            require = 0,
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/SystemReport;setDetail(Ljava/lang/String;Ljava/util/function/Supplier;)V"
            )
    )
    private static void curium_editSystemReport(SystemReport instance, String key, Supplier<String> supplier) {
        // Removes useless sections from the crash report.
        if (!CuriumConstants.OMITTED_SYSTEM_REPORT_SECTIONS.contains(key)) {
            instance.setDetail(key, supplier);
        }
    }

    @Redirect(
            method = "<init>",
            slice = @Slice(
                    from = @At(
                            value = "FIELD",
                            opcode = Opcodes.PUTFIELD,
                            target = "Lnet/minecraft/client/Minecraft;hotbarManager:Lnet/minecraft/client/HotbarManager;"
                    ),
                    to = @At(
                            value = "INVOKE",
                            target = "Lcom/mojang/blaze3d/systems/RenderSystem;initBackendSystem()Lnet/minecraft/util/TimeSource$NanoTimeSource;",
                            remap = false
                    )
            ),
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;info(Ljava/lang/String;Ljava/lang/Object;)V",
                    remap = false
            )
    )
    private void curium_removeBackendLibraryLog(Logger instance, String msg, Object o) {

    }

    @Redirect(
            method = "runTick",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Thread;yield()V"
            )
    )
    private void curium_removeThreadYield() {

    }

    @Inject(
            method = "run",
            at = @At("HEAD")
    )
    private void curium_onGameRunning(CallbackInfo ci) {
        Curium.get().getEventBus().post(ClientStartedEvent.get());
    }

    @Redirect(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/util/tinyfd/TinyFileDialogs;tinyfd_messageBox(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Z)Z",
                    remap = false
            )
    )
    private boolean curium_nopMessageBox(CharSequence aTitleEncoded, CharSequence aMessageEncoded, CharSequence aDialogTypeEncoded, CharSequence aIconTypeEncoded, boolean aTitle) {
        return Util.stubbed();
    }

    @Redirect(
            method = "run",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Thread;setPriority(I)V",
                    remap = false
            )
    )
    private void curium_nopSetPriority(Thread instance, int newPriority) {

    }

    @Redirect(
            method = "runTick",
            slice = @Slice(
                    from = @At(
                            value = "FIELD",
                            opcode = Opcodes.PUTFIELD,
                            target = "Lnet/minecraft/client/Minecraft;gpuUtilization:D"
                    )
            ),
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/Util;getMillis()J"
            )
    )
    private long curium_removeBusyLoop() {
        // This loop usually only executes a few times but because we don't sleep in this method it runs a *lot*.
        return Long.MIN_VALUE;
    }


    // State associated with this client.
    private final CuriumImpl curium_state = new CuriumImpl();

    @Override
    @Unique
    public @NotNull CuriumImpl curium_getState() {
        return this.curium_state;
    }

    // These injectors cap the client tick rate to avoid what is effectively a busy-loop.
    @Inject(
            method = "run",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/profiling/SingleTickProfiler;createTickProfiler(Ljava/lang/String;)Lnet/minecraft/util/profiling/SingleTickProfiler;",
                    shift = At.Shift.BEFORE
            )
    )
    private void curium_preClientTick(CallbackInfo ci) {
        this.curium_state.tpsLimiter.preTick();

        this.profiler.push("curium_pre");
        Curium.get().getEventBus().post(TickEvent.Pre.get());
        this.profiler.pop();
    }

    @Inject(
            method = "run",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Minecraft;finishProfilers(ZLnet/minecraft/util/profiling/SingleTickProfiler;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void curium_postClientTick(CallbackInfo ci) {
        this.profiler.push("curium_post");
        Curium.get().getEventBus().post(TickEvent.Post.get());
        this.profiler.pop();

        this.curium_state.tpsLimiter.postTick();
    }
}
