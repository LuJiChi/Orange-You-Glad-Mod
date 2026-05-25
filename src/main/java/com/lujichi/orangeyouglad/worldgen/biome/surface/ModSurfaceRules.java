package com.lujichi.orangeyouglad.worldgen.biome.surface;

// ...existing code...

import com.lujichi.orangeyouglad.block.ModBlocks;
import com.lujichi.orangeyouglad.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraftforge.registries.RegistryObject;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource QING_YUAN_STONE = makeStateRule(ModBlocks.QINGYUAN_STONE);

    public static SurfaceRules.RuleSource makeRules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        SurfaceRules.ConditionSource isQingyuanBiome = SurfaceRules.isBiome(ModBiomes.QINGYUAN_BIOME);

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(isQingyuanBiome,
                                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE,0,0.5), grassSurface),
                                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE,0.5,1.5), QING_YUAN_STONE)
                )))

        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }

    // Overload to accept RegistryObject<Block> so callers can pass mod-registered blocks
    private static <T extends Block> SurfaceRules.RuleSource makeStateRule(RegistryObject<T> block)
    {
        return makeStateRule(block.get());
    }
}
