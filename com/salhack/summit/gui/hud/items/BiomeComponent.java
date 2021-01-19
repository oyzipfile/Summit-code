// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.util.math.BlockPos;
import com.salhack.summit.managers.HudManager;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class BiomeComponent extends OptionalListHudComponent
{
    public BiomeComponent() {
        super("Biome", 2.0f, 95.0f, 50.0f, 10.0f);
        this.setCurrentCornerList(HudManager.Get().GetModList("BottomLeft"));
        this.setEnabled(true);
    }
    
    @Override
    public void onUpdate() {
        final BlockPos pos = this.mc.player.getPosition();
        final Chunk chunk = this.mc.world.getChunk(pos);
        final Biome biome = chunk.getBiome(pos, this.mc.world.getBiomeProvider());
        this.cornerItem.setName(biome.getBiomeName());
    }
}
