// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import com.salhack.summit.managers.TickRateManager;
import com.mojang.realmsclient.gui.ChatFormatting;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class TPSComponent extends OptionalListHudComponent
{
    public TPSComponent() {
        super("TPS", 2.0f, 125.0f);
        this.setEnabled(true);
    }
    
    @Override
    public void onUpdate() {
        this.cornerItem.setName("TPS " + ChatFormatting.WHITE + String.format("%.2f", TickRateManager.Get().getTickRate()));
    }
}
