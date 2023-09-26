package io.github.andrew6rant.clientcarpetslabs.mixin.client;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.fabricmc.fabric.impl.client.indigo.Indigo;
import net.fabricmc.fabric.impl.client.indigo.renderer.accessor.AccessChunkRendererRegion;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarpetBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.chunk.ChunkBuilder;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ChunkBuilder.BuiltChunk.RebuildTask.class, priority = 1500)
public class ChunkBuilderBuiltChunkRebuildTaskMixinSquared {
    @TargetHandler(
            mixin = "net.fabricmc.fabric.mixin.client.indigo.renderer.ChunkBuilderBuiltChunkRebuildTaskMixin",
            name = "hookChunkBuildTessellate",
            print = true
    )
    @Inject(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true)
    private void clientcarpetslabs$injectCarpetRender(BlockRenderManager renderManager, BlockState blockState, BlockPos blockPos, BlockRenderView blockView, MatrixStack matrix, VertexConsumer bufferBuilder, boolean checkSides, Random random, CallbackInfo ci) {
        if (blockState.getRenderType() == BlockRenderType.MODEL) {
            if (blockState.getBlock() instanceof CarpetBlock) {
                // Bypass Fabric's rendering for carpet blocks
                renderManager.renderBlock(blockState, blockPos, blockView, matrix, bufferBuilder, checkSides, random);
                ci.cancel();
            }
        }
    }
}
