// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.misc;

import net.minecraft.util.EnumFacing;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import com.salhack.summit.events.MinecraftEvent;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.network.EventClientPacket;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Module;

public final class BuildHeight extends Module
{
    @EventHandler
    private Listener<EventClientPacket> onClientPacket;
    
    public BuildHeight() {
        super("BuildHeight", new String[] { "BuildH", "BHeight" }, "Allows you to interact with blocks at build height", "NONE", 14361709, ModuleType.MISC);
        CPacketPlayerTryUseItemOnBlock packet;
        this.onClientPacket = new Listener<EventClientPacket>(event -> {
            if (event.getStage() == MinecraftEvent.Stage.Pre) {
                if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
                    packet = (CPacketPlayerTryUseItemOnBlock)event.getPacket();
                    if (packet.getPos().getY() >= 255 && packet.getDirection() == EnumFacing.UP) {
                        packet.placedBlockDirection = EnumFacing.DOWN;
                    }
                }
            }
        });
    }
}
