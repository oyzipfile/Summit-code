// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.combat;

import net.minecraft.util.math.Vec3d;
import java.util.Iterator;
import com.salhack.summit.util.MathUtil;
import net.minecraft.entity.Entity;
import com.salhack.summit.managers.FriendManager;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import com.salhack.summit.events.MinecraftEvent;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerMotionUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Module;

public class BowAim extends Module
{
    @EventHandler
    private Listener<EventPlayerMotionUpdate> onMotionUpdate;
    
    public BowAim() {
        super("BowAim", new String[] { "BA" }, "Predicts enemies movement and aims at them when using a Bow.", "NONE", 12760722, ModuleType.COMBAT);
        EntityPlayer target;
        float lastDistance;
        final Iterator<EntityPlayer> iterator;
        EntityPlayer p;
        float dist;
        Vec3d pos;
        float[] angles;
        this.onMotionUpdate = new Listener<EventPlayerMotionUpdate>(event -> {
            if (event.getStage() == MinecraftEvent.Stage.Pre && !event.isCancelled()) {
                if (this.mc.player.getHeldItemMainhand().getItem() instanceof ItemBow && this.mc.player.isHandActive() && this.mc.player.getItemInUseMaxCount() >= 3) {
                    target = null;
                    lastDistance = 100.0f;
                    this.mc.world.playerEntities.iterator();
                    while (iterator.hasNext()) {
                        p = iterator.next();
                        if (!(p instanceof EntityPlayerSP)) {
                            if (FriendManager.Get().IsFriend((Entity)p)) {
                                continue;
                            }
                            else {
                                dist = p.getDistance((Entity)this.mc.player);
                                if (dist < lastDistance) {
                                    lastDistance = dist;
                                    target = p;
                                }
                                else {
                                    continue;
                                }
                            }
                        }
                    }
                    if (target != null) {
                        pos = MathUtil.interpolateEntity((Entity)target, this.mc.getRenderPartialTicks());
                        angles = MathUtil.calcAngle(MathUtil.interpolateEntity((Entity)this.mc.player, this.mc.getRenderPartialTicks()), pos);
                        event.cancel();
                        event.setYaw(angles[0]);
                        event.setPitch(angles[1]);
                    }
                }
            }
        });
    }
}
