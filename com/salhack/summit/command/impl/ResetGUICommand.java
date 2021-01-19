// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.command.impl;

import com.salhack.summit.main.SummitStatic;
import com.salhack.summit.command.Command;

public class ResetGUICommand extends Command
{
    public ResetGUICommand() {
        super("ResetGUI", "Reset the ClickGUI positions to default");
    }
    
    @Override
    public void processCommand(final String input, final String[] args) {
        SummitStatic.CLICKGUI.ResetToDefaults();
        this.SendToChat("Reset the ClickGUI");
    }
    
    @Override
    public String getHelp() {
        return "Resets the positions of the ClickGUI";
    }
}
