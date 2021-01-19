// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import java.util.Iterator;
import com.salhack.summit.managers.FontManager;
import com.salhack.summit.util.render.RenderUtil;
import java.util.List;
import java.util.Collections;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.ScaledResolution;
import java.text.DecimalFormat;
import com.salhack.summit.module.Value;
import com.salhack.summit.gui.hud.components.DraggableHudComponent;

public class ArmorComponent extends DraggableHudComponent
{
    public final Value<String> Mode;
    DecimalFormat Formatter;
    
    public ArmorComponent() {
        super("Armor", 200.0f, 200.0f, 200.0f, 200.0f);
        this.Mode = new Value<String>("Mode", new String[] { "Mode" }, "Modes", "Bars");
        this.Formatter = new DecimalFormat("#");
        this.Mode.addString("Bars");
        this.Mode.addString("SimplePct");
    }
    
    @Override
    public void onRender(final ScaledResolution res, final float mouseX, final float mouseY, final float partialTicks) {
        GlStateManager.pushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        super.onRender(res, mouseX, mouseY, partialTicks);
        final Iterator<ItemStack> l_Items = this.mc.player.getArmorInventoryList().iterator();
        final ArrayList<ItemStack> l_Stacks = new ArrayList<ItemStack>();
        while (l_Items.hasNext()) {
            final ItemStack l_Stack = l_Items.next();
            if (l_Stack != ItemStack.EMPTY && l_Stack.getItem() != Items.AIR) {
                l_Stacks.add(l_Stack);
            }
        }
        Collections.reverse(l_Stacks);
        final String s = this.Mode.getValue();
        switch (s) {
            case "Bars": {
                RenderUtil.drawRect(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), -1727263732);
                int l_Y = 0;
                for (int l_I = 0; l_I < l_Stacks.size(); ++l_I) {
                    final ItemStack l_Stack2 = l_Stacks.get(l_I);
                    float l_X = this.getX() + 1.0f;
                    final float l_ArmorPct = (l_Stack2.getMaxDamage() - l_Stack2.getItemDamage()) / (float)l_Stack2.getMaxDamage() * 100.0f;
                    final float l_ArmorBarPct = Math.min(l_ArmorPct, 100.0f);
                    int l_ColorMin = -1717570715;
                    int l_ColorMax = -1726742784;
                    if (l_ArmorBarPct < 80.0f && l_ArmorPct > 30.0f) {
                        l_ColorMin = -1711294976;
                        l_ColorMax = -1711278336;
                    }
                    else if (l_ArmorBarPct < 30.0f) {
                        l_ColorMin = -1711341568;
                        l_ColorMax = -1713759718;
                    }
                    RenderUtil.drawGradientRect(l_X, this.getY() + l_Y, l_X + this.getWidth() * (l_ArmorBarPct / 100.0f), this.getY() + l_Y + 15.0f, l_ColorMin, l_ColorMax);
                    this.mc.getRenderItem().renderItemAndEffectIntoGUI(l_Stack2, (int)l_X, (int)this.getY() + l_Y);
                    final String l_Durability = String.format("%s %s / %s", this.Formatter.format(l_ArmorBarPct) + "%", l_Stack2.getMaxDamage() - l_Stack2.getItemDamage(), l_Stack2.getMaxDamage());
                    l_X = this.getX() + 18.0f;
                    RenderUtil.drawStringWithShadow(l_Durability, l_X, this.getY() + l_Y + 2.0f, 16777215);
                    l_Y += 15;
                }
                this.setWidth(100.0f);
                this.setHeight((float)l_Y);
                break;
            }
            case "SimplePct": {
                float l_X2 = 0.0f;
                float l_TextX = 4.0f;
                for (int l_I2 = 0; l_I2 < l_Stacks.size(); ++l_I2) {
                    final ItemStack l_Stack3 = l_Stacks.get(l_I2);
                    this.mc.getRenderItem().renderItemAndEffectIntoGUI(l_Stack3, (int)(this.getX() + l_X2), (int)this.getY() + 10);
                    this.mc.getRenderItem().renderItemOverlays(this.mc.fontRenderer, l_Stack3, (int)(this.getX() + l_X2), (int)this.getY() + 10);
                    FontManager.Get().getGameFont().drawCenteredString(this.Formatter.format(GetPctFromStack(l_Stack3)), this.getX() + l_TextX, this.getY(), -1);
                    l_X2 += 20.0f;
                    if (l_I2 < l_Stacks.size() - 1) {
                        final float l_Pct = GetPctFromStack(l_Stacks.get(l_I2 + 1));
                        if (l_Pct == 100.0f) {
                            l_TextX += 22.0f;
                        }
                        else if (l_Pct >= 10.0) {
                            l_TextX += 21.0f;
                        }
                        else {
                            l_TextX += 25.0f;
                        }
                    }
                }
                this.setWidth(75.0f);
                this.setHeight(24.0f);
                break;
            }
        }
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
    }
    
    public static float GetPctFromStack(final ItemStack p_Stack) {
        final float l_ArmorPct = (p_Stack.getMaxDamage() - p_Stack.getItemDamage()) / (float)p_Stack.getMaxDamage() * 100.0f;
        final float l_ArmorBarPct = Math.min(l_ArmorPct, 100.0f);
        return l_ArmorBarPct;
    }
}
