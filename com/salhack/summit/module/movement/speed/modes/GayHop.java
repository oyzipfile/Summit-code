// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.movement.speed.modes;

import java.math.RoundingMode;
import java.math.BigDecimal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.block.Block;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import com.salhack.summit.main.SummitStatic;
import com.salhack.summit.events.player.EventPlayerMove;
import com.salhack.summit.events.player.EventPlayerJump;
import com.salhack.summit.util.MovementUtils;
import com.salhack.summit.events.MinecraftEvent;
import com.salhack.summit.events.player.EventPlayerMotionUpdate;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.module.movement.speed.SpeedMode;

public class GayHop extends SpeedMode
{
    private double moveSpeed;
    private int level;
    private double lastDist;
    private int timerDelay;
    private boolean safeJump;
    
    public GayHop() {
        this.moveSpeed = 0.2873;
        this.level = 1;
        this.lastDist = 0.0;
        this.moveSpeed = 0.0;
        this.level = 4;
    }
    
    @Override
    public void onRubberband() {
        this.moveSpeed = 0.2873;
        this.lastDist = 0.0;
        this.moveSpeed = 0.0;
        this.level = 4;
        this.timerDelay = 0;
        this.safeJump = false;
    }
    
    @Override
    public void onPlayerUpdate(final EventPlayerUpdate event) {
    }
    
    @Override
    public void onMotionUpdates(final EventPlayerMotionUpdate event) {
        if (event.getStage() != MinecraftEvent.Stage.Pre) {
            return;
        }
        final double xDist = GayHop.mc.player.posX - GayHop.mc.player.prevPosX;
        final double zDist = GayHop.mc.player.posZ - GayHop.mc.player.prevPosZ;
        this.lastDist = Math.sqrt(xDist * xDist + zDist * zDist);
        if (!MovementUtils.isMoving()) {
            this.safeJump = true;
        }
        else if (GayHop.mc.player.onGround) {
            this.safeJump = false;
        }
    }
    
    @Override
    public void onPlayerJump(final EventPlayerJump event) {
    }
    
    @Override
    public void onPlayerMove(final EventPlayerMove event) {
        if (event.getStage() != MinecraftEvent.Stage.Pre || event.isCancelled()) {
            return;
        }
        event.cancel();
        ++this.timerDelay;
        this.timerDelay %= 5;
        if (this.timerDelay != 0) {
            SummitStatic.TIMER.SetOverrideSpeed(1.0f);
        }
        else if (MovementUtils.hasMotion()) {
            SummitStatic.TIMER.SetOverrideSpeed(1.3f);
            final EntityPlayerSP player = GayHop.mc.player;
            player.motionX *= 1.0199999809265137;
            final EntityPlayerSP player2 = GayHop.mc.player;
            player2.motionZ *= 1.0199999809265137;
        }
        if (GayHop.mc.player.onGround && MovementUtils.hasMotion()) {
            this.level = 2;
        }
        if (this.round(GayHop.mc.player.posY - (int)GayHop.mc.player.posY) == this.round(0.138)) {
            final EntityPlayerSP player3 = GayHop.mc.player;
            player3.motionY -= 0.08;
            event.setY(event.getY() - 0.09316090325960147);
            final EntityPlayerSP player4 = GayHop.mc.player;
            player4.posY -= 0.09316090325960147;
        }
        if (this.level == 1 && (GayHop.mc.player.moveForward != 0.0f || GayHop.mc.player.moveStrafing != 0.0f)) {
            this.level = 2;
            this.moveSpeed = 1.38 * getDefaultSpeed() - 0.01;
        }
        else if (this.level == 2) {
            this.level = 3;
            event.setY(GayHop.mc.player.motionY = 0.399399995803833);
            this.moveSpeed *= 2.149;
        }
        else if (this.level == 3) {
            this.level = 4;
            final double difference = 0.66 * (this.lastDist - getDefaultSpeed());
            this.moveSpeed = this.lastDist - difference;
        }
        else {
            if (GayHop.mc.world.getCollisionBoxes((Entity)GayHop.mc.player, GayHop.mc.player.getEntityBoundingBox().offset(0.0, GayHop.mc.player.motionY, 0.0)).size() > 0 || GayHop.mc.player.collidedVertically) {
                this.level = 1;
            }
            this.moveSpeed = this.lastDist - this.lastDist / 159.0;
        }
        this.moveSpeed = Math.max(this.moveSpeed, SpeedMode.getDefaultSpeed());
        float forward = GayHop.mc.player.movementInput.moveForward;
        float strafe = GayHop.mc.player.movementInput.moveStrafe;
        float yaw = GayHop.mc.player.rotationYaw;
        if (forward == 0.0f && strafe == 0.0f) {
            event.setX(0.0);
            event.setZ(0.0);
        }
        else if (forward != 0.0f) {
            if (strafe >= 1.0f) {
                yaw += ((forward > 0.0f) ? -45 : 45);
                strafe = 0.0f;
            }
            else if (strafe <= -1.0f) {
                yaw += ((forward > 0.0f) ? 45 : -45);
                strafe = 0.0f;
            }
            if (forward > 0.0f) {
                forward = 1.0f;
            }
            else if (forward < 0.0f) {
                forward = -1.0f;
            }
        }
        final double mx = Math.cos(Math.toRadians(yaw + 90.0f));
        final double mz = Math.sin(Math.toRadians(yaw + 90.0f));
        event.setX(forward * this.moveSpeed * mx + strafe * this.moveSpeed * mz);
        event.setZ(forward * this.moveSpeed * mz - strafe * this.moveSpeed * mx);
        GayHop.mc.player.stepHeight = 0.6f;
        if (forward == 0.0f && strafe == 0.0f) {
            event.setX(0.0);
            event.setZ(0.0);
        }
    }
    
    private Block getBlock(final AxisAlignedBB axisAlignedBB) {
        for (int x = MathHelper.floor(axisAlignedBB.minX); x < MathHelper.floor(axisAlignedBB.maxX) + 1; ++x) {
            for (int z = MathHelper.floor(axisAlignedBB.minZ); z < MathHelper.floor(axisAlignedBB.maxZ) + 1; ++z) {
                final Block block = GayHop.mc.world.getBlockState(new BlockPos(x, (int)axisAlignedBB.minY, z)).getBlock();
                if (block != null) {
                    return block;
                }
            }
        }
        return null;
    }
    
    private Block getBlock(final double offset) {
        return this.getBlock(GayHop.mc.player.getEntityBoundingBox().offset(0.0, offset, 0.0));
    }
    
    private double round(final double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
