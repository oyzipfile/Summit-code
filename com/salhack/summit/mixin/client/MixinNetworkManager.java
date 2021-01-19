// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import com.salhack.summit.events.network.EventServerPacket;
import io.netty.channel.ChannelHandlerContext;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.salhack.summit.SummitMod;
import com.salhack.summit.events.network.EventClientPacket;
import com.salhack.summit.events.MinecraftEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.Packet;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ NetworkManager.class })
public class MixinNetworkManager
{
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    private void sendPacketPre(final Packet<?> packet, final CallbackInfo info) {
        final EventClientPacket event = new EventClientPacket(packet, MinecraftEvent.Stage.Pre);
        SummitMod.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            info.cancel();
        }
    }
    
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("RETURN") })
    private void sendPacketPost(final Packet<?> packet, final CallbackInfo callbackInfo) {
        SummitMod.EVENT_BUS.post(new EventClientPacket(packet, MinecraftEvent.Stage.Post));
    }
    
    @Inject(method = { "channelRead0" }, at = { @At("HEAD") }, cancellable = true)
    private void channelReadPre(final ChannelHandlerContext context, final Packet<?> packet, final CallbackInfo info) {
        final EventServerPacket event = new EventServerPacket(packet, MinecraftEvent.Stage.Pre);
        SummitMod.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            info.cancel();
        }
    }
    
    @Inject(method = { "channelRead0" }, at = { @At("RETURN") })
    private void channelReadPost(final ChannelHandlerContext context, final Packet<?> packet, final CallbackInfo info) {
        final EventServerPacket event = new EventServerPacket(packet, MinecraftEvent.Stage.Post);
        SummitMod.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            info.cancel();
        }
    }
}
