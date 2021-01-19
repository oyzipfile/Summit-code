// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import com.salhack.summit.main.SummitStatic;
import java.util.concurrent.TimeUnit;
import com.mojang.realmsclient.gui.ChatFormatting;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class StopwatchComponent extends OptionalListHudComponent
{
    public StopwatchComponent() {
        super("Stopwatch", 2.0f, 275.0f, 0.0f, 0.0f);
    }
    
    @Override
    public void onUpdate() {
        this.currentSide.setName("Seconds " + ChatFormatting.WHITE + TimeUnit.MILLISECONDS.toSeconds(SummitStatic.STOPWATCH.ElapsedMS - SummitStatic.STOPWATCH.StartMS));
    }
}
