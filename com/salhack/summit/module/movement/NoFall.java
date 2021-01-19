// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.movement;

import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import com.salhack.summit.util.entity.PlayerUtil;
import net.minecraft.init.Items;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.math.BlockPos;
import com.salhack.summit.events.MinecraftEvent;
import com.salhack.summit.events.player.EventPlayerMotionUpdate;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.network.EventClientPacket;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.util.Timer;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public final class NoFall extends Module
{
    public final Value<String> Mode;
    private Timer lastElytraFlyTimer;
    @EventHandler
    private Listener<EventClientPacket> onClientPacket;
    @EventHandler
    private Listener<EventPlayerMotionUpdate> onMotionUpdate;
    
    public NoFall() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "NoFall"
        //     3: iconst_1       
        //     4: anewarray       Ljava/lang/String;
        //     7: dup            
        //     8: iconst_0       
        //     9: ldc             "NoFallDamage"
        //    11: aastore        
        //    12: ldc             "Prevents fall damage"
        //    14: ldc             "NONE"
        //    16: ldc             4967004
        //    18: getstatic       com/salhack/summit/module/Module$ModuleType.MOVEMENT:Lcom/salhack/summit/module/Module$ModuleType;
        //    21: invokespecial   com/salhack/summit/module/Module.<init>:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/salhack/summit/module/Module$ModuleType;)V
        //    24: aload_0         /* this */
        //    25: new             Lcom/salhack/summit/module/Value;
        //    28: dup            
        //    29: ldc             "Mode"
        //    31: iconst_1       
        //    32: anewarray       Ljava/lang/String;
        //    35: dup            
        //    36: iconst_0       
        //    37: ldc             "M"
        //    39: aastore        
        //    40: ldc             "Mode to perform on"
        //    42: ldc             "Packet"
        //    44: invokespecial   com/salhack/summit/module/Value.<init>:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
        //    47: putfield        com/salhack/summit/module/movement/NoFall.Mode:Lcom/salhack/summit/module/Value;
        //    50: aload_0         /* this */
        //    51: new             Lcom/salhack/summit/util/Timer;
        //    54: dup            
        //    55: invokespecial   com/salhack/summit/util/Timer.<init>:()V
        //    58: putfield        com/salhack/summit/module/movement/NoFall.lastElytraFlyTimer:Lcom/salhack/summit/util/Timer;
        //    61: aload_0         /* this */
        //    62: new             Lcom/salhack/summit/events/bus/Listener;
        //    65: dup            
        //    66: aload_0         /* this */
        //    67: invokedynamic   BootstrapMethod #0, accept:(Lcom/salhack/summit/module/movement/NoFall;)Ljava/util/function/Consumer;
        //    72: invokespecial   com/salhack/summit/events/bus/Listener.<init>:(Ljava/util/function/Consumer;)V
        //    75: putfield        com/salhack/summit/module/movement/NoFall.onClientPacket:Lcom/salhack/summit/events/bus/Listener;
        //    78: aload_0         /* this */
        //    79: new             Lcom/salhack/summit/events/bus/Listener;
        //    82: dup            
        //    83: aload_0         /* this */
        //    84: invokedynamic   BootstrapMethod #1, accept:(Lcom/salhack/summit/module/movement/NoFall;)Ljava/util/function/Consumer;
        //    89: invokespecial   com/salhack/summit/events/bus/Listener.<init>:(Ljava/util/function/Consumer;)V
        //    92: putfield        com/salhack/summit/module/movement/NoFall.onMotionUpdate:Lcom/salhack/summit/events/bus/Listener;
        //    95: aload_0         /* this */
        //    96: getfield        com/salhack/summit/module/movement/NoFall.Mode:Lcom/salhack/summit/module/Value;
        //    99: ldc             "Packet"
        //   101: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   104: aload_0         /* this */
        //   105: getfield        com/salhack/summit/module/movement/NoFall.Mode:Lcom/salhack/summit/module/Value;
        //   108: ldc             "Bucket"
        //   110: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   113: aload_0         /* this */
        //   114: getfield        com/salhack/summit/module/movement/NoFall.Mode:Lcom/salhack/summit/module/Value;
        //   117: ldc             "Anti"
        //   119: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   122: aload_0         /* this */
        //   123: getfield        com/salhack/summit/module/movement/NoFall.Mode:Lcom/salhack/summit/module/Value;
        //   126: ldc             "AAC"
        //   128: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   131: aload_0         /* this */
        //   132: getfield        com/salhack/summit/module/movement/NoFall.Mode:Lcom/salhack/summit/module/Value;
        //   135: ldc             "NCP"
        //   137: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   140: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
