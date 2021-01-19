// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.math.MathHelper;
import java.text.DecimalFormat;
import com.salhack.summit.managers.HudManager;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class YawComponent extends OptionalListHudComponent
{
    public YawComponent() {
        super("Yaw", 2.0f, 200.0f);
        this.setCurrentCornerList(HudManager.Get().GetModList("BottomLeft"));
    }
    
    @Override
    public void onUpdate() {
        final DecimalFormat l_Format = new DecimalFormat("#.##");
        final float l_Yaw = MathHelper.wrapDegrees(this.mc.player.rotationYaw);
        String direction = "Yaw: " + ChatFormatting.WHITE + l_Format.format(l_Yaw);
        if (!direction.contains(".")) {
            direction += ".00";
        }
        else {
            final String[] l_Split = direction.split("\\.");
            if (l_Split != null && l_Split[1] != null && l_Split[1].length() != 2) {
                direction += 0;
            }
        }
        this.cornerItem.setName(direction);
    }
}
