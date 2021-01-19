// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.movement;

import com.salhack.summit.util.MathUtil;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.init.MobEffects;
import com.salhack.summit.main.SummitStatic;
import com.salhack.summit.events.MinecraftEvent;
import com.salhack.summit.events.player.EventPlayerMove;
import com.salhack.summit.events.network.EventServerPacket;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class FastSwim extends Module
{
    public final Value<String> Mode;
    private int _ticks;
    @EventHandler
    private Listener<EventPlayerUpdate> onUpdate;
    @EventHandler
    private Listener<EventServerPacket> onPlayerPosLook;
    @EventHandler
    private Listener<EventPlayerMove> OnPlayerMove;
    
    public FastSwim() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "FastSwim"
        //     3: iconst_3       
        //     4: anewarray       Ljava/lang/String;
        //     7: dup            
        //     8: iconst_0       
        //     9: ldc             "FastSwim"
        //    11: aastore        
        //    12: dup            
        //    13: iconst_1       
        //    14: ldc             "fastswim"
        //    16: aastore        
        //    17: dup            
        //    18: iconst_2       
        //    19: ldc             "swim"
        //    21: aastore        
        //    22: ldc             "Allows you to swim faster than normal"
        //    24: ldc             "NONE"
        //    26: ldc             50164
        //    28: getstatic       com/salhack/summit/module/Module$ModuleType.MOVEMENT:Lcom/salhack/summit/module/Module$ModuleType;
        //    31: invokespecial   com/salhack/summit/module/Module.<init>:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/salhack/summit/module/Module$ModuleType;)V
        //    34: aload_0         /* this */
        //    35: new             Lcom/salhack/summit/module/Value;
        //    38: dup            
        //    39: ldc             "Mode"
        //    41: iconst_1       
        //    42: anewarray       Ljava/lang/String;
        //    45: dup            
        //    46: iconst_0       
        //    47: ldc             "M"
        //    49: aastore        
        //    50: ldc             "What mode should fast swim be on"
        //    52: ldc             "AAC"
        //    54: invokespecial   com/salhack/summit/module/Value.<init>:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
        //    57: putfield        com/salhack/summit/module/movement/FastSwim.Mode:Lcom/salhack/summit/module/Value;
        //    60: aload_0         /* this */
        //    61: iconst_0       
        //    62: putfield        com/salhack/summit/module/movement/FastSwim._ticks:I
        //    65: aload_0         /* this */
        //    66: new             Lcom/salhack/summit/events/bus/Listener;
        //    69: dup            
        //    70: aload_0         /* this */
        //    71: invokedynamic   BootstrapMethod #0, accept:(Lcom/salhack/summit/module/movement/FastSwim;)Ljava/util/function/Consumer;
        //    76: invokespecial   com/salhack/summit/events/bus/Listener.<init>:(Ljava/util/function/Consumer;)V
        //    79: putfield        com/salhack/summit/module/movement/FastSwim.onUpdate:Lcom/salhack/summit/events/bus/Listener;
        //    82: aload_0         /* this */
        //    83: new             Lcom/salhack/summit/events/bus/Listener;
        //    86: dup            
        //    87: aload_0         /* this */
        //    88: invokedynamic   BootstrapMethod #1, accept:(Lcom/salhack/summit/module/movement/FastSwim;)Ljava/util/function/Consumer;
        //    93: invokespecial   com/salhack/summit/events/bus/Listener.<init>:(Ljava/util/function/Consumer;)V
        //    96: putfield        com/salhack/summit/module/movement/FastSwim.onPlayerPosLook:Lcom/salhack/summit/events/bus/Listener;
        //    99: aload_0         /* this */
        //   100: new             Lcom/salhack/summit/events/bus/Listener;
        //   103: dup            
        //   104: aload_0         /* this */
        //   105: invokedynamic   BootstrapMethod #2, accept:(Lcom/salhack/summit/module/movement/FastSwim;)Ljava/util/function/Consumer;
        //   110: invokespecial   com/salhack/summit/events/bus/Listener.<init>:(Ljava/util/function/Consumer;)V
        //   113: putfield        com/salhack/summit/module/movement/FastSwim.OnPlayerMove:Lcom/salhack/summit/events/bus/Listener;
        //   116: aload_0         /* this */
        //   117: aload_0         /* this */
        //   118: invokevirtual   com/salhack/summit/module/movement/FastSwim.getMetaData:()Ljava/lang/String;
        //   121: invokevirtual   com/salhack/summit/module/movement/FastSwim.setMetaData:(Ljava/lang/String;)V
        //   124: aload_0         /* this */
        //   125: getfield        com/salhack/summit/module/movement/FastSwim.Mode:Lcom/salhack/summit/module/Value;
        //   128: ldc             "AAC"
        //   130: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   133: aload_0         /* this */
        //   134: getfield        com/salhack/summit/module/movement/FastSwim.Mode:Lcom/salhack/summit/module/Value;
        //   137: ldc             "Pulse"
        //   139: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   142: aload_0         /* this */
        //   143: getfield        com/salhack/summit/module/movement/FastSwim.Mode:Lcom/salhack/summit/module/Value;
        //   146: ldc             "Boost"
        //   148: invokevirtual   com/salhack/summit/module/Value.addString:(Ljava/lang/String;)V
        //   151: return         
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
    
    @Override
    public void onEnable() {
        super.onEnable();
        this._ticks = 0;
    }
    
    public enum Modes
    {
        Pulse, 
        AAC, 
        Boost;
    }
}
