// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.command.impl;

import com.salhack.summit.module.Module;
import com.mojang.realmsclient.gui.ChatFormatting;
import com.salhack.summit.managers.ModuleManager;
import com.salhack.summit.command.Command;

public class ToggleCommand extends Command
{
    public ToggleCommand() {
        super("Toggle", "Allows you to toggle a mod");
    }
    
    @Override
    public void processCommand(final String input, final String[] args) {
        if (args.length <= 1) {
            this.SendToChat("Invalid Input");
            return;
        }
        final Module mod = ModuleManager.Get().GetModLike(args[1]);
        if (mod != null) {
            mod.toggle();
            this.SendToChat(String.format("%sToggled %s", mod.isEnabled() ? ChatFormatting.GREEN : ChatFormatting.RED, mod.GetArrayListDisplayName()));
        }
        else {
            this.SendToChat(String.format("Could not find the module named %s", args[1]));
        }
    }
    
    @Override
    public String getHelp() {
        return "Allows you to toggle a mod";
    }
}
