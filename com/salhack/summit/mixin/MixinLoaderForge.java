// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin;

import java.util.Map;
import com.salhack.summit.SummitMod;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.launch.MixinBootstrap;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

public class MixinLoaderForge implements IFMLLoadingPlugin
{
    private static boolean isObfuscatedEnvironment;
    
    public MixinLoaderForge() {
        MixinBootstrap.init();
        Mixins.addConfigurations(new String[] { "mixins.baritone.json", "mixins.summit.json" });
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        SummitMod.log.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }
    
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
        MixinLoaderForge.isObfuscatedEnvironment = data.get("runtimeDeobfuscationEnabled");
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
    
    static {
        MixinLoaderForge.isObfuscatedEnvironment = false;
    }
}
