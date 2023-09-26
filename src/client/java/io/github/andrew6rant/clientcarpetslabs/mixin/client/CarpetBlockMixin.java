package io.github.andrew6rant.clientcarpetslabs.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.StairShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.SlabBlock.TYPE;

@Environment(EnvType.CLIENT)
@Mixin(CarpetBlock.class)
public class CarpetBlockMixin {
    @Unique private static final VoxelShape SLAB_SHAPE = Block.createCuboidShape(0.0, -8.0, 0.0, 16.0, -7.0, 16.0);
    @Unique private static final VoxelShape STAIR_SHAPE = Block.createCuboidShape(0.0, -8.0, 0.0, 16.0, -7.0, 16.0);


    @Unique private static final VoxelShape STAIR_NORTH_WEST_CORNER_LOW = Block.createCuboidShape(0.0, -8.0, 0.0, 8.0, -7.0, 8.0);
    @Unique private static final VoxelShape STAIR_NORTH_EAST_CORNER_LOW = Block.createCuboidShape(8.0, -8.0, 0.0, 16.0, -7.0, 8.0);
    @Unique private static final VoxelShape STAIR_SOUTH_WEST_CORNER_LOW = Block.createCuboidShape(0.0, -8.0, 8.0, 8.0, -7.0, 16.0);
    @Unique private static final VoxelShape STAIR_SOUTH_EAST_CORNER_LOW = Block.createCuboidShape(8.0, -8.0, 8.0, 16.0, -7.0, 16.0);

    @Unique private static final VoxelShape STAIR_NORTH_WEST_CORNER_HIGH = Block.createCuboidShape(0.0, 0.0, 0.0, 8.0, 1.0, 8.0);
    @Unique private static final VoxelShape STAIR_NORTH_EAST_CORNER_HIGH = Block.createCuboidShape(8.0, 0.0, 0.0, 16.0, 1.0, 8.0);
    @Unique private static final VoxelShape STAIR_SOUTH_WEST_CORNER_HIGH = Block.createCuboidShape(0.0, 0.0, 8.0, 8.0, 1.0, 16.0);
    @Unique private static final VoxelShape STAIR_SOUTH_EAST_CORNER_HIGH = Block.createCuboidShape(8.0, 0.0, 8.0, 16.0, 1.0, 16.0);



    @Inject(method = "getOutlineShape(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/ShapeContext;)Lnet/minecraft/util/shape/VoxelShape;",
        at = @At("HEAD"), cancellable = true)
    public void clientcarpetstairs$injectCarpetHitbox(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        BlockState belowState = world.getBlockState(pos.down());
        Block block = belowState.getBlock();
        if (block instanceof SlabBlock) {
            if (belowState.get(TYPE) == SlabType.BOTTOM) {
                cir.setReturnValue(SLAB_SHAPE);
            }
        } else if (block instanceof StairsBlock) {
            if (belowState.get(StairsBlock.HALF) == BlockHalf.TOP) return;
            switch (belowState.get(StairsBlock.FACING)) {
                case NORTH -> {
                    cir.setReturnValue(VoxelShapes.union(STAIR_SOUTH_EAST_CORNER_LOW, STAIR_SOUTH_WEST_CORNER_LOW, STAIR_NORTH_EAST_CORNER_HIGH, STAIR_NORTH_WEST_CORNER_HIGH));
                }
                case SOUTH -> {
                    cir.setReturnValue(STAIR_SOUTH_EAST_CORNER_HIGH);
                }
                case EAST -> {
                    cir.setReturnValue(STAIR_SOUTH_WEST_CORNER_HIGH);
                }
                case WEST -> {
                    cir.setReturnValue(STAIR_NORTH_EAST_CORNER_HIGH);
                }
            }
            //cir.setReturnValue(STAIR_SHAPE);
        }
    }
}
