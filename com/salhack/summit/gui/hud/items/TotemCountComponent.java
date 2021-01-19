// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import com.salhack.summit.util.entity.PlayerUtil;
import net.minecraft.init.Items;
import com.mojang.realmsclient.gui.ChatFormatting;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class TotemCountComponent extends OptionalListHudComponent
{
    public TotemCountComponent() {
        super("TotemCount", 2.0f, 215.0f);
    }
    
    @Override
    public void onUpdate() {
        this.cornerItem.setName("Totems: " + ChatFormatting.WHITE + PlayerUtil.GetItemCount(Items.TOTEM_OF_UNDYING));
    }
}
