// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import net.minecraft.item.ItemStack;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class TrueDurabilityComponent extends OptionalListHudComponent
{
    public TrueDurabilityComponent() {
        super("TrueDurability", 2.0f, 260.0f);
    }
    
    @Override
    public void onUpdate() {
        final ItemStack l_Stack = this.mc.player.getHeldItemMainhand();
        if (!l_Stack.isEmpty() && (l_Stack.getItem() instanceof ItemTool || l_Stack.getItem() instanceof ItemArmor || l_Stack.getItem() instanceof ItemSword)) {
            this.cornerItem.setName("Durability: " + ChatFormatting.GREEN + (l_Stack.getMaxDamage() - l_Stack.getItemDamage()));
        }
    }
}
