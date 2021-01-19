// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import net.minecraft.client.gui.ScaledResolution;
import com.salhack.summit.managers.ModuleManager;
import com.salhack.summit.gui.hud.components.util.CornerList;
import com.salhack.summit.module.Module;
import java.util.HashMap;
import com.salhack.summit.gui.hud.components.util.CornerItem;
import java.util.ArrayList;
import com.salhack.summit.module.Value;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class ArrayListComponent extends OptionalListHudComponent
{
    public final Value<Boolean> NoBackground;
    public final Value<Integer> RainbowIncrease;
    public final Value<Boolean> Reorder;
    private ArrayList<CornerItem> currentModItems;
    private HashMap<Module, CornerItem> modToCornerItems;
    
    public ArrayListComponent() {
        super("ArrayList", 0.0f, 0.0f, 0.0f, 0.0f, "TopRight");
        this.NoBackground = new Value<Boolean>("NoBackground", new String[] { "" }, "NoBackground on arraylist", false);
        this.RainbowIncrease = new Value<Integer>("RainbowIncrease", new String[] { "" }, "How much color array should iterate each mod", 20, 0, 50, 1);
        this.Reorder = new Value<Boolean>("Reorder", new String[] { "" }, "Reorders the rainbow", true);
        this.enabled = true;
    }
    
    @Override
    public void setEnabled(final boolean enabled) {
        this.clear();
        super.setEnabled(enabled);
        this.initList();
    }
    
    public void setCurrentCornerList(final CornerList list) {
        this.clear();
        super.setCurrentCornerList(list);
        this.initList();
    }
    
    public void clear() {
        if (this.getCurrentCornerList() != null) {
            this.currentModItems.forEach(mod -> this.getCurrentCornerList().removeCornerItem(mod));
        }
        if (this.currentModItems != null) {
            this.currentModItems.clear();
        }
    }
    
    public void initList() {
        this.currentModItems = new ArrayList<CornerItem>();
        this.modToCornerItems = new HashMap<Module, CornerItem>();
        if (this.getCurrentCornerList() != null) {
            CornerItem item;
            ModuleManager.Get().GetModuleList().forEach(mod -> {
                if (mod.isEnabled() && !mod.isHidden()) {
                    item = new CornerItem(mod.getDisplayName(), mod.getMetaData(), mod.getColor(), true);
                    this.currentModItems.add(item);
                    this.getCurrentCornerList().addCornerItem(item);
                    this.modToCornerItems.put(mod, item);
                }
            });
        }
    }
    
    @Override
    public void onRender(final ScaledResolution res, final float mouseX, final float mouseY, final float partialTicks) {
        this.setWidth(100.0f);
        if (this.getCurrentCornerList() != null) {
            this.setHeight(this.getCurrentCornerList().getHeight());
        }
    }
    
    public void addMod(final Module mod) {
        final CornerItem item = new CornerItem(mod.getDisplayName(), mod.getMetaData(), mod.getColor(), true);
        this.currentModItems.add(item);
        if (this.getCurrentCornerList() != null) {
            this.getCurrentCornerList().addCornerItem(item);
        }
        this.modToCornerItems.put(mod, item);
    }
    
    public void removeMod(final Module mod) {
        final CornerItem item = this.modToCornerItems.get(mod);
        if (item != null) {
            this.currentModItems.remove(item);
            if (this.getCurrentCornerList() != null) {
                this.getCurrentCornerList().removeCornerWithAnimation(item);
            }
        }
    }
}
