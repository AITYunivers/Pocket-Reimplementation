package io.github.yunivers.pocket.mixin.world.gen.chunk;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.yunivers.pocket.Pocket;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkSource;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import net.minecraft.world.gen.feature.PlantPatchFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@SuppressWarnings("DiscouragedShift")
@Mixin(OverworldChunkGenerator.class)
public class OverworldChunkGeneratorMixin
{
    @Shadow private Random random;

    @Shadow private World world;

    @Inject(
        method = "decorate",
        at = @At(
            value = "INVOKE",
            target = "Ljava/util/Random;nextInt(I)I",
            ordinal = 54,
            shift = At.Shift.BEFORE
        )
    )
    public void pocket$decorate_spawnBlueRoses(ChunkSource source, int chunkX, int chunkZ, CallbackInfo ci)
    {
        // 10x rarer than the Red Rose
        if (this.random.nextInt(20) == 0)
        {
            int patchX = chunkX * 16 + this.random.nextInt(16) + 8;
            int patchY = this.random.nextInt(128);
            int patchZ = chunkZ * 16 + this.random.nextInt(16) + 8;
            new PlantPatchFeature(Pocket.BLOCK_ROSE_BLUE.id).generate(this.world, this.random, patchX, patchY, patchZ);
        }
    }
}
