// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.combat;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.CPacketUseEntity;
import com.salhack.summit.events.MinecraftEvent;
import com.salhack.summit.events.network.EventClientPacket;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.util.Timer;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class Criticals extends Module
{
    public final Value<String> Mode;
    private Timer timer;
    @EventHandler
    private Listener<EventPlayerUpdate> onPlayerUpdate;
    @EventHandler
    private Listener<EventClientPacket> onClientPacket;
    
    public Criticals() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "Criticals"
        //     3: iconst_1       
        //     4: anewarray       Ljava/lang/String;
        //     7: dup            
        //     8: iconst_0       
        //     9: ldc             "BS"
        //    11: aastore        
        //    12: ldc             "Tries to crit your oponent on attack by spoofing positions"
        //    14: ldc             "NONE"
        //    16: ldc             11985571
        //    18: getstatic       com/salhack/summit/module/Module$ModuleType.COMBAT:Lcom/salhack/summit/module/Module$ModuleType;
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
        //    40: ldc             "Mode to change to for criticals"
        //    42: ldc             "Offset"
        //    44: invokespecial   com/salhack/summit/module/Value.<init>:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
        //    47: putfield        com/salhack/summit/module/combat/Criticals.Mode:Lcom/salhack/summit/module/Value;
        //    50: aload_0         /* this */
        //    51: new             Lcom/salhack/summit/util/Timer;
        //    54: dup            
        //    55: invokespecial   com/salhack/summit/util/Timer.<init>:()V
        //    58: putfield        com/salhack/summit/module/combat/Criticals.timer:Lcom/salhack/summit/util/Timer;
        //    61: aload_0         /* this */
        //    62: new             Lcom/salhack/summit/events/bus/Listener;
        //    65: dup            
        //    66: aload_0         /* this */
        //    67: invokedynamic   BootstrapMethod #0, accept:(Lcom/salhack/summit/module/combat/Criticals;)Ljava/util/function/Consumer;
        //    72: invokespecial   com/salhack/summit/events/bus/Listener.<init>:(Ljava/util/function/Consumer;)V
        //    75: putfield        com/salhack/summit/module/combat/Criticals.onPlayerUpdate:Lcom/salhack/summit/events/bus/Listener;
        //    78: aload_0         /* this */
        //    79: new             Lcom/salhack/summit/events/bus/Listener;
        //    82: dup            
        //    83: aload_0         /* this */
        //    84: invokedynamic   BootstrapMethod #1, accept:(Lcom/salhack/summit/module/combat/Criticals;)Ljava/util/function/Consumer;
        //    89: invokespecial   com/salhack/summit/events/bus/Listener.<init>:(Ljava/util/function/Consumer;)V
        //    92: putfield        com/salhack/summit/module/combat/Criticals.onClientPacket:Lcom/salhack/summit/events/bus/Listener;
        //    95: aload_0         /* this */
        //    96: aload_0         /* this */
        //    97: invokevirtual   com/salhack/summit/module/combat/Criticals.getMetaData:()Ljava/lang/String;
        //   100: invokevirtual   com/salhack/summit/module/combat/Criticals.setMetaData:(Ljava/lang/String;)V
        //   103: aload_0         /* this */
        //   104: getfield        com/salhack/summit/module/combat/Criticals.Mode:Lcom/salhack/summit/module/Value;
        //   107: ldc             "Packet"
        //   109: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   112: aload_0         /* this */
        //   113: getfield        com/salhack/summit/module/combat/Criticals.Mode:Lcom/salhack/summit/module/Value;
        //   116: ldc             "Jump"
        //   118: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   121: aload_0         /* this */
        //   122: getfield        com/salhack/summit/module/combat/Criticals.Mode:Lcom/salhack/summit/module/Value;
        //   125: ldc             "Offset"
        //   127: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   130: aload_0         /* this */
        //   131: getfield        com/salhack/summit/module/combat/Criticals.Mode:Lcom/salhack/summit/module/Value;
        //   134: ldc             "MiniJump"
        //   136: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   139: aload_0         /* this */
        //   140: getfield        com/salhack/summit/module/combat/Criticals.Mode:Lcom/salhack/summit/module/Value;
        //   143: ldc             "Hypixel"
        //   145: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   148: aload_0         /* this */
        //   149: getfield        com/salhack/summit/module/combat/Criticals.Mode:Lcom/salhack/summit/module/Value;
        //   152: ldc             "Aura"
        //   154: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   157: aload_0         /* this */
        //   158: getfield        com/salhack/summit/module/combat/Criticals.Mode:Lcom/salhack/summit/module/Value;
        //   161: ldc             "NCPPacket"
        //   163: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   166: return         
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
    
    @Override
    public String getMetaData() {
        return String.valueOf(this.Mode.getValue());
    }
}
