package com.ores.core;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

import java.util.List;

public class ListMaterials {

    /**
     * Le record inclut maintenant minOreXP et maxOreXP.
     */
    public record Material(
            String name,
            boolean selfExist,
            MapColor color,
            SoundType sound,
            float blockDestroyTimeFactor,
            float blockExplosionResistanceFactor,
            float oreDestroyTimeFactor,
            float oreExplosionResistanceFactor,
            int minOreXP,
            int maxOreXP
    ) {}

    public static final List<Material> ALL_MATERIALS = List.of(
            new Material("coal", true, MapColor.COLOR_BLACK, SoundType.STONE, 1.0F, 1.0F, 1.0F, 1.0F, 0, 2),
            new Material("copper", false, MapColor.COLOR_ORANGE, SoundType.COPPER, 1.0F, 1.0F, 1.0F, 1.0F, 0, 0),
            new Material("iron", false, MapColor.METAL, SoundType.METAL, 1.0F, 1.0F, 1.0F, 1.0F, 0, 0),
            new Material("gold", false, MapColor.GOLD, SoundType.METAL, 1.0F, 1.0F, 1.0F, 1.0F, 0, 0),
            new Material("redstone", true, MapColor.COLOR_RED, SoundType.STONE, 1.0F, 1.0F, 1.0F, 1.0F, 1, 5),
            new Material("lapis", true, MapColor.LAPIS, SoundType.STONE, 1.0F, 1.0F, 1.0F, 1.0F, 2, 5),
            new Material("diamond", true, MapColor.DIAMOND, SoundType.METAL, 1.0F, 1.0F, 1.0F, 1.0F, 3, 7),
            new Material("emerald", true, MapColor.EMERALD, SoundType.METAL, 1.0F, 1.0F, 1.0F, 1.0F, 3, 7),
            new Material("quartz", true, MapColor.QUARTZ, SoundType.STONE, 1.0F, 1.0F, 1.0F, 1.0F, 2, 5),
            new Material("netherite", false, MapColor.COLOR_BLACK, SoundType.NETHERITE_BLOCK, 1.0F, 1.0F, 1.0F, 1.0F, 0, 0),
            new Material("tin", false, MapColor.COLOR_LIGHT_GRAY, SoundType.METAL, 0.8F, 0.8F, 0.8F, 0.8F, 0, 1),
            new Material("lead", false, MapColor.COLOR_GRAY, SoundType.METAL, 1.2F, 1.2F, 1.2F, 1.2F, 1, 2),
            new Material("silver", false, MapColor.COLOR_LIGHT_GRAY, SoundType.METAL, 1.1F, 1.1F, 1.1F, 1.1F, 2, 4),
            new Material("nickel", false, MapColor.COLOR_LIGHT_GRAY, SoundType.METAL, 1.1F, 1.1F, 1.1F, 1.1F, 1, 3)
    );
}
