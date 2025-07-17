package com.ores.core;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import java.util.List;

/**
 * Représente un matériau du mod, avec toutes ses propriétés de base.
 * Utilise un "record" pour une définition concise et immuable.
 */
public record ListMaterials(
        String name,
        MapColor color,
        SoundType sound,
        boolean selfExist,
        float oreDestroyTimeFactor,
        float oreExplosionResistanceFactor,
        float blockDestroyTimeFactor,
        float blockExplosionResistanceFactor
) {
    // --- DÉFINITIONS DES MATÉRIAUX ---
    public static final ListMaterials IRON = new ListMaterials("iron", MapColor.METAL, SoundType.METAL, false, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final ListMaterials GOLD = new ListMaterials("gold", MapColor.GOLD, SoundType.METAL, false, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final ListMaterials DIAMOND = new ListMaterials("diamond", MapColor.DIAMOND, SoundType.METAL, true, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final ListMaterials EMERALD = new ListMaterials("emerald", MapColor.EMERALD, SoundType.METAL, true, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final ListMaterials LAPIS = new ListMaterials("lapis", MapColor.LAPIS, SoundType.STONE, true, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final ListMaterials REDSTONE = new ListMaterials("redstone", MapColor.COLOR_RED, SoundType.STONE, false, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final ListMaterials COPPER = new ListMaterials("copper", MapColor.COLOR_ORANGE, SoundType.METAL, false, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final ListMaterials NETHERITE = new ListMaterials("netherite", MapColor.COLOR_BLACK, SoundType.NETHERITE_BLOCK, false, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final ListMaterials COAL = new ListMaterials("coal", MapColor.COLOR_BLACK, SoundType.STONE, true, 1.0F, 1.0F, 1.0F, 1.0F);
    public static final ListMaterials QUARTZ = new ListMaterials("quartz", MapColor.QUARTZ, SoundType.STONE, true, 1.0F, 1.0F, 1.0F, 1.0F);

    /**
     * Une liste immuable contenant tous les matériaux définis ci-dessus.
     * C'est cette liste qui sera utilisée pour générer les blocs et items.
     */
    public static final List<ListMaterials> ALL_MATERIALS = List.of(
            IRON, GOLD, DIAMOND, EMERALD, LAPIS, REDSTONE, COPPER, NETHERITE, COAL, QUARTZ
    );
}
