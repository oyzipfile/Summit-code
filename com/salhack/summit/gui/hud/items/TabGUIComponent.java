// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import net.minecraft.client.gui.ScaledResolution;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.minecraft.EventKeyInput;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.gui.tabgui.TabGui;
import com.salhack.summit.gui.hud.components.DraggableHudComponent;

public class TabGUIComponent extends DraggableHudComponent
{
    private TabGui gui;
    @EventHandler
    private final Listener<EventKeyInput> onKeyInput;
    
    public TabGUIComponent() {
        super("TabGUI", 0.0f, 25.0f, 100.0f, 100.0f);
        this.gui = new TabGui();
        this.onKeyInput = new Listener<EventKeyInput>(event -> this.gui.onKeyInput(event));
        this.setEnabled(true);
    }
    
    @Override
    public void onRender(final ScaledResolution res, final float mouseX, final float mouseY, final float partialTicks) {
        super.onRender(res, mouseX, mouseY, partialTicks);
        this.gui.onRender(this.getX(), this.getY());
    }
}
