package me.katie.curium.impl.mixin.core.minecraft;

import com.mojang.realmsclient.client.RealmsClient;
import me.katie.curium.events.client.ClientStartedEvent;
import me.katie.curium.events.client.TickEvent;
import me.katie.curium.impl.CuriumConstants;
import me.katie.curium.impl.CuriumProperties;
import me.katie.curium.impl.api.CuriumImpl;
import me.katie.curium.impl.duck.CuriumStateHolder;
import me.katie.curium.impl.util.Util;
import net.minecraft.SystemReport;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.main.GameConfig;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.server.packs.resources.ReloadInstance;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements CuriumStateHolder {
    @Shadow public String fpsString;

    @Shadow private ProfilerFiller profiler;

    @Inject(
            method = "setInitialScreen",
            at = @At("HEAD"),
            cancellable = true
    )
    private void curium_joinViaSystemProperty(RealmsClient realmsClient, ReloadInstance reloadInstance, GameConfig.QuickPlayData quickPlayData, CallbackInfo ci) {
        if (CuriumProperties.SERVER_HOST == null) {
            return;
        }

        ci.cancel();

        // Parse port from system property.
        int port;

        try {
            port = Integer.parseInt(CuriumProperties.SERVER_PORT);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Curium server port was set to an invalid value", e);
        }

        // Join server by host and port.
        reloadInstance.done().thenRunAsync(() -> {
            JoinMultiplayerScreen screen = new JoinMultiplayerScreen(new TitleScreen());
            ServerAddress address = new ServerAddress(CuriumProperties.SERVER_HOST, port);
            ServerData data = new ServerData("Curium Debug", CuriumProperties.SERVER_HOST, false);

            ConnectScreen.startConnecting(screen, (Minecraft) (Object) this, address, data, true);
        }, (Minecraft) (Object) this);
    }

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
                            target = "Lcom/mojang/blaze3d/systems/RenderSystem;initBackendSystem()Lnet/minecraft/util/TimeSource$NanoTimeSource;"
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
        CuriumImpl.get().getEventBus().post(ClientStartedEvent.get());
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
    @Unique
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
        CuriumImpl.get().getEventBus().post(TickEvent.Pre.get());
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
        CuriumImpl.get().getEventBus().post(TickEvent.Post.get());
        this.profiler.pop();

        this.curium_state.tpsLimiter.postTick();
    }
}
