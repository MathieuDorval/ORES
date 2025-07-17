package com.ores.core;

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
            String nameFormat,
            BlockType blockType,
            float destroyTime,
            float explosionResistance,
            Object friction,
            boolean dropsOnBreak
    ) {}

    /**
     * Le record OreVariant utilise maintenant l'énumération BlockType.
     */
    public record OreVariant(
            String name,
            BlockType blockType, // Remplacement de isFalling par BlockType
            float destroyTime,
            float explosionResistance,
            MapColor mapColor,
            SoundType soundType,
            NoteBlockInstrument instrument
    ) {}

    public record ItemVariant(
            String nameFormat
    ) {}

    public static final List<BlockVariant> BLOCKS_STORAGE_VARIANTS = List.of(
            new BlockVariant("%s_block", BlockType.BLOCK, 5.0F, 6.0F, 0.6F, true),
            new BlockVariant("raw_%s_block", BlockType.BLOCK, 5.0F, 6.0F, false, true),
            new BlockVariant("dust_%s_block", BlockType.FALLING_BLOCK, 1.5F, 1.5F, false, true)
    );

    /**
     * La liste des minerais utilise maintenant BlockType pour définir leur comportement.
     */
    public static final List<OreVariant> BLOCKS_ORE_VARIANTS = List.of(
            new OreVariant("stone", BlockType.BLOCK, 3.0F, 3.0F, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("granite", BlockType.BLOCK, 3.0F, 3.0F, MapColor.DIRT, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("diorite", BlockType.BLOCK, 3.0F, 3.0F, MapColor.QUARTZ, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("andesite", BlockType.BLOCK, 3.0F, 3.0F, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("deepslate", BlockType.BLOCK, 4.5F, 3.0F, MapColor.DEEPSLATE, SoundType.DEEPSLATE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("tuff", BlockType.BLOCK, 3.0F, 3.0F, MapColor.TERRACOTTA_GRAY, SoundType.TUFF, NoteBlockInstrument.BASEDRUM),
            new OreVariant("cobblestone", BlockType.BLOCK, 3.0F, 3.0F, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("basalt", BlockType.BLOCK, 2.0F, 3.0F, MapColor.COLOR_BLACK, SoundType.BASALT, NoteBlockInstrument.BASEDRUM),
            new OreVariant("polished_basalt", BlockType.BLOCK, 2.0F, 3.0F, MapColor.COLOR_BLACK, SoundType.BASALT, NoteBlockInstrument.BASEDRUM),
            new OreVariant("calcite", BlockType.BLOCK, 2.0F, 3.0F, MapColor.TERRACOTTA_WHITE, SoundType.CALCITE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("netherrack", BlockType.BLOCK, 1.4F, 3.0F, MapColor.NETHER, SoundType.NETHERRACK, NoteBlockInstrument.BASEDRUM),
            new OreVariant("obsidian", BlockType.BLOCK, 50.0F, 1200.0F, MapColor.COLOR_BLACK, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("end_stone", BlockType.BLOCK, 3.0F, 5.0F, MapColor.SAND, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("dirt", BlockType.BLOCK, 1.5F, 3.0F, MapColor.DIRT, SoundType.GRAVEL, NoteBlockInstrument.BASEDRUM),
            new OreVariant("sand", BlockType.FALLING_BLOCK, 1.5F, 3.0F, MapColor.SAND, SoundType.SAND, NoteBlockInstrument.SNARE),
            new OreVariant("gravel", BlockType.FALLING_BLOCK, 1.6F, 3.0F, MapColor.STONE, SoundType.GRAVEL, NoteBlockInstrument.SNARE),
            new OreVariant("soul_sand", BlockType.REDSTONE_ORE_BLOCK, 1.5F, 3.0F, MapColor.COLOR_BROWN, SoundType.SOUL_SAND, NoteBlockInstrument.SNARE),
            new OreVariant("soul_soil", BlockType.BLOCK, 1.5F, 3.0F, MapColor.COLOR_BROWN, SoundType.SOUL_SOIL, NoteBlockInstrument.SNARE)
    );

    public static final List<ItemVariant> ITEMS_SIMPLE_VARIANTS = List.of(
            new ItemVariant("raw_%s"),
            new ItemVariant("%s_ingot"),
            new ItemVariant("%s_nugget"),
            new ItemVariant("%s_dust"),
            new ItemVariant("%s_scrap")
    );
}
