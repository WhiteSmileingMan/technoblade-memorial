package java.org.example.technomemorial.mixin;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PigEntityModel;
import org.example.technomemorial.feature.CrownRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings({"unchecked", "rawtypes"})
@Mixin(PigEntityRenderer.class)
public abstract class PigCrownRendererMixin extends LivingEntityRenderer {
    public PigCrownRendererMixin(EntityRendererFactory.Context ctx, EntityModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRendererFactory$Context;)V", at = @At("TAIL"))
    private void addTechnoCrownFeature(EntityRendererFactory.Context context, CallbackInfo ci) {
        this.addFeature(new CrownRenderer(this, new PigEntityModel(context.getPart(EntityModelLayers.PIG_SADDLE))));
    }
}