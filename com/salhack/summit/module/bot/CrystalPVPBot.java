// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.bot;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import com.salhack.summit.util.Hole;
import com.salhack.summit.util.render.ESPUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import java.util.Iterator;
import baritone.api.pathing.goals.GoalXZ;
import com.salhack.summit.module.combat.AutoCrystal;
import com.salhack.summit.main.SummitStatic;
import baritone.api.pathing.goals.Goal;
import baritone.api.BaritoneAPI;
import net.minecraft.entity.Entity;
import com.salhack.summit.managers.FriendManager;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import net.minecraft.init.Items;
import com.salhack.summit.util.entity.PlayerUtil;
import com.salhack.summit.events.player.EventPlayerOnStoppedUsingItem;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class CrystalPVPBot extends Module
{
    public final Value<Integer> Radius;
    private boolean isEating;
    @EventHandler
    private Listener<EventPlayerUpdate> onPlayerUpdate;
    @EventHandler
    private Listener<EventPlayerOnStoppedUsingItem> OnStopUsingItem;
    
    public CrystalPVPBot() {
        super("CrystalPVPBot", new String[] { "CPVPBot" }, "Automatically fights the battles for you, because pvp is gay", "NONE", -1, ModuleType.BOT);
        this.Radius = new Value<Integer>("Radius", new String[] { "Radius", "Range", "Distance" }, "Radius in blocks to scan for holes.", 8, 0, 32, 1);
        final boolean isTrapped;
        int slot;
        EntityPlayer target;
        float distance;
        final Iterator<EntityPlayer> iterator;
        EntityPlayer player;
        float dist;
        boolean enemyInHole;
        int slot2;
        this.onPlayerUpdate = new Listener<EventPlayerUpdate>(event -> {
            isTrapped = PlayerUtil.IsPlayerTrapped();
            if (PlayerUtil.GetHealthWithAbsorption() <= 20.0f || isTrapped) {
                slot = PlayerUtil.GetItemInHotbar(isTrapped ? Items.CHORUS_FRUIT : Items.GOLDEN_APPLE);
                if (slot != -1) {
                    this.mc.player.inventory.currentItem = slot;
                    this.mc.playerController.updateController();
                    if (this.mc.rightClickDelayTimer != 0) {
                        return;
                    }
                    else {
                        this.mc.rightClickDelayTimer = 4;
                        this.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                        this.isEating = true;
                    }
                }
            }
            else {
                this.isEating = false;
            }
            target = null;
            distance = 6.0f;
            this.mc.world.playerEntities.iterator();
            while (iterator.hasNext()) {
                player = iterator.next();
                if (!(player instanceof EntityPlayerSP)) {
                    if (FriendManager.Get().IsFriend((Entity)player)) {
                        continue;
                    }
                    else {
                        dist = this.mc.player.getDistance((Entity)player);
                        if (dist < distance) {
                            distance = dist;
                            target = player;
                        }
                        else {
                            continue;
                        }
                    }
                }
            }
            if (target != null) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoal((Goal)null);
                if (!SummitStatic.AUTOCRYSTAL.isEnabled()) {
                    SummitStatic.AUTOCRYSTAL.toggle();
                }
                if (!PlayerUtil.IsPlayerInHole()) {
                    if (!this.baritoneIntoHole()) {
                        if (!SummitStatic.SURROUND.isEnabled()) {
                            SummitStatic.SURROUND.toggle();
                        }
                    }
                    else if (SummitStatic.SURROUND.isEnabled()) {
                        SummitStatic.SURROUND.toggle();
                    }
                }
                enemyInHole = PlayerUtil.IsPlayerInHole(target);
                if (enemyInHole) {
                    if (target.getHealth() + target.getAbsorptionAmount() > AutoCrystal.FacePlace.getValue()) {
                        if (!SummitStatic.AURA.isEnabled()) {
                            SummitStatic.AURA.toggle();
                        }
                    }
                    else if (SummitStatic.AURA.isEnabled()) {
                        SummitStatic.AURA.toggle();
                    }
                }
                else if (SummitStatic.AUTOCRYSTAL.isCrystalling()) {
                    if (SummitStatic.AURA.isEnabled()) {
                        SummitStatic.AURA.toggle();
                    }
                }
                else if (!SummitStatic.AURA.isEnabled()) {
                    SummitStatic.AURA.toggle();
                    slot2 = PlayerUtil.GetItemSlot(Items.DIAMOND_SWORD);
                    if (slot2 != -1) {
                        this.mc.player.inventory.currentItem = slot2;
                        this.mc.playerController.updateController();
                    }
                }
            }
            else {
                if (SummitStatic.SURROUND.isEnabled()) {
                    SummitStatic.SURROUND.toggle();
                }
                if (SummitStatic.AUTOCRYSTAL.isEnabled()) {
                    SummitStatic.AUTOCRYSTAL.toggle();
                }
                if (SummitStatic.AURA.isEnabled()) {
                    SummitStatic.AURA.toggle();
                }
                BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath((Goal)GoalXZ.fromDirection(this.mc.player.getPositionVector(), this.mc.player.rotationYawHead, 100.0));
            }
            return;
        });
        this.OnStopUsingItem = new Listener<EventPlayerOnStoppedUsingItem>(event -> {
            if (this.isEating) {
                event.cancel();
            }
        });
    }
    
    private boolean baritoneIntoHole() {
        BlockPos bestHole = null;
        double dist = 100.0;
        final Vec3i playerPos = new Vec3i(this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ);
        for (int x = playerPos.getX() - this.Radius.getValue(); x < playerPos.getX() + this.Radius.getValue(); ++x) {
            for (int z = playerPos.getZ() - this.Radius.getValue(); z < playerPos.getZ() + this.Radius.getValue(); ++z) {
                for (int y = playerPos.getY() + this.Radius.getValue(); y > playerPos.getY() - this.Radius.getValue(); --y) {
                    final BlockPos blockPos = new BlockPos(x, y, z);
                    final double holeDist = this.mc.player.getDistanceSq(blockPos);
                    if (holeDist > 1.0) {
                        final IBlockState blockState = this.mc.world.getBlockState(blockPos);
                        Hole.HoleTypes l_Type = ESPUtil.isBlockValid(blockState, blockPos);
                        if (l_Type == Hole.HoleTypes.Bedrock) {
                            final IBlockState downBlockState = this.mc.world.getBlockState(blockPos.down());
                            if (downBlockState.getBlock() == Blocks.AIR) {
                                l_Type = ESPUtil.isBlockValid(downBlockState, blockPos);
                                if (l_Type != Hole.HoleTypes.None && holeDist < dist) {
                                    dist = holeDist;
                                    bestHole = blockPos;
                                }
                            }
                            else if (holeDist < dist) {
                                dist = holeDist;
                                bestHole = blockPos;
                            }
                        }
                    }
                }
            }
        }
        if (bestHole == null) {
            this.SendMessage("Couldn't find a proper hole");
            return false;
        }
        return true;
    }
}
