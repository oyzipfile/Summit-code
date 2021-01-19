// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.guiclick2.effects;

import com.salhack.summit.util.render.RenderUtil;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.gui.ScaledResolution;
import java.util.Random;

public class Snow
{
    private static final Random random;
    private final int x;
    private int y;
    private int fallingSpeed;
    private float size;
    private int age;
    
    public Snow(final int x, final int y) {
        this.fallingSpeed = Snow.random.nextInt(2) + 1;
        this.size = (float)Math.random();
        this.age = Snow.random.nextInt(100) + 1;
        this.x = x;
        this.y = y;
    }
    
    public void Update(final ScaledResolution res) {
        if (this.y > res.getScaledHeight() + 10 || this.y < -10) {
            this.y = -10;
            this.fallingSpeed = Snow.random.nextInt(10) + 1;
            this.size = (float)Math.random() + 1.0f;
            return;
        }
        ++this.age;
        final float xOffset = MathHelper.sin(this.age / 16.0f) * 32.0f;
        final float result = this.x + xOffset;
        RenderUtil.drawCircle(result, (float)this.y, this.size + 1.0f, -7207161);
        RenderUtil.drawCircle(result, (float)this.y, this.size, -9216);
        this.y += this.fallingSpeed;
    }
    
    static {
        random = new Random();
    }
}
