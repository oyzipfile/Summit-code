// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.movement;

import net.minecraft.util.MovementInput;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalXZ;
import baritone.api.BaritoneAPI;
import com.salhack.summit.util.MathUtil;
import net.minecraft.entity.item.EntityBoat;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdateMoveState;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public final class AutoWalk extends Module
{
    public final Value<String> Mode;
    @EventHandler
    private Listener<EventPlayerUpdateMoveState> OnUpdateMoveState;
    
    public AutoWalk() {
        super("AutoWalk", new String[] { "AW" }, "Automatically walks forward", "NONE", 12723419, ModuleType.MOVEMENT);
        this.Mode = new Value<String>("Mode", new String[] { "Modes", "M" }, "The mode for walking", "Forward");
        MovementInput movementInput;
        double[] dir;
        GoalXZ goal;
        this.OnUpdateMoveState = new Listener<EventPlayerUpdateMoveState>(p_Event -> {
            if (this.Mode.getValue().equals("Forward")) {
                if (!this.NeedPause()) {
                    movementInput = this.mc.player.movementInput;
                    ++movementInput.moveForward;
                    if (this.mc.player.getRidingEntity() instanceof EntityBoat) {
                        dir = MathUtil.directionSpeed(0.4699999988079071);
                        this.mc.player.getRidingEntity().motionX = dir[0];
                        this.mc.player.getRidingEntity().motionZ = dir[1];
                    }
                }
            }
            else {
                BaritoneAPI.getSettings().allowSprint.value = true;
                BaritoneAPI.getSettings().allowBreak.value = false;
                BaritoneAPI.getSettings().primaryTimeoutMS.value = 2000L;
                goal = GoalXZ.fromDirection(this.mc.player.getPositionVector(), this.mc.player.rotationYawHead, 100.0);
                BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath((Goal)goal);
            }
            return;
        });
        this.Mode.addString("Forward");
        this.Mode.addString("Smart");
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoal((Goal)null);
    }
    
    private boolean NeedPause() {
        return false;
    }
}
