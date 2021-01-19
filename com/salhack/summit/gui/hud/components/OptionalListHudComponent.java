// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.components;

import com.salhack.summit.util.render.RenderUtil;
import net.minecraft.client.gui.ScaledResolution;
import com.salhack.summit.module.ValueListeners;
import com.salhack.summit.managers.HudManager;
import com.salhack.summit.module.Value;
import com.salhack.summit.gui.hud.components.util.CornerItem;
import com.salhack.summit.gui.hud.components.util.CornerList;

public class OptionalListHudComponent extends DraggableHudComponent
{
    protected CornerList currentCornerList;
    protected CornerItem cornerItem;
    public final Value<String> currentSide;
    public final Value<Boolean> RainbowVal;
    
    public OptionalListHudComponent(final String displayName, final float x, final float y, final float width, final float height, final String string2) {
        super(displayName, x, y, width, height);
        this.currentCornerList = null;
        this.currentSide = new Value<String>("CurrentSide", new String[0], "The current side to go on", "BottomRight");
        this.RainbowVal = new Value<Boolean>("Rainbow", new String[] { "" }, "Makes a dynamic rainbow", true);
        this.cornerItem = new CornerItem(displayName, "", 0, false);
        this.addFlag(HudComponentFlags.IsInCornerList);
        this.setCurrentCornerList(HudManager.Get().GetModList("BottomRight"));
        this.getValueList().add(this.currentSide);
        this.getValueList().add(this.RainbowVal);
        this.currentSide.addString("TopRight");
        this.currentSide.addString("BottomRight");
        this.currentSide.addString("BottomLeft");
        this.currentSide.addString("TopLeft");
        this.currentSide.addString("None");
        this.currentSide.Listener = new ValueListeners() {
            @Override
            public void OnValueChange(final Value<?> val) {
                OptionalListHudComponent.this.onValChange(val);
            }
        };
    }
    
    public OptionalListHudComponent(final String displayName, final float x, final float y, final float width, final float height) {
        this(displayName, x, y, width, height, "BottomRight");
    }
    
    @Override
    public void afterLoad(final boolean enabled) {
        this.setEnabled(enabled);
        this.setCurrentCornerList(HudManager.Get().GetModList(this.currentSide.getValue()));
    }
    
    public OptionalListHudComponent(final String displayName, final float x, final float y) {
        this(displayName, x, y, 0.0f, 0.0f);
    }
    
    public final CornerList getCurrentCornerList() {
        return this.currentCornerList;
    }
    
    protected void setCurrentCornerList(final CornerList list) {
        if (this.currentCornerList != null) {
            this.currentCornerList.removeCornerItem(this.cornerItem);
        }
        this.currentCornerList = list;
        if (!this.hasFlag(HudComponentFlags.IsInCornerList)) {
            this.addFlag(HudComponentFlags.IsInCornerList);
        }
        if (list != null) {
            if (this.isEnabled()) {
                list.addCornerItem(this.cornerItem);
            }
        }
        else {
            this.removeFlag(HudComponentFlags.IsInCornerList);
        }
    }
    
    @Override
    public void onRender(final ScaledResolution res, final float mouseX, final float mouseY, final float partialTicks) {
        super.onRender(res, mouseX, mouseY, partialTicks);
        this.setWidth(this.cornerItem.getWidth());
        this.setHeight(this.cornerItem.getHeight());
        RenderUtil.drawStringWithShadow(this.cornerItem.getDisplayName(), this.getX(), this.getY(), ((boolean)this.RainbowVal.getValue()) ? HudManager.Get().rainbow.GetRainbowColorAt(0) : 11184810);
    }
    
    @Override
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        if (this.currentCornerList == null) {
            return;
        }
        if (enabled) {
            if (!this.currentCornerList.contains(this.cornerItem)) {
                this.currentCornerList.addCornerItem(this.cornerItem);
            }
        }
        else {
            this.currentCornerList.removeCornerItem(this.cornerItem);
        }
    }
    
    @Override
    public float getWidth() {
        return this.cornerItem.getWidth();
    }
    
    @Override
    public float getHeight() {
        return this.cornerItem.getHeight();
    }
    
    public void onValChange(final Value<?> val) {
        this.setCurrentCornerList(HudManager.Get().GetModList(this.currentSide.getValue()));
    }
}
