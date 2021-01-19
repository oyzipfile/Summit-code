// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.movement.speed;

import net.minecraft.init.MobEffects;
import com.salhack.summit.events.player.EventPlayerMove;
import com.salhack.summit.events.player.EventPlayerJump;
import com.salhack.summit.events.player.EventPlayerMotionUpdate;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.util.MinecraftInstance;

public class SpeedMode extends MinecraftInstance
{
    public void onPlayerUpdate(final EventPlayerUpdate event) {
    }
    
    public void onMotionUpdates(final EventPlayerMotionUpdate event) {
    }
    
    public void onPlayerJump(final EventPlayerJump event) {
    }
    
    public void onPlayerMove(final EventPlayerMove event) {
    }
    
    public void onRubberband() {
    }
    
    public static double getDefaultSpeed() {
        double defaultSpeed = 0.2873;
        if (SpeedMode.mc.player.isPotionActive(MobEffects.SPEED)) {
            final int amplifier = SpeedMode.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();
            defaultSpeed *= 1.0 + 0.2 * (amplifier + 1);
        }
        if (SpeedMode.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
            final int amplifier = SpeedMode.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();
            defaultSpeed /= 1.0 + 0.2 * (amplifier + 1);
        }
        return defaultSpeed;
    }
}
