// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.command.impl;

import com.salhack.summit.main.SummitStatic;
import com.salhack.summit.command.Command;

public class AnnoyCommand extends Command
{
    public AnnoyCommand() {
        super("Annoy", "Choose who you will annoy.");
        this.commandChunks.add("<username>");
    }
    
    @Override
    public void processCommand(final String input, final String[] args) {
        if (args.length < 1) {
            this.SendToChat("Please specify a target!");
        }
        else if (!SummitStatic.ANNOY.manualTarget.getValue()) {
            this.SendToChat("Please enable manual target in the Annoy Module.");
        }
        else {
            SummitStatic.ANNOY.targetName = args[1].toLowerCase();
            this.SendToChat("Locked on to " + args[1]);
        }
    }
    
    @Override
    public String getHelp() {
        return "Locks on to a specific player.\ntarget <username>";
    }
}
