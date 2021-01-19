// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.movement;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import com.salhack.summit.events.blocks.EventBlockCollisionBoundingBox;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class WebSpeed extends Module
{
    public final Value<Boolean> BoundingBox;
    public final Value<Float> BBOffset;
    @EventHandler
    private Listener<EventPlayerUpdate> onUpdate;
    @EventHandler
    private Listener<EventBlockCollisionBoundingBox> onCollisionBoundingBox;
    
    public WebSpeed() {
        super("WebSpeed", new String[] { "WebSpeed" }, "Speed hax in web", "NONE", -1, ModuleType.MOVEMENT);
        this.BoundingBox = new Value<Boolean>("BoundingBox", new String[] { "" }, "Allows you to walk on the web", false);
        this.BBOffset = new Value<Float>("BBOffset", new String[] { "" }, "How much Y to subtract from the BB", 0.25f);
        EntityPlayerSP player;
        EntityPlayerSP player2;
        this.onUpdate = new Listener<EventPlayerUpdate>(event -> {
            if (this.mc.player.isInWeb) {
                this.mc.player.onGround = false;
                this.mc.player.isInWeb = false;
                player = this.mc.player;
                player.motionX *= 0.84;
                player2 = this.mc.player;
                player2.motionZ *= 0.84;
            }
            return;
        });
        Block block;
        this.onCollisionBoundingBox = new Listener<EventBlockCollisionBoundingBox>(event -> {
            if (this.mc.world != null && this.BoundingBox.getValue()) {
                block = this.mc.world.getBlockState(event.getPos()).getBlock();
                if (block.equals(Blocks.WEB)) {
                    event.cancel();
                    event.setBoundingBox(Block.FULL_BLOCK_AABB.contract(0.0, (double)this.BBOffset.getValue(), 0.0));
                }
            }
        });
    }
}
