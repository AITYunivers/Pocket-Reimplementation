package io.github.yunivers.pocket.mixin.item;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.yunivers.pocket.Pocket;
import net.minecraft.item.DyeItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DyeItem.class)
public class DyeItemMixin
{
    @WrapOperation(
        method = "useOnBlock",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;setBlock(IIII)Z",
            ordinal = 1
        )
    )
    public boolean pocket$useOnBlock_injectBlueRose(World instance, int x, int y, int z, int blockId, Operation<Boolean> original)
    {
        if (Pocket.RANDOM.nextInt(10) == 0)
            return original.call(instance, x, y, z, Pocket.BLOCK_ROSE_BLUE.id);
        return original.call(instance, x, y, z, blockId);
    }
}
