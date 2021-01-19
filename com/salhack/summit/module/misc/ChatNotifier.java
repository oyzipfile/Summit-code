// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.misc;

import com.salhack.summit.managers.NotificationManager;
import com.mojang.realmsclient.gui.ChatFormatting;
import com.salhack.summit.events.salhack.EventSalHackModuleDisable;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.salhack.EventSalHackModuleEnable;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Module;

public class ChatNotifier extends Module
{
    @EventHandler
    private Listener<EventSalHackModuleEnable> OnModEnable;
    @EventHandler
    private Listener<EventSalHackModuleDisable> OnModDisable;
    
    public ChatNotifier() {
        super("ChatNotifier", new String[] { "" }, "Notifiys you in chat and notification system when a mod is enabled/disabled", "NONE", -1, ModuleType.MISC);
        final String l_Msg;
        this.OnModEnable = new Listener<EventSalHackModuleEnable>(p_Event -> {
            l_Msg = String.format("%s was enabled.", ChatFormatting.GREEN + p_Event.Mod.getDisplayName() + ChatFormatting.AQUA);
            this.SendMessage(l_Msg);
            NotificationManager.Get().AddNotification("ChatNotifier", l_Msg);
            return;
        });
        final String l_Msg2;
        this.OnModDisable = new Listener<EventSalHackModuleDisable>(p_Event -> {
            l_Msg2 = String.format("%s was disabled.", ChatFormatting.RED + p_Event.Mod.getDisplayName() + ChatFormatting.AQUA);
            this.SendMessage(l_Msg2);
            NotificationManager.Get().AddNotification("ChatNotifier", l_Msg2);
        });
    }
}
