package java.org.example.technomemorial.feature;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class CrownRenderer<T extends Entity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {

    private final Identifier TEXTURE = new Identifier("technomemorial","textures/entity/pig/crown.png");
    private final M model;

    public CrownRenderer(FeatureRendererContext<T, M> context, M model) {
        super(context);
        this.model = model;
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.hasCustomName() && entity.getCustomName().getString().equals("Technoblade")) {
            this.getContextModel().copyStateTo(this.model);
            this.model.animateModel(entity, limbAngle, limbDistance, tickDelta);
            this.model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(this.TEXTURE));
            this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}