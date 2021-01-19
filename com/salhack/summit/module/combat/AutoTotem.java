// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.combat;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.util.EnumHand;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.inventory.GuiContainer;
import com.salhack.summit.main.SummitStatic;
import com.salhack.summit.util.entity.PlayerUtil;
import net.minecraft.init.Items;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.util.Timer;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class AutoTotem extends Module
{
    private final Value<Double> Delay;
    private final Value<Boolean> ChatMSGS;
    private boolean isSwitching;
    private Timer timer;
    @EventHandler
    private Listener<EventPlayerUpdate> onPlayerUpdate;
    
    public AutoTotem() {
        super("AutoTotem", new String[] { "AutoTotem" }, "Automatically puts a Totem in offhand.", "NONE", 14342948, ModuleType.COMBAT);
        this.Delay = new Value<Double>("Delay", new String[] { "Delay" }, "Delay before switching to a totem.", 0.0, 0.0, 2.0, 0.1);
        this.ChatMSGS = new Value<Boolean>("ChatMSGS", new String[] { "ChatMSGS" }, "Display messages in chat.", true);
        this.timer = new Timer();
        int i;
        int n;
        this.onPlayerUpdate = new Listener<EventPlayerUpdate>(p_Event -> {
            this.setMetaData(String.valueOf(PlayerUtil.GetItemCount(Items.TOTEM_OF_UNDYING)));
            if (!SummitStatic.OFFHAND.isEnabled()) {
                if (!(this.mc.currentScreen instanceof GuiContainer) || this.mc.currentScreen instanceof GuiInventory) {
                    if (!this.isSwitching) {
                        this.timer.reset();
                    }
                    if (this.mc.player.getHeldItem(EnumHand.OFF_HAND).getItem() != Items.TOTEM_OF_UNDYING) {
                        if (!this.mc.player.isCreative()) {
                            for (n = (i = 44); i >= 9; i = --n) {
                                if (this.mc.player.inventoryContainer.getSlot(n).getStack().getItem() == Items.TOTEM_OF_UNDYING) {
                                    this.isSwitching = true;
                                    if (this.timer.passed(this.Delay.getValue() * 1000.0) && this.mc.player.inventory.getItemStack().getItem() != Items.TOTEM_OF_UNDYING) {
                                        this.mc.playerController.windowClick(0, n, 0, ClickType.PICKUP, (EntityPlayer)this.mc.player);
                                    }
                                    if (this.timer.passed(this.Delay.getValue() * 2000.0) && this.mc.player.inventory.getItemStack().getItem() == Items.TOTEM_OF_UNDYING) {
                                        this.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)this.mc.player);
                                        if (this.mc.player.inventory.getItemStack().isEmpty()) {
                                            if (this.ChatMSGS.getValue()) {
                                                this.SendMessage(ChatFormatting.YELLOW + "Offhand now has a Totem.");
                                            }
                                            this.isSwitching = false;
                                            return;
                                        }
                                    }
                                    if (this.timer.passed(this.Delay.getValue() * 3000.0) && !this.mc.player.inventory.getItemStack().isEmpty() && this.mc.player.getHeldItem(EnumHand.OFF_HAND).getItem() == Items.TOTEM_OF_UNDYING) {
                                        this.mc.playerController.windowClick(0, n, 0, ClickType.PICKUP, (EntityPlayer)this.mc.player);
                                        this.isSwitching = false;
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.isSwitching = false;
    }
}
