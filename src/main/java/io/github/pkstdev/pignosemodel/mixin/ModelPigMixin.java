package io.github.pkstdev.pignosemodel.mixin;

import net.minecraft.src.ModelPig;
import net.minecraft.src.ModelQuadruped;
import net.minecraft.src.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ModelPig.class, remap = false)
public class ModelPigMixin extends ModelQuadruped {
    @Unique public ModelRenderer nose;

    public ModelPigMixin(int i, float f) {
        super(i, f);
    }

    @Inject(method = "<init>()V", at = @At("RETURN"))
    public void onInit(CallbackInfo ci) {
        nose = new ModelRenderer(16, 16);
        nose.addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1);
        nose.setRotationPoint(0.0F, 12, -6F);
    }

    @Inject(method = "<init>(F)V", at = @At("RETURN"))
    public void onInit(float f, CallbackInfo ci) {
        nose = new ModelRenderer(16, 16);
        nose.addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1);
        nose.setRotationPoint(0.0F, 12, -6F);
    }

    @Override
    public void render(float limbSwing, float limbYaw, float ticksExisted, float headYaw, float headPitch, float scale) {
        super.render(limbSwing, limbYaw, ticksExisted, headYaw, headPitch, scale);
        nose.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbYaw, float ticksExisted, float headYaw, float headPitch, float scale) {
        super.setRotationAngles(limbSwing, limbYaw, ticksExisted, headYaw, headPitch, scale);
        nose.rotateAngleX = headPitch / 57.29578F;
        nose.rotateAngleY = headYaw / 57.29578F;
    }
}
