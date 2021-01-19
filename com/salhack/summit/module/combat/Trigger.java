// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.combat;

import com.salhack.summit.managers.TickRateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import com.salhack.summit.managers.FriendManager;
import net.minecraft.entity.EntityLivingBase;
import com.salhack.summit.main.SummitStatic;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class Trigger extends Module
{
    public final Value<Boolean> HitDelay;
    public final Value<Boolean> TpsSync;
    @EventHandler
    private Listener<EventPlayerUpdate> onPlayerUpdate;
    
    public Trigger() {
        super("Trigger", new String[] { "AutoClicker" }, "Clicks for you when you are hovering over a target.", "NONE", -1, ModuleType.COMBAT);
        this.HitDelay = new Value<Boolean>("Hit Delay", new String[] { "Hit Delay" }, "Use vanilla hit delay", true);
        this.TpsSync = new Value<Boolean>("TpsSync", new String[] { "TPS" }, "Tps syncs the HitDelay", false);
        Entity e;
        this.onPlayerUpdate = new Listener<EventPlayerUpdate>(event -> {
            if (!SummitStatic.AURA.isEnabled()) {
                if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.entityHit != null) {
                    e = this.mc.objectMouseOver.entityHit;
                    if (e instanceof EntityLivingBase && !FriendManager.Get().IsFriend(e) && !e.isInvisible()) {
                        if (this.isAttackReady()) {
                            this.mc.playerController.attackEntity((EntityPlayer)this.mc.player, e);
                            this.mc.player.swingArm(EnumHand.MAIN_HAND);
                        }
                    }
                }
            }
        });
    }
    
    private boolean isAttackReady() {
        final float ticks = 20.0f - TickRateManager.Get().getTickRate();
        return !this.HitDelay.getValue() || this.mc.player.getCooledAttackStrength(((boolean)this.TpsSync.getValue()) ? (-ticks) : 0.0f) >= 1.0f;
    }
}
