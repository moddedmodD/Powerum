package net.moddedmod16.powerum.mixin;

import net.minecraft.server.packs.resources.SimpleReloadInstance;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ReloadInstance;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Unit;
import net.moddedmod16.powerum.client.cpu.ThreadCreator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(SimpleReloadInstance.class)
public class SimpleResourceReloadMixin {

    @Inject(method = "create", at = @At("HEAD"), cancellable = true)
    private static void powerum$OverrideAndOptimize(ResourceManager resourceManager, List<PreparableReloadListener> listeners, Executor backgroundExecutor, Executor mainThreadExecutor, CompletableFuture<Unit> initialTask, boolean enableProfiling, CallbackInfoReturnable<ReloadInstance> cir){

        Executor parallelBackground = ThreadCreator::submitTask;

        cir.setReturnValue(SimpleReloadInstance.of(
                resourceManager,
                listeners,
                parallelBackground,
                mainThreadExecutor,
                initialTask
        ));
    }
}
