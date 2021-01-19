// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.misc;

import java.io.BufferedWriter;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.OpenOption;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import com.salhack.summit.main.Summit;
import java.lang.reflect.Field;
import java.io.Serializable;
import net.minecraft.network.play.server.SPacketEntityHeadLook;
import net.minecraft.network.play.server.SPacketMoveVehicle;
import net.minecraft.util.StringUtils;
import net.minecraft.network.play.client.CPacketPlayer;
import com.salhack.summit.events.MinecraftEvent;
import com.salhack.summit.events.network.EventServerPacket;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.network.EventClientPacket;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.util.Timer;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class PacketLogger extends Module
{
    public final Value<Boolean> Client;
    public final Value<Boolean> Server;
    public final Value<Boolean> Data;
    public final Value<Boolean> Save;
    public final Value<Boolean> PlayerOnly;
    public final Value<Boolean> Chat;
    private Timer timer;
    private long startTimer;
    private int totalPackets;
    @EventHandler
    private Listener<EventClientPacket> onClientPacket;
    @EventHandler
    private Listener<EventServerPacket> onServerPacket;
    
    public PacketLogger() {
        super("PacketLogger", new String[] { "PacketSniffer", "Packets" }, "Allows you to log certain types of packets", "NONE", -1, ModuleType.MISC);
        this.Client = new Value<Boolean>("Client", new String[] { "CMSG" }, "Logs client packets", true);
        this.Server = new Value<Boolean>("Server", new String[] { "SMSG" }, "Logs server packets", true);
        this.Data = new Value<Boolean>("Data", new String[] { "Datas" }, "Logs data in packets", true);
        this.Save = new Value<Boolean>("Save", new String[] { "Save" }, "Saves to a file", false);
        this.PlayerOnly = new Value<Boolean>("PlayerOnly", new String[] { "PlayerOnly" }, "Only logs player packets", false);
        this.Chat = new Value<Boolean>("Chat", new String[] { "Save" }, "Saves to a file", false);
        this.timer = new Timer();
        this.totalPackets = 0;
        Serializable clazz;
        final Field[] array;
        int length;
        int i = 0;
        Field field;
        this.onClientPacket = new Listener<EventClientPacket>(event -> {
            if (event.getStage() != MinecraftEvent.Stage.Pre || !this.Client.getValue()) {
                return;
            }
            else if (this.PlayerOnly.getValue() && !(event.getPacket() instanceof CPacketPlayer)) {
                return;
            }
            else {
                this.SendMessage(String.format("MotionY: %s", this.mc.player.motionY), true);
                this.SendMessage("[Client] " + event.getPacket().getClass().getSimpleName(), true);
                if (!this.Data.getValue()) {
                    return;
                }
                else {
                    try {
                        for (clazz = event.getPacket().getClass(); clazz != Object.class; clazz = ((Class<Object>)clazz).getSuperclass()) {
                            ((Class)clazz).getDeclaredFields();
                            for (length = array.length; i < length; ++i) {
                                field = array[i];
                                if (field != null) {
                                    if (!field.isAccessible()) {
                                        field.setAccessible(true);
                                    }
                                    this.SendMessage(StringUtils.stripControlCodes("      " + field.getType().getSimpleName() + " " + field.getName() + " = " + field.get(event.getPacket())), true);
                                }
                            }
                        }
                    }
                    catch (Exception ex) {}
                    return;
                }
            }
        });
        Serializable clazz2;
        final Field[] array2;
        int length2;
        int j = 0;
        Field field2;
        this.onServerPacket = new Listener<EventServerPacket>(event -> {
            if (event.getStage() == MinecraftEvent.Stage.Pre && this.Server.getValue()) {
                if (!(event.getPacket() instanceof SPacketMoveVehicle) && !(event.getPacket() instanceof SPacketEntityHeadLook)) {
                    this.SendMessage("[Server] " + event.getPacket().getClass().getSimpleName(), false);
                    if (!(!this.Data.getValue())) {
                        try {
                            for (clazz2 = event.getPacket().getClass(); clazz2 != Object.class; clazz2 = ((Class<Object>)clazz2).getSuperclass()) {
                                ((Class)clazz2).getDeclaredFields();
                                for (length2 = array2.length; j < length2; ++j) {
                                    field2 = array2[j];
                                    if (field2 != null) {
                                        if (!field2.isAccessible()) {
                                            field2.setAccessible(true);
                                        }
                                        this.SendMessage(StringUtils.stripControlCodes("      " + field2.getType().getSimpleName() + " " + field2.getName() + " = " + field2.get(event.getPacket())), false);
                                    }
                                }
                            }
                        }
                        catch (Exception ex2) {}
                    }
                }
            }
        });
    }
    
    @Override
    public void toggleNoSave() {
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.startTimer = System.currentTimeMillis();
    }
    
    public void SendMessage(String msg, final boolean client) {
        if (this.Chat.getValue()) {
            Summit.SendMessage(msg);
        }
        if (!this.Save.getValue()) {
            return;
        }
        msg += "\n";
        try {
            Path path = Paths.get(Summit.GetDirectoryManager().GetCurrentDirectory() + "/Summit/PacketLogger/", this.startTimer + "SMSG.txt");
            if (client) {
                path = Paths.get(Summit.GetDirectoryManager().GetCurrentDirectory() + "/Summit/PacketLogger/", this.startTimer + "CMSG.txt");
            }
            try (final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
                writer.write(msg);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception ex) {}
    }
}
