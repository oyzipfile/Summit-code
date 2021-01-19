// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import net.minecraft.client.gui.ScaledResolution;
import com.salhack.summit.guiclick2.components.MenuComponent;
import java.util.Iterator;
import com.salhack.summit.guiclick2.components.MenuComponentHudItem;
import com.salhack.summit.gui.hud.components.HudComponentItem;
import com.salhack.summit.managers.HudManager;
import com.salhack.summit.gui.hud.components.HudComponentFlags;
import com.salhack.summit.guiclick2.components.MenuListComponent;
import com.salhack.summit.gui.hud.components.DraggableHudComponent;

public class SelectorMenuComponent extends DraggableHudComponent
{
    private MenuListComponent list;
    
    public SelectorMenuComponent() {
        super("Selector", 300.0f, 300.0f, 100.0f, 100.0f);
        this.setEnabled(true);
        this.addFlag(HudComponentFlags.OnlyVisibleInHudEditor);
        this.UpdateMenu();
    }
    
    public void UpdateMenu() {
        this.list = new MenuListComponent("Selector", this.getX(), this.getY(), 100.0f, 200.0f, 15.0f);
        float currY = 0.0f;
        for (final HudComponentItem item : HudManager.Get().Items) {
            final MenuComponent hudComponent = new MenuComponentHudItem(item.getDisplayName(), 0.0f, currY, 100.0f, 10.0f, item);
            this.list.addItem(hudComponent);
            currY += 12.0f;
        }
        this.list.setHeight(currY + 19.0f);
    }
    
    @Override
    public void onRender(final ScaledResolution res, final float mouseX, final float mouseY, final float partialTicks) {
        super.onRender(res, mouseX, mouseY, partialTicks);
        this.list.onRender(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public boolean onMouseClick(final float mouseX, final float mouseY, final int mouseButton) {
        this.list.onClicked((int)mouseX, (int)mouseY, mouseButton);
        return false;
    }
    
    @Override
    public void onMouseRelease() {
        super.onMouseRelease();
        this.list.onReleased(0, 0, 0);
    }
}
