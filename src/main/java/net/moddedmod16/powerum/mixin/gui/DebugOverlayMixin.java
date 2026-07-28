package net.moddedmod16.powerum.mixin.gui;

import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.moddedmod16.powerum.client.gui.integration.debug.DebugOverlay;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.Minecraft;

@Mixin(DebugScreenOverlay.class)
public class DebugOverlayMixin {

    @Inject(method = "extractRenderState", at = @At("RETURN"))
    private void powerumCustomOverlay(GuiGraphicsExtractor graphics, CallbackInfo ci){
        Minecraft client = Minecraft.getInstance();

        if (client.getDebugOverlay().showDebugScreen() && client.player != null){
            DebugOverlay.render(graphics, client);
        }
    }
}
