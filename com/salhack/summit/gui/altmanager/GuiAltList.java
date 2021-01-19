// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.altmanager;

import net.minecraft.util.Session;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.GuiYesNo;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.FontRenderer;
import com.salhack.summit.main.Wrapper;
import net.minecraft.client.gui.GuiScreen;

public class GuiAltList extends GuiScreen
{
    public String dispErrorString;
    public boolean deleteMenuOpen;
    private SlotAlt tSlot;
    
    public GuiAltList() {
        this.dispErrorString = "";
        this.deleteMenuOpen = false;
        Manager.altList.clear();
        Wrapper.getFileManager().loadAlts();
    }
    
    public FontRenderer getLocalFontRenderer() {
        return this.fontRenderer;
    }
    
    public void onGuiClosed() {
        Wrapper.getFileManager().saveAlts();
        super.onGuiClosed();
    }
    
    public void initGui() {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(5, this.width / 2 - 105, this.height - 47, 100, 20, "Direct Login"));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 5, this.height - 47, 100, 20, "Login"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 105, this.height - 26, 66, 20, "Add"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 33, this.height - 26, 66, 20, "Remove"));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 39, this.height - 26, 66, 20, "Cancel"));
        (this.tSlot = new SlotAlt(Minecraft.getMinecraft(), this)).registerScrollButtons(7, 8);
    }
    
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.tSlot.handleMouseInput();
    }
    
    public void confirmClicked(final boolean flag, final int i1) {
        super.confirmClicked(flag, i1);
        if (this.deleteMenuOpen) {
            this.deleteMenuOpen = false;
            if (flag) {
                Manager.altList.remove(i1);
                Wrapper.getFileManager().saveAlts();
            }
            this.mc.displayGuiScreen((GuiScreen)this);
        }
    }
    
    public void actionPerformed(final GuiButton button) {
        try {
            super.actionPerformed(button);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (button.id == 1) {
            final GuiAltAdd gaa = new GuiAltAdd(this);
            this.mc.displayGuiScreen((GuiScreen)gaa);
        }
        if (button.id == 2) {
            try {
                final Alt a1 = Manager.altList.get(this.tSlot.getSelected());
                if (a1.isPremium()) {
                    try {
                        final Session s = YggdrasilPayload.loginPassword(a1.getUsername(), a1.getPassword());
                        if (s != null) {
                            Minecraft.getMinecraft().session = s;
                        }
                        Manager.altScreen.dispErrorString = "";
                    }
                    catch (Exception error) {
                        this.dispErrorString = "".concat("§cBad Login §7(").concat(a1.getUsername()).concat(")");
                    }
                }
                else {
                    Minecraft.getMinecraft().session = YggdrasilPayload.loginCrack(a1.getUsername());
                    Manager.altScreen.dispErrorString = "";
                }
            }
            catch (Exception ex) {}
        }
        if (button.id == 3) {
            try {
                final String s2 = "Delete the alt: \"" + Manager.altList.get(this.tSlot.getSelected()).getUsername() + "\"?";
                final String s3 = "Delete";
                final String s4 = "Cancel";
                final GuiYesNo guiyesno = new GuiYesNo((GuiYesNoCallback)this, s2, "", s3, s4, this.tSlot.getSelected());
                this.deleteMenuOpen = true;
                this.mc.displayGuiScreen((GuiScreen)guiyesno);
            }
            catch (Exception ex2) {}
        }
        if (button.id == 4) {
            this.mc.displayGuiScreen((GuiScreen)new GuiMainMenu());
        }
        if (button.id == 5) {
            final GuiDirectLogin gdl = new GuiDirectLogin(this);
            this.mc.displayGuiScreen((GuiScreen)new GuiDirectLogin(this));
        }
    }
    
    public void updateScreen() {
        super.updateScreen();
    }
    
    public void drawScreen(final int i, final int j, final float f) {
        this.tSlot.drawScreen(i, j, f);
        this.drawCenteredString(this.fontRenderer, "Alts", this.width / 2, 13, 16777215);
        this.fontRenderer.drawStringWithShadow("Playing as " + Minecraft.getMinecraft().session.getUsername(), 3.0f, 3.0f, 16777215);
        this.fontRenderer.drawStringWithShadow(this.dispErrorString, 3.0f, 13.0f, 16777215);
        super.drawScreen(i, j, f);
    }
}
