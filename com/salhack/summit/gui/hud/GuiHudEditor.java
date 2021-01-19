// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud;

import java.io.IOException;
import java.util.Iterator;
import com.salhack.summit.gui.hud.components.HudComponentItem;
import com.salhack.summit.managers.HudManager;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import com.salhack.summit.main.SummitStatic;
import com.salhack.summit.module.ui.HudEditor;
import com.salhack.summit.gui.SalGuiScreen;

public class GuiHudEditor extends SalGuiScreen
{
    private HudEditor HudEditor;
    private boolean Clicked;
    private boolean Dragging;
    private int ClickMouseX;
    private int ClickMouseY;
    
    public GuiHudEditor(final HudEditor p_HudEditor) {
        this.Clicked = false;
        this.Dragging = false;
        this.ClickMouseX = 0;
        this.ClickMouseY = 0;
        SummitStatic.SELECTORMENU.UpdateMenu();
        this.HudEditor = p_HudEditor;
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawDefaultBackground();
        GL11.glPushMatrix();
        final HudComponentItem l_LastHovered = null;
        final ScaledResolution res = new ScaledResolution(this.mc);
        for (final HudComponentItem l_Item : HudManager.Get().Items) {
            if (l_Item.isEnabled()) {
                l_Item.onRender(res, (float)mouseX, (float)mouseY, partialTicks);
            }
        }
        if (l_LastHovered != null) {
            HudManager.Get().Items.remove(l_LastHovered);
            HudManager.Get().Items.add(l_LastHovered);
        }
        GL11.glPopMatrix();
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (final HudComponentItem l_Item : HudManager.Get().Items) {
            if (l_Item.isEnabled() && l_Item.onMouseClick((float)mouseX, (float)mouseY, mouseButton)) {
                return;
            }
        }
        this.Clicked = true;
        this.ClickMouseX = mouseX;
        this.ClickMouseY = mouseY;
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int state) {
        super.mouseReleased(mouseX, mouseY, state);
        HudManager.Get().Items.forEach(p_Item -> {
            if (p_Item.isEnabled()) {
                p_Item.onMouseRelease();
            }
            return;
        });
        this.Clicked = false;
    }
    
    @Override
    public void mouseClickMove(final int mouseX, final int mouseY, final int clickedMouseButton, final long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }
    
    public void onGuiClosed() {
        super.onGuiClosed();
        if (this.HudEditor.isEnabled()) {
            this.HudEditor.toggle();
        }
        this.Clicked = false;
        this.Dragging = false;
        this.ClickMouseX = 0;
        this.ClickMouseY = 0;
        HudManager.Get().onHudEditorClosed();
    }
}
