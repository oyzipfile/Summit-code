// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.altmanager;

import com.salhack.summit.main.Wrapper;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiScreen;

public class GuiAltAdd extends GuiScreen
{
    private GuiScreen previousScreen;
    public GuiTextField usernameBox;
    public GuiPasswordField passwordBox;
    
    public GuiAltAdd(final GuiScreen previousScreen) {
        this.previousScreen = previousScreen;
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96 + 12, "Add"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 96 + 36, "Back"));
        this.usernameBox = new GuiTextField(0, this.fontRenderer, this.width / 2 - 100, 76, 200, 20);
        this.passwordBox = new GuiPasswordField(2, this.fontRenderer, this.width / 2 - 100, 116, 200, 20);
        this.usernameBox.setMaxStringLength(200);
        this.passwordBox.setMaxStringLength(128);
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        this.drawString(this.fontRenderer, "Username", this.width / 2 - 100, 63, 10526880);
        this.drawString(this.fontRenderer, "Password", this.width / 2 - 100, 104, 10526880);
        try {
            this.usernameBox.drawTextBox();
            this.passwordBox.drawTextBox();
        }
        catch (Exception err) {
            err.printStackTrace();
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        this.usernameBox.mouseClicked(mouseX, mouseY, mouseButton);
        this.passwordBox.mouseClicked(mouseX, mouseY, mouseButton);
        try {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        this.usernameBox.textboxKeyTyped(typedChar, keyCode);
        this.passwordBox.textboxKeyTyped(typedChar, keyCode);
        if (typedChar == '\t') {
            if (this.usernameBox.isFocused()) {
                this.usernameBox.setFocused(false);
                this.passwordBox.setFocused(true);
            }
            else {
                this.usernameBox.setFocused(true);
                this.passwordBox.setFocused(false);
            }
        }
        if (typedChar == '\r') {
            this.actionPerformed(this.buttonList.get(0));
        }
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button.enabled) {
            if (button.id == 1) {
                if (!this.usernameBox.getText().trim().isEmpty()) {
                    if (this.passwordBox.getText().trim().isEmpty()) {
                        final Alt theAlt = new Alt(this.usernameBox.getText().trim());
                        if (!Manager.altList.contains(theAlt)) {
                            Manager.altList.add(theAlt);
                            Wrapper.getFileManager().saveAlts();
                        }
                    }
                    else {
                        final Alt theAlt = new Alt(this.usernameBox.getText().trim(), this.passwordBox.getText().trim());
                        if (!Manager.altList.contains(theAlt)) {
                            Manager.altList.add(theAlt);
                            Wrapper.getFileManager().saveAlts();
                        }
                    }
                }
                Wrapper.GetMC().displayGuiScreen(this.previousScreen);
            }
            else if (button.id == 2) {
                Wrapper.GetMC().displayGuiScreen(this.previousScreen);
            }
        }
    }
    
    public void updateScreen() {
        this.usernameBox.updateCursorCounter();
        this.passwordBox.updateCursorCounter();
    }
}
