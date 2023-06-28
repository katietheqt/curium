package me.katie.curium.impl.mixin.core.minecraft;

import me.katie.curium.events.ClipboardEvent;
import me.katie.curium.impl.api.CuriumImpl;
import net.minecraft.client.KeyboardHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(KeyboardHandler.class)
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class KeyboardHandlerMixin {
    @Overwrite
    public String getClipboard() {
        ClipboardEvent.Get event = CuriumImpl.get().getEventBus().post(ClipboardEvent.Get.get());
        return event.contents;
    }

    @Overwrite
    public void setClipboard(String contents) {
        CuriumImpl.get().getEventBus().post(ClipboardEvent.Set.get(contents));
    }

    @Overwrite
    public void setup(long l2) {

    }
}
