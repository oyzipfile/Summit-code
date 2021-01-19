// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.guiclick2;

import com.salhack.summit.main.SummitStatic;
import java.io.IOException;
import net.minecraft.client.gui.ScaledResolution;
import java.util.Iterator;
import java.util.List;
import com.salhack.summit.guiclick2.components.MenuComponentModItem;
import com.salhack.summit.guiclick2.components.MenuListComponent;
import com.salhack.summit.managers.ModuleManager;
import com.salhack.summit.module.Module;
import com.salhack.summit.util.colors.SalRainbowUtil;
import com.salhack.summit.guiclick2.components.MenuComponent;
import com.salhack.summit.guiclick2.effects.Snow;
import java.util.ArrayList;
import com.salhack.summit.gui.SalGuiScreen;

public class ClickGuiS extends SalGuiScreen
{
    private ArrayList<Snow> _snowList;
    private final ArrayList<MenuComponent> menuComponents;
    public static SalRainbowUtil rainbowUtil;
    
    public ClickGuiS() {
        this._snowList = new ArrayList<Snow>();
        this.menuComponents = new ArrayList<MenuComponent>();
        this.addModMenuComponent(Module.ModuleType.COMBAT, 10.0f, 10.0f);
        this.addModMenuComponent(Module.ModuleType.EXPLOIT, 130.0f, 10.0f);
        this.addModMenuComponent(Module.ModuleType.MISC, 250.0f, 10.0f);
        this.addModMenuComponent(Module.ModuleType.MOVEMENT, 370.0f, 10.0f);
        this.addModMenuComponent(Module.ModuleType.RENDER, 490.0f, 10.0f);
        this.addModMenuComponent(Module.ModuleType.UI, 610.0f, 10.0f);
        this.addModMenuComponent(Module.ModuleType.WORLD, 730.0f, 10.0f);
        this.addModMenuComponent(Module.ModuleType.BOT, 10.0f, 340.0f);
        for (int i = 0; i < 100; ++i) {
            for (int y = 0; y < 3; ++y) {
                final Snow snow = new Snow(25 * i, y * -50);
                this._snowList.add(snow);
            }
        }
    }
    
    private void addModMenuComponent(final Module.ModuleType type, final float x, final float y) {
        final String typeString = String.valueOf(type);
        String string = typeString.substring(0, 1).toUpperCase() + typeString.substring(1).toLowerCase();
        if (string.equals("Ui")) {
            string = "UI";
        }
        final List<Module> modList = ModuleManager.Get().GetModuleList(type);
        string = string + " (" + modList.size() + ")";
        final MenuListComponent component = new MenuListComponent(string, x, y, 100.0f, 200.0f, 15.0f);
        float currY = 0.0f;
        for (final Module mod : modList) {
            final MenuComponent modComp = new MenuComponentModItem(mod.getDisplayName(), 0.0f, currY, 100.0f, 10.0f, mod);
            component.addItem(modComp);
            currY += 12.0f;
        }
        component.setHeight(currY + 19.0f);
        this.menuComponents.add(component);
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        final ScaledResolution res = new ScaledResolution(this.mc);
        if (!this._snowList.isEmpty()) {
            this._snowList.forEach(snow -> snow.Update(res));
        }
        this.menuComponents.forEach(item -> item.onRender((float)mouseX, (float)mouseY, partialTicks));
        ClickGuiS.rainbowUtil.OnRender();
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.menuComponents.forEach(item -> item.onClicked(mouseX, mouseY, mouseButton));
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int state) {
        super.mouseReleased(mouseX, mouseY, state);
        this.menuComponents.forEach(item -> item.onReleased(mouseX, mouseY, state));
    }
    
    @Override
    public void mouseClickMove(final int mouseX, final int mouseY, final int clickedMouseButton, final long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        this.menuComponents.forEach(item -> item.onClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick));
    }
    
    public void onGuiClosed() {
        if (SummitStatic.CLICKGUI.isEnabled()) {
            SummitStatic.CLICKGUI.toggle();
        }
    }
    
    static {
        ClickGuiS.rainbowUtil = new SalRainbowUtil(9);
    }
}
