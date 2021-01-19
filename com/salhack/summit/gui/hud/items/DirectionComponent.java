// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import net.minecraft.util.math.MathHelper;
import net.minecraft.client.Minecraft;
import com.mojang.realmsclient.gui.ChatFormatting;
import com.salhack.summit.managers.HudManager;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class DirectionComponent extends OptionalListHudComponent
{
    public DirectionComponent() {
        super("Direction", 2.0f, 155.0f);
        this.setCurrentCornerList(HudManager.Get().GetModList("BottomLeft"));
        this.setEnabled(true);
    }
    
    @Override
    public void onUpdate() {
        this.cornerItem.setName(this.getFacing() + " " + ChatFormatting.GRAY + this.getTowards());
    }
    
    private String getFacing() {
        switch (MathHelper.floor(Minecraft.getMinecraft().player.rotationYaw * 8.0f / 360.0f + 0.5) & 0x7) {
            case 0: {
                return "South";
            }
            case 1: {
                return "South West";
            }
            case 2: {
                return "West";
            }
            case 3: {
                return "North West";
            }
            case 4: {
                return "North";
            }
            case 5: {
                return "North East";
            }
            case 6: {
                return "East";
            }
            case 7: {
                return "South East";
            }
            default: {
                return "Invalid";
            }
        }
    }
    
    private String getTowards() {
        switch (MathHelper.floor(Minecraft.getMinecraft().player.rotationYaw * 8.0f / 360.0f + 0.5) & 0x7) {
            case 0: {
                return "+Z";
            }
            case 1: {
                return "-X +Z";
            }
            case 2: {
                return "-X";
            }
            case 3: {
                return "-X -Z";
            }
            case 4: {
                return "-Z";
            }
            case 5: {
                return "+X -Z";
            }
            case 6: {
                return "+X";
            }
            case 7: {
                return "+X +Z";
            }
            default: {
                return "Invalid";
            }
        }
    }
}
