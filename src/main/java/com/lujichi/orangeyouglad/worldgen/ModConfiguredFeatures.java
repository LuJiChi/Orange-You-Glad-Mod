package com.lujichi.orangeyouglad.worldgen;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DANGO_ORE_KEY = registerKey("dango_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_FLARITE_ORE_KEY = registerKey("flarite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_KEY = registerKey("orange");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);


        List<OreConfiguration.TargetBlockState> overworldDangoOre = List.of(OreConfiguration.target(stoneReplaceable,
                ModBlocks.DANGO_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceable,
                                ModBlocks.DEEPSLATE_DANGO_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_DANGO_ORE_KEY, Feature.ORE, new OreConfiguration(overworldDangoOre, 6));

        List<OreConfiguration.TargetBlockState> overworldFlariteOre = List.of(OreConfiguration.target(stoneReplaceable,
                ModBlocks.FLARITE_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceable,
                                ModBlocks.DEEPSLATE_FLARITE_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_FLARITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldFlariteOre, 6));


        register(context, ORANGE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.ORANGE_LOG.get().defaultBlockState()),
                new StraightTrunkPlacer(4, 1, 2),
                BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get().defaultBlockState()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                           ResourceKey<ConfiguredFeature<?, ?>> key, F  feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
