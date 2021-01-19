// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.world;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.blocks.EventBlockCollisionBoundingBox;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class Avoid extends Module
{
    public final Value<Boolean> Fire;
    public final Value<Boolean> Cactus;
    public final Value<Boolean> Unloaded;
    @EventHandler
    private Listener<EventBlockCollisionBoundingBox> onCollisionBoundingBox;
    
    public Avoid() {
        super("Avoid", new String[] { "Avoid", "AntiVoid" }, "Avoids fire, cactus and optionally unloaded chunks", "NONE", -1, ModuleType.WORLD);
        this.Fire = new Value<Boolean>("Fire", new String[] { "f" }, "Prevents going into fire.", true);
        this.Cactus = new Value<Boolean>("Cactus", new String[] { "c" }, "Prevents going into cactus.", true);
        this.Unloaded = new Value<Boolean>("Unloaded", new String[] { "Void", "AntiVoid" }, "Prevents from going into unloaded chunks.", true);
        Block block;
        this.onCollisionBoundingBox = new Listener<EventBlockCollisionBoundingBox>(event -> {
            if (this.mc.world != null) {
                block = this.mc.world.getBlockState(event.getPos()).getBlock();
                if ((block.equals(Blocks.FIRE) && this.Fire.getValue()) || (block.equals(Blocks.CACTUS) && this.Cactus.getValue()) || ((!this.mc.world.isBlockLoaded(event.getPos(), false) || event.getPos().getY() < 0) && this.Unloaded.getValue())) {
                    event.cancel();
                    event.setBoundingBox(Block.FULL_BLOCK_AABB);
                }
            }
        });
    }
}
