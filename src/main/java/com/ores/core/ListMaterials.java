package com.ores.core;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import java.util.List;

public class ListMaterials {

    public record FoodFactors(float nutritionFactor, float saturationFactor, boolean canAlwaysEat) {}

    public record Material(
            String name,
            boolean selfExist,
            MapColor color,
            SoundType sound,
            float blockDestroyTimeFactor,
            float blockExplosionResistanceFactor,
            float oreDestroyTimeFactor,
            float oreExplosionResistanceFactor,
            int oreMinXP,
            int oreMaxXP,
            boolean isRedstoneLike,
            Object friction,
            Object jumpFactor,
            Object speedFactor,
            Object lightLevel,
            Rarity rarity,
            Object stacksTo,
            boolean fireResistant,
            FoodFactors foodFactors,
            Integer redstonePower
    ) {}

    public static final List<Material> ALL_MATERIALS = List.of(

            new Material("coal", true, MapColor.COLOR_BLACK, SoundType.STONE, 1.0F, 1.0F, 1.0F, 1.0F, 0, 2, false, false, false, false, false, Rarity.COMMON, false, false, null, null),
            new Material("copper", false, MapColor.COLOR_ORANGE, SoundType.COPPER, 1.0F, 1.0F, 1.0F, 1.0F, 0, 0, false, false, false, false, false, Rarity.COMMON, false, false, null, null),
            new Material("iron", false, MapColor.METAL, SoundType.METAL, 1.0F, 1.0F, 1.0F, 1.0F, 0, 0, false, false, false, false, false, Rarity.COMMON, false, false, null, null),
            new Material("gold", false, MapColor.GOLD, SoundType.METAL, 1.0F, 1.0F, 1.0F, 1.0F, 0, 0, false, false, false, false, false, Rarity.COMMON, false, false, new FoodFactors(1.0F, 1.2F, true), null),
            new Material("redstone", true, MapColor.COLOR_RED, SoundType.STONE, 1.0F, 1.0F, 1.0F, 1.0F, 1, 5, true, false, false, false, false, Rarity.COMMON, false, false, null, 15),
            new Material("lapis", true, MapColor.LAPIS, SoundType.STONE, 1.0F, 1.0F, 1.0F, 1.0F, 2, 5, false, false, false, false, false, Rarity.COMMON, false, false, null, null),
            new Material("diamond", true, MapColor.DIAMOND, SoundType.METAL, 1.0F, 1.0F, 1.0F, 1.0F, 3, 7, false, false, false, false, false, Rarity.COMMON, false, false, null, null),
            new Material("emerald", true, MapColor.EMERALD, SoundType.METAL, 1.0F, 1.0F, 1.0F, 1.0F, 3, 7, false, false, false, false, false, Rarity.COMMON, false, false, null, null),
            new Material("quartz", true, MapColor.QUARTZ, SoundType.STONE, 1.0F, 1.0F, 1.0F, 1.0F, 2, 5, false, false, false, false, false, Rarity.COMMON, false, false, null, null),
            new Material("netherite", false, MapColor.COLOR_BLACK, SoundType.NETHERITE_BLOCK, 1.0F, 1.0F, 1.0F, 1.0F, 0, 0, false, false, false, false, false, Rarity.COMMON, false, true, null, null),
            new Material("amethyst", true, MapColor.COLOR_PURPLE, SoundType.AMETHYST, 1.0F, 1.0F, 1.0F, 1.0F, 0, 0, false, false, false, false, false, Rarity.COMMON, false, false, null, null)
    );
}
