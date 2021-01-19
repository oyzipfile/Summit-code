// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import net.minecraft.client.network.NetworkPlayerInfo;
import com.mojang.realmsclient.gui.ChatFormatting;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class PingComponent extends OptionalListHudComponent
{
    public PingComponent() {
        super("Ping", 2.0f, 230.0f, 100.0f, 100.0f);
        this.setEnabled(true);
    }
    
    @Override
    public void onUpdate() {
        if (this.mc.world == null || this.mc.player == null || this.mc.player.getUniqueID() == null) {
            return;
        }
        final NetworkPlayerInfo playerInfo = this.mc.getConnection().getPlayerInfo(this.mc.player.getUniqueID());
        if (playerInfo == null) {
            return;
        }
        this.cornerItem.setName("Ping " + ChatFormatting.WHITE + playerInfo.getResponseTime() + "ms");
    }
}
