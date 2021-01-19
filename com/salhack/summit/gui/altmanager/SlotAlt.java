// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.altmanager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;

public class SlotAlt extends GuiSlot
{
    private GuiAltList aList;
    private int selected;
    
    public SlotAlt(final Minecraft theMinecraft, final GuiAltList aList) {
        super(theMinecraft, aList.width, aList.height, 32, aList.height - 59, 25);
        this.aList = aList;
        this.selected = -1;
    }
    
    protected int getSize() {
        return Manager.altList.size();
    }
    
    protected void elementClicked(final int var1, final boolean var2, final int var3, final int var4) {
        this.selected = var1;
    }
    
    protected boolean isSelected(final int var1) {
        return this.selected == var1;
    }
    
    protected int getSelected() {
        return this.selected;
    }
    
    protected void drawBackground() {
        this.aList.drawDefaultBackground();
    }
    
    protected void drawSlot(final int var1, final int var2, final int var3, final int heightIn, final int mouseXIn, final int mouseYIn, final float partialTicks) {
        final Alt theAlt = Manager.altList.get(var1);
        this.aList.drawString(this.aList.getLocalFontRenderer(), theAlt.getUsername(), var2, var3 + 1, 16777215);
        if (theAlt.isPremium()) {
            this.aList.drawString(this.aList.getLocalFontRenderer(), "Premium", var2, var3 + 12, 65280);
        }
        else {
            this.aList.drawString(this.aList.getLocalFontRenderer(), "§mPremium", var2, var3 + 12, 10027008);
        }
    }
}
