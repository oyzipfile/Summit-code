// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.chest;

import java.io.IOException;
import com.salhack.summit.main.SummitStatic;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;
import net.minecraft.client.gui.inventory.GuiChest;

public class SalGuiChest extends GuiChest
{
    private boolean WasEnabledByGUI;
    
    public SalGuiChest(final IInventory upperInv, final IInventory lowerInv) {
        super(upperInv, lowerInv);
        this.WasEnabledByGUI = false;
        this.mc = Minecraft.getMinecraft();
        final ScaledResolution l_Res = new ScaledResolution(this.mc);
        this.setWorldAndResolution(this.mc, l_Res.getScaledWidth(), l_Res.getScaledHeight());
    }
    
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(1337, this.width / 2 + 100, this.height / 2 - this.ySize + 110, 50, 20, "Steal"));
        this.buttonList.add(new GuiButton(1338, this.width / 2 + 100, this.height / 2 - this.ySize + 130, 50, 20, "Store"));
        this.buttonList.add(new GuiButton(1339, this.width / 2 + 100, this.height / 2 - this.ySize + 150, 50, 20, "Drop"));
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        super.actionPerformed(button);
        switch (button.id) {
            case 1337: {
                SummitStatic.CHESTSTEALER.Mode.setValue("Steal");
                if (!SummitStatic.CHESTSTEALER.isEnabled()) {
                    this.WasEnabledByGUI = true;
                    SummitStatic.CHESTSTEALER.toggle();
                    break;
                }
                break;
            }
            case 1338: {
                SummitStatic.CHESTSTEALER.Mode.setValue("Store");
                if (!SummitStatic.CHESTSTEALER.isEnabled()) {
                    this.WasEnabledByGUI = true;
                    SummitStatic.CHESTSTEALER.toggle();
                    break;
                }
                break;
            }
            case 1339: {
                SummitStatic.CHESTSTEALER.Mode.setValue("Drop");
                if (!SummitStatic.CHESTSTEALER.isEnabled()) {
                    this.WasEnabledByGUI = true;
                    SummitStatic.CHESTSTEALER.toggle();
                    break;
                }
                break;
            }
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public void onGuiClosed() {
        super.onGuiClosed();
        if (this.WasEnabledByGUI && SummitStatic.CHESTSTEALER.isEnabled()) {
            SummitStatic.CHESTSTEALER.toggle();
        }
    }
}
