// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ReportedException;
import net.minecraft.crash.CrashReport;
import com.salhack.summit.main.SummitStatic;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ TileEntityRendererDispatcher.class })
public class MixinTileEntityRendererDispatcher
{
    @Shadow
    private Tessellator batchBuffer;
    @Shadow
    private boolean drawingBatch;
    
    @Overwrite
    public void render(final TileEntity tileEntityIn, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float p_192854_10_) {
        final TileEntitySpecialRenderer<TileEntity> tileentityspecialrenderer = (TileEntitySpecialRenderer<TileEntity>)((TileEntityRendererDispatcher)this).getRenderer(tileEntityIn);
        if (tileentityspecialrenderer != null) {
            try {
                if (SummitStatic.STORAGEESP != null && SummitStatic.STORAGEESP.isEnabled()) {
                    SummitStatic.STORAGEESP.render(tileentityspecialrenderer, tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10_);
                }
                if (this.drawingBatch && tileEntityIn.hasFastRenderer()) {
                    tileentityspecialrenderer.renderTileEntityFast(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10_, this.batchBuffer.getBuffer());
                }
                else {
                    tileentityspecialrenderer.render(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10_);
                }
            }
            catch (Throwable throwable) {
                final CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Rendering Block Entity");
                final CrashReportCategory crashreportcategory = crashreport.makeCategory("Block Entity Details");
                tileEntityIn.addInfoToCrashReport(crashreportcategory);
                throw new ReportedException(crashreport);
            }
        }
    }
}
