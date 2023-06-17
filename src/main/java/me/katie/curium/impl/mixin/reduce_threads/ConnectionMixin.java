package me.katie.curium.impl.mixin.reduce_threads;

import net.minecraft.network.Connection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Connection.class)
public class ConnectionMixin {
    @ModifyArg(
            // NETWORK_WORKER_GROUP
            method = "method_10767",
            remap = false,
            index = 0,
            at = @At(
                    value = "INVOKE",
                    target = "Lio/netty/channel/nio/NioEventLoopGroup;<init>(ILjava/util/concurrent/ThreadFactory;)V",
                    remap = false
            )
    )
    private static int curium_capNettyThreads(int nThreads) {
        return 1;
    }

    @ModifyArg(
            // NETWORK_EPOLL_WORKER_GROUP
            method = "method_10765",
            remap = false,
            index = 0,
            at = @At(
                    value = "INVOKE",
                    target = "Lio/netty/channel/epoll/EpollEventLoopGroup;<init>(ILjava/util/concurrent/ThreadFactory;)V",
                    remap = false
            )
    )
    private static int curium_capNettyThreads1(int nThreads) {
        return 1;
    }

    @ModifyArg(
            // LOCAL_WORKER_GROUP
            method = "method_10766",
            remap = false,
            index = 0,
            at = @At(
                    value = "INVOKE",
                    target = "Lio/netty/channel/DefaultEventLoopGroup;<init>(ILjava/util/concurrent/ThreadFactory;)V",
                    remap = false
            )
    )
    private static int curium_capNettyThreads2(int nThreads) {
        return 1;
    }
}
