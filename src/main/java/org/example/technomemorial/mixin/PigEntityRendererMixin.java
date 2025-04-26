package org.example.technomemorial.mixin;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PigEntityRenderer.class)
public abstract class PigEntityRendererMixin extends LivingEntityRenderer<PigEntity, EntityModel<PigEntity>> {

    private final Identifier TEXTURE = Identifier.of("technomemorial","textures/entity/pig/technoblade.png");

    public PigEntityRendererMixin(EntityRendererFactory.Context context, EntityModel<PigEntity> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Inject(
            method = "getTexture(Lnet/minecraft/entity/Entity;)Lnet/minecraft/util/Identifier;",
            at = @At("HEAD"),
            cancellable = true
    )
    private void overridePigTexture(Entity entity, CallbackInfoReturnable<Identifier> cir) {
        Text customName = entity.getName();
        if (customName != null && customName.getString().equals("Technoblade")) {
            cir.setReturnValue(TEXTURE);
        }
    }
}
