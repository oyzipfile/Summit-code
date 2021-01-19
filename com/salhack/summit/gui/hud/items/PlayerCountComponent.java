// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class PlayerCountComponent extends OptionalListHudComponent
{
    public PlayerCountComponent() {
        super("PlayerCount", 2.0f, 185.0f);
    }
    
    @Override
    public void onUpdate() {
        this.cornerItem.setName("Players " + ChatFormatting.WHITE + this.mc.player.connection.getPlayerInfoMap().size());
    }
}
