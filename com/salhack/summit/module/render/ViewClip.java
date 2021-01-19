// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.render;

import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class ViewClip extends Module
{
    public final Value<Float> Distance;
    
    public ViewClip() {
        super("ViewClip", new String[] { "F5", "CameraClip" }, "Prevents the third person camera from ray-tracing", "NONE", 14127965, ModuleType.RENDER);
        this.Distance = new Value<Float>("Distance", new String[] { "Length", "Lenght", "Far", "d", "l" }, "How much distance should viewclip give", 3.5f, 0.0f, 10.0f, 1.0f);
    }
}
