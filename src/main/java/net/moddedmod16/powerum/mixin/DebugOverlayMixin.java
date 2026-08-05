package net.moddedmod16.powerum.mixin;

import net.minecraft.client.gui.components.debug.DebugScreenEntryList;
import net.minecraft.resources.Identifier;
import net.moddedmod16.powerum.client.PowerumClientMod;
import net.moddedmod16.powerum.client.gui.config.pages.GeneralPage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.gui.components.debug.DebugScreenEntryStatus;
import java.util.Map;

@Mixin(DebugScreenEntryList.class)
public class DebugOverlayMixin {

    @Final
    @Shadow
    private Map<Identifier, DebugScreenEntryStatus> allStatuses;

    @Inject(method = "rebuildCurrentList", at = @At("HEAD"))
    private void powerum$DebugOverlay(CallbackInfo ci) {
        if (GeneralPage.DEBUG_OVERLAY){
            this.allStatuses.put(PowerumClientMod.POWERUM_DEBUG_KEY, DebugScreenEntryStatus.IN_OVERLAY);
        } else {
            this.allStatuses.remove(PowerumClientMod.POWERUM_DEBUG_KEY);
        }
    }
}
