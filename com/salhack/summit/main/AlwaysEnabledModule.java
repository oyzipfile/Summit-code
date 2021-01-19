// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.main;

import net.minecraft.client.Minecraft;
import com.salhack.summit.events.player.EventPlayerLeave;
import com.salhack.summit.events.player.EventPlayerJoin;
import com.salhack.summit.SummitMod;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.handshake.client.C00Handshake;
import com.salhack.summit.events.MinecraftEvent;
import com.salhack.summit.events.network.EventServerPacket;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.network.EventClientPacket;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.events.bus.EventListener;

public class AlwaysEnabledModule implements EventListener
{
    public static String LastIP;
    public static int LastPort;
    @EventHandler
    private Listener<EventClientPacket> onClientPacket;
    @EventHandler
    private Listener<EventServerPacket> onServerPacket;
    
    public AlwaysEnabledModule() {
        C00Handshake packet;
        this.onClientPacket = new Listener<EventClientPacket>(event -> {
            if (event.getStage() != MinecraftEvent.Stage.Pre) {
                return;
            }
            else {
                if (event.getPacket() instanceof C00Handshake) {
                    packet = (C00Handshake)event.getPacket();
                    if (packet.getRequestedState() == EnumConnectionState.LOGIN) {
                        AlwaysEnabledModule.LastIP = packet.ip;
                        AlwaysEnabledModule.LastPort = packet.port;
                    }
                }
                return;
            }
        });
        SPacketChat packet2;
        TextComponentString component;
        SPacketPlayerListItem packet3;
        Minecraft mc;
        this.onServerPacket = new Listener<EventServerPacket>(event -> {
            if (event.getStage() == MinecraftEvent.Stage.Pre) {
                try {
                    if (event.getPacket() instanceof SPacketChat) {
                        if (Wrapper.GetMC().player != null) {
                            packet2 = (SPacketChat)event.getPacket();
                            if (packet2.getChatComponent() instanceof TextComponentString) {
                                component = (TextComponentString)packet2.getChatComponent();
                                if (component.getFormattedText().toLowerCase().contains("polymer") || component.getFormattedText().toLowerCase().contains("veteranhack")) {
                                    event.cancel();
                                }
                            }
                        }
                    }
                    else if (event.getPacket() instanceof SPacketTitle) {
                        event.cancel();
                    }
                    else if (event.getPacket() instanceof SPacketPlayerListItem) {
                        packet3 = (SPacketPlayerListItem)event.getPacket();
                        mc = Wrapper.GetMC();
                        if (mc.player != null && mc.player.ticksExisted >= 1000) {
                            if (packet3.getAction() == SPacketPlayerListItem.Action.ADD_PLAYER) {
                                packet3.getEntries().forEach(playerData -> {
                                    if (playerData.getProfile().getId() != mc.session.getProfile().getId()) {
                                        SummitMod.EVENT_BUS.post(new EventPlayerJoin(playerData.getProfile().getName().toString(), playerData.getProfile().getId().toString()));
                                    }
                                    return;
                                });
                            }
                            if (packet3.getAction() == SPacketPlayerListItem.Action.REMOVE_PLAYER) {
                                packet3.getEntries().forEach(playerData -> {
                                    if (playerData.getProfile().getId() != mc.session.getProfile().getId()) {
                                        SummitMod.EVENT_BUS.post(new EventPlayerLeave(playerData.getProfile().getName().toString(), playerData.getProfile().getId().toString()));
                                    }
                                });
                            }
                        }
                    }
                }
                catch (Exception ex) {}
            }
        });
    }
    
    public void init() {
        SummitMod.EVENT_BUS.subscribe(this);
    }
    
    static {
        AlwaysEnabledModule.LastIP = null;
        AlwaysEnabledModule.LastPort = -1;
    }
}
