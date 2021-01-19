// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.world;

import com.salhack.summit.util.VecRotation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.block.state.IBlockState;
import java.util.Iterator;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;
import com.salhack.summit.util.RotationUtils;
import net.minecraft.util.math.Vec3d;
import net.minecraft.init.Blocks;
import com.salhack.summit.util.blocks.BlockUtils;
import com.salhack.summit.events.MinecraftEvent;
import com.salhack.summit.util.MathUtil;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import com.salhack.summit.util.render.RenderUtil;
import net.minecraft.util.math.AxisAlignedBB;
import com.salhack.summit.events.render.RenderEvent;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerMotionUpdate;
import com.salhack.summit.events.bus.Listener;
import net.minecraft.util.math.BlockPos;
import java.util.List;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class AutoTunnel extends Module
{
    public final Value<String> Mode;
    public final Value<String> MiningMode;
    private List<BlockPos> blocksToMine;
    @EventHandler
    private Listener<EventPlayerMotionUpdate> onMotionUpdates;
    @EventHandler
    private Listener<RenderEvent> OnRenderEvent;
    
    public AutoTunnel() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "AutoTunnel"
        //     3: iconst_1       
        //     4: anewarray       Ljava/lang/String;
        //     7: dup            
        //     8: iconst_0       
        //     9: ldc             ""
        //    11: aastore        
        //    12: ldc             "Automatically mines different kind of 2d tunnels, in the direction you're facing"
        //    14: ldc             "NONE"
        //    16: iconst_m1      
        //    17: getstatic       com/salhack/summit/module/Module$ModuleType.WORLD:Lcom/salhack/summit/module/Module$ModuleType;
        //    20: invokespecial   com/salhack/summit/module/Module.<init>:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/salhack/summit/module/Module$ModuleType;)V
        //    23: aload_0         /* this */
        //    24: new             Lcom/salhack/summit/module/Value;
        //    27: dup            
        //    28: ldc             "Mode"
        //    30: iconst_1       
        //    31: anewarray       Ljava/lang/String;
        //    34: dup            
        //    35: iconst_0       
        //    36: ldc             ""
        //    38: aastore        
        //    39: ldc             "Mode"
        //    41: ldc             "Tunnel1x2"
        //    43: invokespecial   com/salhack/summit/module/Value.<init>:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
        //    46: putfield        com/salhack/summit/module/world/AutoTunnel.Mode:Lcom/salhack/summit/module/Value;
        //    49: aload_0         /* this */
        //    50: new             Lcom/salhack/summit/module/Value;
        //    53: dup            
        //    54: ldc             "MiningMode"
        //    56: iconst_1       
        //    57: anewarray       Ljava/lang/String;
        //    60: dup            
        //    61: iconst_0       
        //    62: ldc             ""
        //    64: aastore        
        //    65: ldc             "Mode of mining to use"
        //    67: ldc             "Normal"
        //    69: invokespecial   com/salhack/summit/module/Value.<init>:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
        //    72: putfield        com/salhack/summit/module/world/AutoTunnel.MiningMode:Lcom/salhack/summit/module/Value;
        //    75: aload_0         /* this */
        //    76: new             Ljava/util/concurrent/CopyOnWriteArrayList;
        //    79: dup            
        //    80: invokespecial   java/util/concurrent/CopyOnWriteArrayList.<init>:()V
        //    83: putfield        com/salhack/summit/module/world/AutoTunnel.blocksToMine:Ljava/util/List;
        //    86: aload_0         /* this */
        //    87: new             Lcom/salhack/summit/events/bus/Listener;
        //    90: dup            
        //    91: aload_0         /* this */
        //    92: invokedynamic   BootstrapMethod #0, accept:(Lcom/salhack/summit/module/world/AutoTunnel;)Ljava/util/function/Consumer;
        //    97: invokespecial   com/salhack/summit/events/bus/Listener.<init>:(Ljava/util/function/Consumer;)V
        //   100: putfield        com/salhack/summit/module/world/AutoTunnel.onMotionUpdates:Lcom/salhack/summit/events/bus/Listener;
        //   103: aload_0         /* this */
        //   104: new             Lcom/salhack/summit/events/bus/Listener;
        //   107: dup            
        //   108: aload_0         /* this */
        //   109: invokedynamic   BootstrapMethod #1, accept:(Lcom/salhack/summit/module/world/AutoTunnel;)Ljava/util/function/Consumer;
        //   114: invokespecial   com/salhack/summit/events/bus/Listener.<init>:(Ljava/util/function/Consumer;)V
        //   117: putfield        com/salhack/summit/module/world/AutoTunnel.OnRenderEvent:Lcom/salhack/summit/events/bus/Listener;
        //   120: aload_0         /* this */
        //   121: aload_0         /* this */
        //   122: invokevirtual   com/salhack/summit/module/world/AutoTunnel.getMetaData:()Ljava/lang/String;
        //   125: invokevirtual   com/salhack/summit/module/world/AutoTunnel.setMetaData:(Ljava/lang/String;)V
        //   128: aload_0         /* this */
        //   129: getfield        com/salhack/summit/module/world/AutoTunnel.Mode:Lcom/salhack/summit/module/Value;
        //   132: ldc             "Tunnel1x2"
        //   134: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   137: aload_0         /* this */
        //   138: getfield        com/salhack/summit/module/world/AutoTunnel.Mode:Lcom/salhack/summit/module/Value;
        //   141: ldc             "Tunnel2x2"
        //   143: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   146: aload_0         /* this */
        //   147: getfield        com/salhack/summit/module/world/AutoTunnel.Mode:Lcom/salhack/summit/module/Value;
        //   150: ldc             "Tunnel2x3"
        //   152: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   155: aload_0         /* this */
        //   156: getfield        com/salhack/summit/module/world/AutoTunnel.Mode:Lcom/salhack/summit/module/Value;
        //   159: ldc             "Tunnel3x3"
        //   161: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   164: aload_0         /* this */
        //   165: getfield        com/salhack/summit/module/world/AutoTunnel.MiningMode:Lcom/salhack/summit/module/Value;
        //   168: ldc             "Normal"
        //   170: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   173: aload_0         /* this */
        //   174: getfield        com/salhack/summit/module/world/AutoTunnel.MiningMode:Lcom/salhack/summit/module/Value;
        //   177: ldc             "Packet"
        //   179: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   182: return         
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
