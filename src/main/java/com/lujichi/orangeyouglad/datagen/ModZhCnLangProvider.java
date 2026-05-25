package com.lujichi.orangeyouglad.datagen;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import com.lujichi.orangeyouglad.effect.ModEffects;
import com.lujichi.orangeyouglad.entity.ModEntities;
import com.lujichi.orangeyouglad.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModZhCnLangProvider extends LanguageProvider {
        public ModZhCnLangProvider(PackOutput output) {
            super(output, OrangeYouGladMod.MOD_ID, "zh_cn");
        }

    @Override
    protected void addTranslations() {
        add(ModItems.ORANGE.get(), "橙子");
        add(ModItems.SALTED_FISH.get(), "咸鱼");
        add(ModItems.CHICKEN_WING.get(), "鸡翅");
        add(ModItems.COOKED_CHICKEN_WING.get(), "熟鸡翅");
        add(ModItems.RAW_DANGO.get(), "粗团子");
        add(ModItems.DANGO_INGOT.get(), "团子锭");
        add(ModItems.FLARITE.get(), "才火煤");
        add(ModItems.ONION.get(), "洋葱");
        add(ModItems.ORANGE_BRAISED_CHICKEN_WING.get(), "橙子卤鸡翅");


        add(ModBlocks.ORANGE_PLANKS.get(), "橙树木板");
        add(ModBlocks.ORANGE_LOG.get(), "橙树原木");
        add(ModBlocks.ORANGE_WOOD.get(), "橙木");
        add(ModBlocks.STRIPPED_ORANGE_LOG.get(), "去皮橙树原木");
        add(ModBlocks.STRIPPED_ORANGE_WOOD.get(), "去皮橙木");
        add(ModBlocks.ORANGE_LEAVES.get(), "橙树树叶");
        add(ModBlocks.ONION_CROP.get(), "洋葱作物");
        add(ModBlocks.ORANGE_SAPLING.get(), "橙树树苗");




        add(ModBlocks.QINGYUAN_STONE.get(), "清源石");
        add(ModBlocks.DANGO_ORE.get(), "团子矿");
        add(ModBlocks.DEEPSLATE_DANGO_ORE.get(), "深团子矿");
        add(ModBlocks.DANGO_INGOT_BLOCK.get(), "团子块");
        add(ModBlocks.FLARITE_ORE.get(), "才火矿");
        add(ModBlocks.DEEPSLATE_FLARITE_ORE.get(), "深才火矿");
        add(ModBlocks.FLARITE_BLOCK.get(), "才火块");
        add(ModBlocks.FLARITE_FURNACE.get(), "才火炉");

        add(ModBlocks.ORANGE_STAIRS.get(), "橙木楼梯");
        add(ModBlocks.ORANGE_SLAB.get(), "橙木台阶");
        add(ModBlocks.ORANGE_BUTTON.get(), "橙木按钮");
        add(ModBlocks.ORANGE_PRESSURE_PLATE.get(), "橙木压力板");
        add(ModBlocks.ORANGE_FENCE.get(), "橙木栅栏");
        add(ModBlocks.ORANGE_FENCE_GATE.get(), "橙木栅栏门");
        add(ModBlocks.ORANGE_DOOR.get(), "橙木门");
        add(ModBlocks.ORANGE_TRAPDOOR.get(), "橙木活板门");

        add(ModBlocks.QINGYUAN_STONE_WALL.get(), "清源石墙");
        add(ModBlocks.QINGYUAN_STONE_STAIRS.get(), "清源石质楼梯");
        add(ModBlocks.QINGYUAN_STONE_SLAB.get(), "清源石台阶");
        add(ModBlocks.QINGYUAN_STONE_BUTTON.get(), "清源石质按钮");
        add(ModBlocks.QINGYUAN_STONE_PRESSURE_PLATE.get(), "清源石质压力板");



        add(ModItems.DANGO_SWORD.get(), "团子剑");
        add(ModItems.DANGO_PICKAXE.get(), "团子镐");
        add(ModItems.DANGO_AXE.get(), "团子斧");
        add(ModItems.DANGO_SHOVEL.get(), "团子铁锹");
        add(ModItems.DANGO_HOE.get(), "团子锄");
        add(ModItems.TUANZI_SPAWN_EGG.get(), "团子刷怪蛋");
        add(ModItems.HORNBILL_SPAWN_EGG.get(), "犀鸟刷怪蛋");

        add(ModEffects.HARVEST.get(), "丰收");

        add(ModEntities.TUANZI.get(), "团子");
        add(ModEntities.HORNBILL.get(), "犀鸟");

        add("painting.orangeyouglad_mod.tuanzi_painting.title", "团子画");

        add("tooltip.flarite_furnace.talent", "才华值:%s/%s");
        add("jei.orangeyouglad_mod.talent_cost", "才华值消耗: %s");
        add("jei.orangeyouglad_mod.talent_bar_hint", "才火煤=1000才华值");


        add("itemGroup.orangeyouglad_tab", "橙啦！");
    }
}
