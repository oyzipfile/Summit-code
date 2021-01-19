// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import com.salhack.summit.main.SummitStatic;
import net.minecraft.util.math.MathHelper;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.text.DecimalFormat;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class SpeedComponent extends OptionalListHudComponent
{
    final DecimalFormat Formatter;
    private float[] _speeds;
    private double _prevTickX;
    private double _prevTickZ;
    
    public SpeedComponent() {
        super("Speed", 2.0f, 80.0f, 50.0f, 9.0f);
        this.Formatter = new DecimalFormat("#.#");
        this._speeds = new float[30];
        this.setEnabled(true);
    }
    
    private float getAverage() {
        float average = 0.0f;
        for (final float s : this._speeds) {
            average += s;
        }
        average /= this._speeds.length;
        return average;
    }
    
    private String generateName() {
        String l_Formatter = this.Formatter.format(this.getAverage());
        if (!l_Formatter.contains(".")) {
            l_Formatter += ".0";
        }
        return "Speed " + ChatFormatting.WHITE + l_Formatter + "km/h";
    }
    
    @Override
    public void onUpdate() {
        final double deltaX = this.mc.player.posX - this._prevTickX;
        final double deltaZ = this.mc.player.posZ - this._prevTickZ;
        final float l_Distance = MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        final float hour = 3600.0f * SummitStatic.TIMER.getCurrentSpeed();
        final double l_KMH = Math.floor(l_Distance / 1000.0f / (0.05f / hour));
        for (int i = 0; i < this._speeds.length - 1; ++i) {
            this._speeds[i] = this._speeds[i + 1];
        }
        this._speeds[this._speeds.length - 1] = (float)l_KMH;
        this._prevTickX = this.mc.player.posX;
        this._prevTickZ = this.mc.player.posZ;
        this.cornerItem.setName(this.generateName());
    }
}
