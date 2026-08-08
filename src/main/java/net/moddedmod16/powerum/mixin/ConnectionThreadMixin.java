package net.moddedmod16.powerum.mixin;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import net.minecraft.network.Connection;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.moddedmod16.powerum.client.PowerumClientMod;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Connection.class)
public class ConnectionThreadMixin {

    @Shadow
    private Channel channel;

    @Shadow
    private PacketListener packetListener;

    @Inject(method = "send(Lnet/minecraft/network/protocol/Packet;Lio/netty/channel/ChannelFutureListener;)V", at = @At("HEAD"), cancellable = true)
    private void powerum$ConnectionMultithreading(Packet<?> packet, @Nullable ChannelFutureListener listener, CallbackInfo ci){

        if (this.channel == null || !this.channel.isOpen()){
            return;
        }

        if (!(this.packetListener instanceof ClientGamePacketListener)){
            return;
        }

        this.channel.eventLoop().execute(() -> {
                    PowerumClientMod.LAST_PACKET = packet.getClass().getSimpleName();
                    if (listener != null){
                        this.channel.writeAndFlush(packet).addListener(listener);
                    } else {
                        this.channel.writeAndFlush(packet);
                    }
        }
        );
        ci.cancel();
    }
}
