package net.moddedmod16.powerum.mixin.culling;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityCullingMixin {

    @Inject(method = "submit", at = @At("HEAD"), cancellable = true)
    private void powerum$EntityCulling(EntityRenderState renderState, CameraRenderState camera, double x, double y, double z, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CallbackInfo ci){

        Minecraft client = Minecraft.getInstance();

        if (client != null && client.player != null){
            Vec3 lookVector = client.player.getLookAngle();

            Vec3 targetVector = new Vec3(x, y, z).normalize();

            double dotProduct = lookVector.dot(targetVector);

            if (dotProduct < 0.0){
                ci.cancel();
            }
        }
    }
}
