// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui;

import java.io.IOException;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.Minecraft;
import com.salhack.summit.main.SummitStatic;
import net.minecraft.client.gui.GuiScreen;

public class SalGuiScreen extends GuiScreen
{
    private boolean InventoryMoveEnabled() {
        return SummitStatic.NOSLOW.InventoryMove.getValue();
    }
    
    public static void UpdateRotationPitch(final float p_Amount) {
        final Minecraft mc = Minecraft.getMinecraft();
        float l_NewRotation = mc.player.rotationPitch + p_Amount;
        l_NewRotation = Math.max(l_NewRotation, -90.0f);
        l_NewRotation = Math.min(l_NewRotation, 90.0f);
        mc.player.rotationPitch = l_NewRotation;
    }
    
    public static void UpdateRotationYaw(final float p_Amount) {
        final Minecraft mc = Minecraft.getMinecraft();
        final float l_NewRotation = mc.player.rotationYaw + p_Amount;
        mc.player.rotationYaw = l_NewRotation;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (!this.InventoryMoveEnabled()) {
            return;
        }
        if (Keyboard.isKeyDown(200)) {
            UpdateRotationPitch(-2.5f);
        }
        if (Keyboard.isKeyDown(208)) {
            UpdateRotationPitch(2.5f);
        }
        if (Keyboard.isKeyDown(205)) {
            UpdateRotationYaw(2.5f);
        }
        if (Keyboard.isKeyDown(203)) {
            UpdateRotationYaw(-2.5f);
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }
    
    public void mouseClickMove(final int mouseX, final int mouseY, final int clickedMouseButton, final long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
}
