// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.salhack.summit.events.world.EventChunkLoad;
import com.salhack.summit.SummitMod;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.play.server.SPacketChunkData;
import org.spongepowered.asm.mixin.Final;
import net.minecraft.network.NetworkManager;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ NetHandlerPlayClient.class })
public abstract class MixinNetHandlerPlayClient
{
    @Shadow
    private WorldClient world;
    @Shadow
    private Minecraft client;
    @Shadow
    private boolean doneLoadingTerrain;
    @Shadow
    @Final
    private NetworkManager netManager;
    @Shadow
    public int currentServerMaxPlayers;
    
    @Inject(method = { "handleChunkData" }, at = { @At("RETURN") })
    public void handleChunkData(final SPacketChunkData packet, final CallbackInfo info) {
        SummitMod.EVENT_BUS.post(new EventChunkLoad(EventChunkLoad.Type.LOAD, this.world.getChunk(packet.getChunkX(), packet.getChunkZ())));
    }
}
