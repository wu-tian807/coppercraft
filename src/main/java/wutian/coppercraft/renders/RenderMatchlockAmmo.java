package wutian.coppercraft.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import org.joml.*;
import org.joml.Vector3f;;
import org.joml.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import wutian.coppercraft.CopperCraft;
import wutian.coppercraft.entities.EntityMatchlockAmmo;

public class RenderMatchlockAmmo extends EntityRenderer<EntityMatchlockAmmo> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(CopperCraft.MODID,"textures/entity/matchlock_ammo.png");
    private static final RenderType renderType = RenderType.entityCutoutNoCull(TEXTURE);
    private static final float MIN_CAMERA_DISTANCE_SQUARED = 12.25F;
    private final ItemRenderer itemRenderer;

    private final float scale;

    private final boolean fullBright;

    public RenderMatchlockAmmo(EntityRendererProvider.Context manager)
    {
        super(manager);
        this.itemRenderer=manager.getItemRenderer();
        this.scale=0.25F;
        this.fullBright=false;
    }

    @Override
    protected int getSkyLightLevel(EntityMatchlockAmmo p_239381_1_, BlockPos p_239381_2_) {
        return 15;
    }

    @Override
    protected int getBlockLightLevel(EntityMatchlockAmmo pEntity, BlockPos pPos) {
        return 15;
    }

    @Override
    public ResourceLocation getTextureLocation(EntityMatchlockAmmo p_110775_1_) {
        return TEXTURE;
    }

    @Override
    public void render(EntityMatchlockAmmo entityIn, float entityYaw, float partialTicks, PoseStack poseStackIn, MultiBufferSource bufferIn, int packedLightIn) {

        if (entityIn.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(entityIn) < 12.25D)) {

            poseStackIn.pushPose();

            poseStackIn.scale(this.scale, this.scale, this.scale);

            poseStackIn.mulPose(this.entityRenderDispatcher.cameraOrientation());

            poseStackIn.mulPose(Axis.YP.rotationDegrees(180.0F));

            this.itemRenderer.renderStatic(entityIn.getItem(), ItemTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, poseStackIn, bufferIn, entityIn.getId());

            poseStackIn.popPose();

            super.render(entityIn, entityYaw, partialTicks, poseStackIn, bufferIn, packedLightIn);

        }

    }



    private static void vertexRender(VertexConsumer p_229045_0_, Matrix4f p_229045_1_, Matrix3f p_229045_2_, int p_229045_3_, float p_229045_4_, int p_229045_5_, int p_229045_6_, int p_229045_7_) {

        p_229045_0_.vertex(p_229045_1_, p_229045_4_ - 0.5F, (float)p_229045_5_ - 0.25F, 0.0F).color(255, 255, 255, 255).overlayCoords(OverlayTexture.NO_OVERLAY).normal(p_229045_2_, 0.0F, 1.0F, 0.0F).endVertex();

    }
}
