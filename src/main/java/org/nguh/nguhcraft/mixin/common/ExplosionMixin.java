package org.nguh.nguhcraft.mixin.common;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.nguh.nguhcraft.protect.ProtectionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {
    /** Disable exploding protected blocks. */
    @Redirect(
        method = "collectBlocksAndDamageEntities",
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/world/explosion/ExplosionBehavior.getBlastResistance (Lnet/minecraft/world/explosion/Explosion;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/fluid/FluidState;)Ljava/util/Optional;",
            ordinal = 0
        )
    )
    private Optional<Float> redirect$collectBlocksAndDamageEntities(
        ExplosionBehavior EB,
        Explosion E,
        BlockView View,
        BlockPos Pos,
        BlockState St,
        FluidState FSt
    ) {
        // Setting the blast resistance to infinity will prevent the block from being destroyed.
        if (View instanceof World W && ProtectionManager.IsProtectedBlock(W, Pos))
            return Optional.of(Float.POSITIVE_INFINITY);
        return EB.getBlastResistance(E, View, Pos, St, FSt);
    }
}
