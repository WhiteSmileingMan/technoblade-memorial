package org.example.technomemorial.mixin;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings({"unchecked", "rawtypes"})
@Mixin(PigEntityRenderer.class)
public abstract class PigEntityRendererMixin extends LivingEntityRenderer {
    public PigEntityRendererMixin(EntityRendererFactory.Context ctx, EntityModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/pig/technoblade.png");

    @Inject(
            method = "getTexture(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;)Lnet/minecraft/util/Identifier;",
            at = @At("HEAD"), cancellable = true
    )
    private void overridePigTexture(LivingEntityRenderState state, CallbackInfoReturnable<Identifier> cir) {
        if (state.customName != null && "Technoblade".equals(state.customName.getString())) {
            cir.setReturnValue(TEXTURE);
        }
    }
}
