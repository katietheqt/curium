package me.katie.curium.impl.mixin.core.blaze3d.audio;

import com.mojang.blaze3d.audio.Channel;
import me.katie.curium.impl.asm.mixin.annotations.StubClass;
import me.katie.curium.impl.util.Util;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Channel.class)
@StubClass
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class ChannelMixin {
    @Overwrite
    private int getState() {
        return 4116;
    }

    @Overwrite
    @SuppressWarnings("DataFlowIssue") // IntelliJ complains if the nullable is there
    public static @Nullable Channel create() {
        return new Channel(-1);
    }

    @Overwrite
    private int removeProcessedBuffers() {
        return Util.stubbed();
    }
}
