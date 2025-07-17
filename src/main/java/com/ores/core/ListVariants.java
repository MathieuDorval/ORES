package com.ores.core;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import java.util.List;

public class ListVariants {

    public enum BlockType {
        BLOCK,
        FALLING_BLOCK,
        DROP_EXPERIENCE_BLOCK
    }

    public record BlockVariant(
            String nameFormat,
            BlockType blockType,
            float destroyTime,
            float explosionResistance,
            Object friction,
            boolean dropsOnBreak
    ) {}

    public record OreVariant(
            String name,
            float destroyTime,
            float explosionResistance,
            MapColor mapColor,
            SoundType soundType,
            NoteBlockInstrument instrument,
            boolean isFalling
    ) {}

    public record ItemVariant(
            String nameFormat
    ) {}

    public static final List<BlockVariant> BLOCKS_STORAGE_VARIANTS = List.of(
            new BlockVariant("%s_block", BlockType.BLOCK, 5.0F, 6.0F, 0.6F, true),
            new BlockVariant("raw_%s_block", BlockType.BLOCK, 5.0F, 6.0F, false, true),
            new BlockVariant("dust_%s_block", BlockType.FALLING_BLOCK, 1.5F, 1.5F, false, true)
    );

    public static final List<OreVariant> BLOCKS_ORE_VARIANTS = List.of(
            new OreVariant("stone", 3.0F, 3.0F, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("granite", 3.0F, 3.0F, MapColor.DIRT, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("diorite", 3.0F, 3.0F, MapColor.QUARTZ, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("andesite", 3.0F, 3.0F, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("deepslate", 4.5F, 3.0F, MapColor.DEEPSLATE, SoundType.DEEPSLATE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("tuff", 3.0F, 3.0F, MapColor.TERRACOTTA_GRAY, SoundType.TUFF, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("cobblestone", 3.0F, 3.0F, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("basalt", 2.0F, 3.0F, MapColor.COLOR_BLACK, SoundType.BASALT, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("polished_basalt", 2.0F, 3.0F, MapColor.COLOR_BLACK, SoundType.BASALT, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("calcite", 2.0F, 3.0F, MapColor.TERRACOTTA_WHITE, SoundType.CALCITE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("netherrack", 1.4F, 3.0F, MapColor.NETHER, SoundType.NETHERRACK, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("obsidian", 50.0F, 1200.0F, MapColor.COLOR_BLACK, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("end_stone", 3.0F, 5.0F, MapColor.SAND, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("dirt", 1.5F, 3.0F, MapColor.DIRT, SoundType.GRAVEL, NoteBlockInstrument.BASEDRUM, false),
            new OreVariant("sand", 1.5F, 3.0F, MapColor.SAND, SoundType.SAND, NoteBlockInstrument.SNARE, true),
            new OreVariant("gravel", 1.6F, 3.0F, MapColor.STONE, SoundType.GRAVEL, NoteBlockInstrument.SNARE, true),
            new OreVariant("soul_sand", 1.5F, 3.0F, MapColor.COLOR_BROWN, SoundType.SOUL_SAND, NoteBlockInstrument.SNARE, false),
            new OreVariant("soul_soil", 1.5F, 3.0F, MapColor.COLOR_BROWN, SoundType.SOUL_SOIL, NoteBlockInstrument.SNARE, false)
    );

    public static final List<ItemVariant> ITEMS_SIMPLE_VARIANTS = List.of(
            new ItemVariant("raw_%s"),
            new ItemVariant("%s_ingot"),
            new ItemVariant("%s_nugget"),
            new ItemVariant("%s_dust"),
            new ItemVariant("%s_scrap")
    );
}
