package me.katie.curium.impl.mixin.core.minecraft.renderer;

import com.mojang.blaze3d.platform.DisplayData;
import com.mojang.blaze3d.platform.Window;
import me.katie.curium.impl.asm.annotations.OverwriteCtor;
import me.katie.curium.impl.asm.annotations.StubClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.VirtualScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;

@Mixin(VirtualScreen.class)
@StubClass
@SuppressWarnings({"overwrite", "OverwriteAuthorRequired"})
public class VirtualScreenMixin {
    @Mutable
    @Shadow @Final private Minecraft minecraft;

    @OverwriteCtor
    public void curium_overwriteCtor(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    @Overwrite
    public Window newWindow(DisplayData displayData, @Nullable String string, String string2) {
        // Window constructor is overwritten.
        return new Window(null, null, null, null, null);
    }
}
