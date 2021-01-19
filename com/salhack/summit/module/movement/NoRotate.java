// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.movement;

import net.minecraft.network.play.server.SPacketPlayerPosLook;
import com.salhack.summit.events.MinecraftEvent;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.network.EventServerPacket;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Module;

public final class NoRotate extends Module
{
    @EventHandler
    private Listener<EventServerPacket> onPlayerPosLook;
    
    public NoRotate() {
        super("NoRotate", new String[] { "NoRot", "AntiRotate" }, "Prevents you from processing server rotations", "NONE", 2405083, ModuleType.MOVEMENT);
        SPacketPlayerPosLook packet;
        this.onPlayerPosLook = new Listener<EventServerPacket>(event -> {
            if (event.getStage() == MinecraftEvent.Stage.Pre && event.getPacket() instanceof SPacketPlayerPosLook) {
                packet = (SPacketPlayerPosLook)event.getPacket();
                packet.pitch = this.mc.player.rotationPitch;
                packet.yaw = this.mc.player.rotationYaw;
            }
        });
    }
}
