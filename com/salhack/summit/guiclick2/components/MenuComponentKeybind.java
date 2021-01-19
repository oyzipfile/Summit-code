// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.guiclick2.components;

import org.lwjgl.input.Mouse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import com.salhack.summit.main.Summit;
import com.salhack.summit.module.Module;

public class MenuComponentKeybind extends MenuComponentEditorItem
{
    public boolean Listening;
    final Module Mod;
    private String LastKey;
    
    public MenuComponentKeybind(final Module mod, final float x, final float y, final float width, final float height) {
        super("Keybind " + mod.getKey(), x, y, width, height, true);
        this.Listening = false;
        this.LastKey = "";
        this.Mod = mod;
    }
    
    @Override
    public String getDisplayName() {
        if (this.Listening) {
            return "Press a Key...";
        }
        return "Keybind " + this.Mod.getKey();
    }
    
    @Override
    public void clicked(final int p_MouseButton) {
        super.clicked(p_MouseButton);
        if (this.hovered) {
            this.LastKey = "";
            if (p_MouseButton == 0) {
                this.Listening = !this.Listening;
            }
            else if (p_MouseButton == 1) {
                this.Listening = false;
            }
            else if (p_MouseButton == 2) {
                this.Mod.setKey("NONE");
                Summit.SendMessage("Unbinded the module: " + this.Mod.getDisplayName());
                this.Listening = false;
            }
        }
    }
    
    @Override
    public void keyTyped(final char typedChar, final int keyCode) {
        if (this.Listening) {
            String l_Key = String.valueOf(Keyboard.getKeyName(keyCode)).toUpperCase();
            if (l_Key.length() < 1) {
                this.Listening = false;
                return;
            }
            if (l_Key.equals("END") || l_Key.equals("BACK") || l_Key.equals("DELETE")) {
                l_Key = "NONE";
            }
            if (!l_Key.equals("NONE") && !l_Key.contains("CONTROL") && !l_Key.contains("SHIFT") && !l_Key.contains("MENU")) {
                if (GuiScreen.isAltKeyDown()) {
                    l_Key = (Keyboard.isKeyDown(56) ? "LMENU" : "RMENU") + " + " + l_Key;
                }
                else if (GuiScreen.isCtrlKeyDown()) {
                    String l_CtrlKey = "";
                    if (Minecraft.IS_RUNNING_ON_MAC) {
                        l_CtrlKey = (Keyboard.isKeyDown(219) ? "LCONTROL" : "RCONTROL");
                    }
                    else {
                        l_CtrlKey = (Keyboard.isKeyDown(29) ? "LCONTROL" : "RCONTROL");
                    }
                    l_Key = l_CtrlKey + " + " + l_Key;
                }
                else if (GuiScreen.isShiftKeyDown()) {
                    l_Key = (Keyboard.isKeyDown(42) ? "LSHIFT" : "RSHIFT") + " + " + l_Key;
                }
            }
            this.LastKey = l_Key;
        }
    }
    
    @Override
    public void onMouseInput() {
        if (this.Listening) {
            final String mouse = Mouse.getButtonName(Mouse.getEventButton());
            if (mouse.equalsIgnoreCase("BUTTON0") || mouse.equalsIgnoreCase("BUTTON1") || mouse.equalsIgnoreCase("BUTTON2")) {
                return;
            }
            this.Mod.setKey(mouse);
            Summit.SendMessage("Set the key of " + this.Mod.getDisplayName() + " to " + mouse);
            this.Listening = false;
        }
    }
    
    @Override
    public void renderWith(final float x, final float y, final float mouseX, final float mouseY, final float partialTicks) {
        super.renderWith(x, y, mouseX, mouseY, partialTicks);
        if (!Keyboard.getEventKeyState() && this.Listening && this.LastKey != "") {
            this.Mod.setKey(this.LastKey);
            Summit.SendMessage("Set the key of " + this.Mod.getDisplayName() + " to " + this.LastKey);
            this.Listening = false;
        }
    }
}
