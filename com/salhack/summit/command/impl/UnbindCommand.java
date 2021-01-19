// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.command.impl;

import com.salhack.summit.module.Module;
import com.salhack.summit.managers.ModuleManager;
import com.salhack.summit.command.Command;

public class UnbindCommand extends Command
{
    public UnbindCommand() {
        super("Unbind", "Allows you to unbind a mod to a key");
        this.commandChunks.add("<module>");
    }
    
    @Override
    public void processCommand(final String input, final String[] args) {
        if (args.length <= 1) {
            this.SendToChat("Invalid Input");
            return;
        }
        final Module mod = ModuleManager.Get().GetModLike(args[1]);
        if (mod != null) {
            mod.setKey("NONE");
            this.SendToChat(String.format("Unbound %s", mod.getDisplayName()));
        }
        else {
            this.SendToChat(String.format("Could not find the module named %s", args[1]));
        }
    }
    
    @Override
    public String getHelp() {
        return "Allows you to unbind a mod";
    }
}
