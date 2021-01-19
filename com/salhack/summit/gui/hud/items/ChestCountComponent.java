// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import com.mojang.realmsclient.gui.ChatFormatting;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class ChestCountComponent extends OptionalListHudComponent
{
    public ChestCountComponent() {
        super("ChestCount", 2.0f, 245.0f, 0.0f, 0.0f);
        this.setEnabled(true);
    }
    
    @Override
    public void onUpdate() {
        this.cornerItem.setName("Chests " + ChatFormatting.WHITE + this.mc.world.loadedTileEntityList.stream().filter(e -> e instanceof TileEntityChest).count());
    }
}
