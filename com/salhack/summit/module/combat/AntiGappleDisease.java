// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.combat;

import net.minecraft.item.ItemStack;
import com.salhack.summit.util.entity.PlayerUtil;
import net.minecraft.init.Items;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.entity.EventItemUseFinish;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerOnStoppedUsingItem;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class AntiGappleDisease extends Module
{
    private Value<String> mode;
    private int _counter;
    private int _delay;
    boolean shouldSwap;
    boolean shouldSwapToGap;
    @EventHandler
    private Listener<EventPlayerOnStoppedUsingItem> StopUsingItem;
    @EventHandler
    private Listener<EventItemUseFinish> ItemUseFinish;
    @EventHandler
    private Listener<EventPlayerUpdate> OnPlayerUpdate;
    
    public AntiGappleDisease() {
        super("AntiGappleDisease", new String[] { "" }, "Prevents Gapple Disease", "NONE", 10167515, ModuleType.COMBAT);
        this.mode = new Value<String>("Mode", new String[] { "Modes" }, "Choose the mode. Always is recommended for 2b.", "Always");
        this._counter = 0;
        this._delay = 0;
        this.shouldSwap = false;
        this.shouldSwapToGap = false;
        this.StopUsingItem = new Listener<EventPlayerOnStoppedUsingItem>(p_Event -> {
            if (this.mode.getValue().equals("Always") && this.mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE) {
                this.shouldSwap = true;
            }
            return;
        });
        this.ItemUseFinish = new Listener<EventItemUseFinish>(p_Event -> {
            if (this.mode.getValue().equals("Always") && this.mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE) {
                this.shouldSwap = true;
            }
            return;
        });
        this.OnPlayerUpdate = new Listener<EventPlayerUpdate>(p_Event -> {
            if (this.shouldSwap) {
                this.mc.player.inventory.currentItem = 0;
                this.shouldSwapToGap = true;
                this.shouldSwap = false;
            }
            else if (this.shouldSwapToGap && ++this._delay >= 1) {
                this.swapToGap();
                this._delay = 0;
                this.shouldSwapToGap = false;
            }
            if (this.mode.getValue().equals("Old") && PlayerUtil.IsEating() && ++this._counter >= 30) {
                this.mc.player.inventory.currentItem = 0;
                this.swapToGap();
                this._counter = 0;
            }
            return;
        });
        this.mode.addString("Always");
        this.mode.addString("Old");
    }
    
    private void swapToGap() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = this.mc.player.inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() == Items.GOLDEN_APPLE) {
                this.mc.player.inventory.currentItem = i;
                this.mc.playerController.updateController();
            }
        }
    }
}
