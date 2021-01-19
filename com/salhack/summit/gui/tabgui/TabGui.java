// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.tabgui;

import com.salhack.summit.events.minecraft.EventKeyInput;
import com.salhack.summit.managers.HudManager;
import com.salhack.summit.util.render.RenderUtil;
import java.util.Iterator;
import java.util.List;
import com.salhack.summit.managers.ModuleManager;
import com.salhack.summit.module.Module;
import java.util.ArrayList;

public class TabGui
{
    private ArrayList<TabGuiItem> categories;
    private TabGuiItem Hovered;
    private int currMainIndex;
    private TabGuiItem ItemToUse;
    private int currOffIndex;
    
    public TabGui() {
        this.categories = new ArrayList<TabGuiItem>();
        this.Hovered = null;
        this.currMainIndex = 0;
        this.ItemToUse = null;
        this.currOffIndex = 0;
        for (final Module.ModuleType type : Module.ModuleType.values()) {
            final List<Module> mods = ModuleManager.Get().GetModuleList(type);
            if (!mods.isEmpty()) {
                final String typeString = String.valueOf(type);
                String string = typeString.substring(0, 1).toUpperCase() + typeString.substring(1).toLowerCase();
                if (string.equals("Ui")) {
                    string = "UI";
                }
                final TabGuiItem item = new TabGuiItem(string);
                for (final Module mod : mods) {
                    item.getAddons().add(new TabGuiItem(mod.getDisplayName(), mod));
                }
                this.categories.add(item);
            }
        }
    }
    
    public void onRender(final float x, final float y) {
        RenderUtil.drawRect(x, y, x + 80.0f, y + this.categories.size() * 11, 1493172224);
        RenderUtil.drawOutlineRect(x, y, x + 80.0f, y + this.categories.size() * 11, 3.0f, 4472639);
        int totalY = 0;
        for (int i = 0; i < this.categories.size(); ++i) {
            final TabGuiItem item = this.categories.get(i);
            float offsetX = 5.0f;
            if (i == this.currMainIndex) {
                RenderUtil.drawLine(x + 1.0f, y + totalY, x + 1.0f, y + totalY + 11.0f, 4.0f, HudManager.Get().rainbow.GetRainbowColorAt(0));
                this.Hovered = item;
                if (item.isOpened()) {
                    this.renderItemSubItems(item, x, y, (float)totalY);
                }
                offsetX = 8.0f;
                item.setHovered(true);
            }
            else {
                item.setHovered(false);
                item.setOpened(false);
            }
            RenderUtil.drawStringWithShadow(item.getDisplayName(), x + offsetX, y + totalY + 3.0f, item.isHovered() ? HudManager.Get().rainbow.GetRainbowColorAt(0) : -1);
            totalY += 11;
        }
    }
    
    private void renderItemSubItems(final TabGuiItem item, final float currX, final float currY, final float totalY) {
        RenderUtil.drawRect(currX + 81.0f, currY + totalY, currX + 180.0f, currY + totalY + this.Hovered.getAddons().size() * 11, 1493172224);
        float totalSubY = 0.0f;
        int i = 0;
        final int total = item.getAddons().size() - 1;
        if (this.currOffIndex > total) {
            this.currOffIndex = 0;
        }
        else if (this.currOffIndex < 0) {
            this.currOffIndex = total;
        }
        for (final TabGuiItem subItem : item.getAddons()) {
            float offsetX = 85.0f;
            if (i++ == this.currOffIndex) {
                RenderUtil.drawLine(currX + offsetX - 3.0f, currY + totalY + totalSubY, currX + offsetX - 3.0f, currY + totalY + 11.0f + totalSubY, 4.0f, HudManager.Get().rainbow.GetRainbowColorAt(0));
                this.ItemToUse = subItem;
                if (subItem.isOpened()) {
                    for (final TabGuiItem extraSubItems : subItem.getAddons()) {
                        this.renderItemSubItems(extraSubItems, currX + 81.0f, currY + totalY, totalY);
                    }
                }
                subItem.setHovered(true);
                offsetX += 3.0f;
            }
            else {
                subItem.setHovered(false);
            }
            RenderUtil.drawStringWithShadow(subItem.getDisplayName(), currX + offsetX, currY + totalY + totalSubY + 3.0f, subItem.isHovered() ? HudManager.Get().rainbow.GetRainbowColorAt(0) : subItem.getColor());
            totalSubY += 11.0f;
        }
    }
    
    public void onKeyInput(final EventKeyInput event) {
        if (event.getKey().equals("UP")) {
            if (this.ItemToUse != null) {
                --this.currOffIndex;
                return;
            }
            if (this.currMainIndex <= 0) {
                this.currMainIndex = this.categories.size() - 1;
                return;
            }
            --this.currMainIndex;
        }
        else if (event.getKey().equals("DOWN")) {
            if (this.ItemToUse != null) {
                ++this.currOffIndex;
                return;
            }
            if (this.currMainIndex >= this.categories.size() - 1) {
                this.currMainIndex = 0;
                return;
            }
            ++this.currMainIndex;
        }
        else if (event.getKey().equals("RIGHT")) {
            if (this.ItemToUse != null) {
                this.ItemToUse.toggle();
            }
            else if (this.Hovered != null) {
                this.Hovered.setOpened(true);
            }
        }
        else if (event.getKey().equals("LEFT")) {
            this.ItemToUse = null;
            if (this.Hovered != null) {
                this.Hovered.setOpened(false);
            }
        }
    }
}
