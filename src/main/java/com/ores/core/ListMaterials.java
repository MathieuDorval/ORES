package com.ores.core;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import java.util.List;

public class ListMaterials {

    // La définition de chaque matériau est maintenant plus concise.
    public static final Material IRON = new Material("iron", MapColor.METAL, SoundType.METAL, false, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final Material GOLD = new Material("gold", MapColor.GOLD, SoundType.METAL, false, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final Material DIAMOND = new Material("diamond", MapColor.DIAMOND, SoundType.METAL, true, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final Material EMERALD = new Material("emerald", MapColor.EMERALD, SoundType.METAL, true, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final Material LAPIS = new Material("lapis", MapColor.LAPIS, SoundType.STONE, true, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final Material REDSTONE = new Material("redstone", MapColor.COLOR_RED, SoundType.STONE, false, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final Material COPPER = new Material("copper", MapColor.COLOR_ORANGE, SoundType.METAL, false, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final Material NETHERITE = new Material("netherite", MapColor.COLOR_BLACK, SoundType.NETHERITE_BLOCK, false, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final Material COAL = new Material("coal", MapColor.COLOR_BLACK, SoundType.STONE, true, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final Material QUARTZ = new Material("quartz", MapColor.QUARTZ, SoundType.STONE, true, 1.0F, 1.0F, 1.0F, 1.0F);

    public static final List<Material> ALL_MATERIALS = List.of(
            IRON, GOLD, DIAMOND, EMERALD, LAPIS, REDSTONE, COPPER, NETHERITE, COAL, QUARTZ
    );
}
