package com.ores.core;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import java.util.List;

public class ListVariants {

    public enum BlockType {
        BLOCK,
        FALLING_BLOCK,
        DROP_EXPERIENCE_BLOCK,
        ROTATED_PILLAR_BLOCK,
        REDSTONE_ORE_BLOCK
    }

    public record BlockVariant(
            String ID,
            BlockType blockType,
            float destroyTime,
            float explosionResistance,
            boolean requiresCorrectToolForDrops,
            boolean dropsOnFalling,
            Object friction,
            Object jumpFactor,
            Object speedFactor,
            Object lightLevel
    ) {}

    public record OreVariant(
            String ID,
            BlockType blockType,
            float destroyTime,
            float explosionResistance,
            boolean requiresCorrectToolForDrops,
            MapColor mapColor,
            SoundType soundType,
            NoteBlockInstrument instrument,
            Object lightLevel
    ) {}

    /**
     * Le paramètre stacksTo est maintenant de type Object.
     */
    public record ItemVariant(
            String ID,
            Rarity rarity,
            Object stacksTo,
            boolean fireResistant
    ) {}

    public static final List<BlockVariant> BLOCKS_STORAGE_VARIANTS = List.of(
            new BlockVariant("%s_block", BlockType.BLOCK, 5.0F, 6.0F, true, true, true, true, true, false),
            new BlockVariant("raw_%s_block", BlockType.BLOCK, 5.0F, 6.0F, true, true, true, true, true, false),
            new BlockVariant("dust_%s_block", BlockType.FALLING_BLOCK, 1.5F, 1.5F, false, false, true, true, true, false)
    );


    public static final List<OreVariant> BLOCKS_ORE_VARIANTS = List.of(
            new OreVariant("stone", BlockType.BLOCK, 3.0F, 3.0F, true, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("granite", BlockType.BLOCK, 3.0F, 3.0F, true, MapColor.DIRT, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("diorite", BlockType.BLOCK, 3.0F, 3.0F, true, MapColor.QUARTZ, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("andesite", BlockType.BLOCK, 3.0F, 3.0F, true, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("deepslate", BlockType.BLOCK, 4.5F, 3.0F, true, MapColor.DEEPSLATE, SoundType.DEEPSLATE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("tuff", BlockType.BLOCK, 3.0F, 3.0F, true, MapColor.TERRACOTTA_GRAY, SoundType.TUFF, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("cobblestone", BlockType.BLOCK, 3.0F, 3.0F, true, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("basalt", BlockType.BLOCK, 2.0F, 3.0F, true, MapColor.COLOR_BLACK, SoundType.BASALT, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("polished_basalt", BlockType.BLOCK, 2.0F, 3.0F, true, MapColor.COLOR_BLACK, SoundType.BASALT, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("calcite", BlockType.BLOCK, 2.0F, 3.0F, true, MapColor.TERRACOTTA_WHITE, SoundType.CALCITE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("netherrack", BlockType.BLOCK, 1.4F, 3.0F, true, MapColor.NETHER, SoundType.NETHERRACK, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("obsidian", BlockType.BLOCK, 50.0F, 1200.0F, true, MapColor.COLOR_BLACK, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("end_stone", BlockType.BLOCK, 3.0F, 5.0F, true, MapColor.SAND, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("dirt", BlockType.BLOCK, 1.5F, 3.0F, false, MapColor.DIRT, SoundType.GRAVEL, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("sand", BlockType.FALLING_BLOCK, 1.5F, 3.0F, false, MapColor.SAND, SoundType.SAND, NoteBlockInstrument.SNARE, false),
            new OreVariant("gravel", BlockType.FALLING_BLOCK, 1.6F, 3.0F, false, MapColor.STONE, SoundType.GRAVEL, NoteBlockInstrument.SNARE, false),
            new OreVariant("soul_sand", BlockType.BLOCK, 1.5F, 3.0F, false, MapColor.COLOR_BROWN, SoundType.SOUL_SAND, NoteBlockInstrument.SNARE, false),
            new OreVariant("soul_soil", BlockType.BLOCK, 1.5F, 3.0F, false, MapColor.COLOR_BROWN, SoundType.SOUL_SOIL, NoteBlockInstrument.SNARE, false)
    );

    public static final List<ItemVariant> ITEMS_SIMPLE_VARIANTS = List.of(
            new ItemVariant("raw_%s", Rarity.COMMON, false, false),
            new ItemVariant("%s_ingot", Rarity.COMMON, false, false),
            new ItemVariant("%s_nugget", Rarity.COMMON, false, false),
            new ItemVariant("%s_dust", Rarity.COMMON, false, false),
            new ItemVariant("%s_scrap", Rarity.UNCOMMON, false, false)
    );
}
