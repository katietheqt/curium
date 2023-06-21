package me.katie.curium.impl.mixin.performance;

import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameRenderer.class)
@SuppressWarnings({"overwrite"})
public class GameRendererMixin {
    @Shadow @Final
    Minecraft minecraft;

    /*@Overwrite
    public void render(float f, long l, boolean bl) {
        // Overlay rendering has side effects...
        GuiGraphics guiGraphics = new GuiGraphics(this.minecraft, this.minecraft.renderBuffers().bufferSource());
        int i = (int)(this.minecraft.mouseHandler.xpos() * (double)this.minecraft.getWindow().getGuiScaledWidth() / (double)this.minecraft.getWindow().getScreenWidth());
        int j = (int)(this.minecraft.mouseHandler.ypos() * (double)this.minecraft.getWindow().getGuiScaledHeight() / (double)this.minecraft.getWindow().getScreenHeight());

        if (this.minecraft.getOverlay() != null) {
            try {
                this.minecraft.getOverlay().render(guiGraphics, i, j, this.minecraft.getDeltaFrameTime());
            } catch (Throwable throwable) {
                CrashReport crashReport = CrashReport.forThrowable(throwable, "Rendering overlay");
                CrashReportCategory crashReportCategory = crashReport.addCategory("Overlay render details");
                crashReportCategory.setDetail("Overlay name", () -> this.minecraft.getOverlay().getClass().getCanonicalName());
                throw new ReportedException(crashReport);
            }
        }
    }*/
}
