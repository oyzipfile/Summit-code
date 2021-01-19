// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.movement;

import net.minecraft.block.Block;
import net.minecraft.util.math.AxisAlignedBB;
import com.salhack.summit.util.blocks.BlockUtils;
import net.minecraft.block.BlockLiquid;
import com.salhack.summit.events.player.EventPlayerJump;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Module;

public class ReverseStep extends Module
{
    private boolean jumped;
    private boolean sendPacket;
    @EventHandler
    private Listener<EventPlayerUpdate> OnPlayerUpdate;
    @EventHandler
    private Listener<EventPlayerJump> OnPlayerJump;
    
    public ReverseStep() {
        super("ReverseStep", new String[] { "RS" }, "Allows you to step down blocks faster", "NONE", -1, ModuleType.MOVEMENT);
        this.sendPacket = true;
        this.OnPlayerUpdate = new Listener<EventPlayerUpdate>(event -> {
            if (this.mc.player.onGround) {
                this.jumped = false;
            }
            if (this.mc.player.motionY > 0.0) {
                this.jumped = true;
            }
            if (!BlockUtils.collideBlock(this.mc.player.getEntityBoundingBox(), block -> block instanceof BlockLiquid)) {
                if (!BlockUtils.collideBlock(new AxisAlignedBB(this.mc.player.getEntityBoundingBox().maxX, this.mc.player.getEntityBoundingBox().maxY, this.mc.player.getEntityBoundingBox().maxZ, this.mc.player.getEntityBoundingBox().minX, this.mc.player.getEntityBoundingBox().minY - 0.01, this.mc.player.getEntityBoundingBox().minZ), block -> block instanceof BlockLiquid)) {
                    if (!this.mc.player.movementInput.jump && !this.mc.player.onGround && !this.mc.player.movementInput.jump && this.mc.player.motionY <= 0.0 && this.mc.player.fallDistance <= 1.0f && !this.jumped) {
                        this.mc.player.motionY = -1.0;
                    }
                    else {
                        this.sendPacket = true;
                    }
                }
            }
            return;
        });
        this.OnPlayerJump = new Listener<EventPlayerJump>(event -> this.jumped = true);
    }
}
