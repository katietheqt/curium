package me.katie.curium.impl.mixin.core.minecraft;

import me.katie.curium.Curium;
import me.katie.curium.events.ClipboardEvent;
import net.minecraft.client.KeyboardHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(KeyboardHandler.class)
@SuppressWarnings("overwrite")
public class KeyboardHandlerMixin {
    @Overwrite
    public String getClipboard() {
        ClipboardEvent.Get event = Curium.get().getEventBus().post(ClipboardEvent.Get.get());
        return event.contents;
    }

    @Overwrite
    public void setClipboard(String contents) {
        Curium.get().getEventBus().post(ClipboardEvent.Set.get(contents));
    }

    @Overwrite
    public void setup(long l2) {

    }
}
