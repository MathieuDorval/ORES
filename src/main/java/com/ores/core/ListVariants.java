package com.ores.core;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

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
            Object lightLevel,
            boolean fireResistant,
            Rarity rarity,
            Object stacksTo,
            PushReaction pushReaction,
            Boolean isRedstoneConductor,
            boolean ignitedByLava,
            Integer redstonePower
    ) {}

    public record OreVariant(
            String ID,
            String BLOCK_ID,
            BlockType blockType,
            float destroyTime,
            float explosionResistance,
            boolean requiresCorrectToolForDrops,
            MapColor mapColor,
            SoundType soundType,
            NoteBlockInstrument instrument,
            Object lightLevel,
            boolean fireResistant,
            PushReaction pushReaction,
            Boolean isRedstoneConductor,
            boolean ignitedByLava
    ) {}

    public record ItemVariant(
            String ID,
            Rarity rarity,
            Object stacksTo,
            boolean fireResistant,
            int nutrition,
            float saturationModifier,
            boolean canAlwaysEat
    ) {}

    public static final List<BlockVariant> BLOCKS_STORAGE_VARIANTS = List.of(
            new BlockVariant("%s_block", BlockType.BLOCK, 5.0F, 6.0F, true, true, true, true, true, false, false, Rarity.COMMON, true, null, null, false, null),
            new BlockVariant("raw_%s_block", BlockType.BLOCK, 5.0F, 6.0F, true, true, true, true, true, false, false, Rarity.COMMON, false, null, null, false, null),
            new BlockVariant("dust_%s_block", BlockType.FALLING_BLOCK, 1.5F, 1.5F, false, false, true, true, true, false, false, Rarity.COMMON, true, null, null, false, null)
    );


    public static final List<OreVariant> BLOCKS_ORE_VARIANTS = List.of(
            new OreVariant("%s_ore", "minecraft:stone", BlockType.BLOCK, 3.0F, 3.0F, true, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false, false, null, null, false),
            new OreVariant("granite_%s_ore", "minecraft:granite", BlockType.BLOCK, 3.0F, 3.0F, true, MapColor.DIRT, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false, false, null, null, false),
            new OreVariant("diorite_%s_ore", "minecraft:diorite", BlockType.BLOCK, 3.0F, 3.0F, true, MapColor.QUARTZ, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false, false, null, null, false),
            new OreVariant("andesite_%s_ore", "minecraft:andesite", BlockType.BLOCK, 3.0F, 3.0F, true, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false, false, null, null, false),
            new OreVariant("deepslate_%s_ore", "minecraft:deepslate", BlockType.BLOCK, 4.5F, 3.0F, true, MapColor.DEEPSLATE, SoundType.DEEPSLATE, NoteBlockInstrument.BASEDRUM, false, false, null, null, false),
            new OreVariant("tuff_%s_ore", "minecraft:tuff", BlockType.BLOCK, 3.0F, 3.0F, true, MapColor.TERRACOTTA_GRAY, SoundType.TUFF, NoteBlockInstrument.BASEDRUM, false, false, null, null, false),
            new OreVariant("cobblestone_%s_ore", "minecraft:cobblestone", BlockType.BLOCK, 3.0F, 3.0F, true, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false, false, null, null, false),
            new OreVariant("basalt_%s_ore", "minecraft:basalt", BlockType.BLOCK, 2.0F, 3.0F, true, MapColor.COLOR_BLACK, SoundType.BASALT, NoteBlockInstrument.BASEDRUM, false, false, null, null, false),
            new OreVariant("polished_basalt_%s_ore", "minecraft:polished_basalt", BlockType.BLOCK, 2.0F, 3.0F, true, MapColor.COLOR_BLACK, SoundType.BASALT, NoteBlockInstrument.BASEDRUM, false, false, null, null, false),
            new OreVariant("calcite_%s_ore", "minecraft:calcite", BlockType.BLOCK, 2.0F, 3.0F, true, MapColor.TERRACOTTA_WHITE, SoundType.CALCITE, NoteBlockInstrument.BASEDRUM, false, false, null, null, false),
            new OreVariant("netherrack_%s_ore", "minecraft:netherrack", BlockType.BLOCK, 1.4F, 3.0F, true, MapColor.NETHER, SoundType.NETHERRACK, NoteBlockInstrument.BASEDRUM, false, true, null, null, true),
            new OreVariant("obsidian_%s_ore", "minecraft:obsidian", BlockType.BLOCK, 50.0F, 1200.0F, true, MapColor.COLOR_BLACK, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false, false, PushReaction.BLOCK, false, false),
            new OreVariant("end_stone_%s_ore", "minecraft:end_stone", BlockType.BLOCK, 3.0F, 5.0F, true, MapColor.SAND, SoundType.STONE, NoteBlockInstrument.BASEDRUM, false, false, null, null, false),
            new OreVariant("dirt_%s_ore", "minecraft:dirt", BlockType.BLOCK, 1.5F, 3.0F, false, MapColor.DIRT, SoundType.GRAVEL, NoteBlockInstrument.BASEDRUM, false, false, null, null, false),
            new OreVariant("sand_%s_ore", "minecraft:sand", BlockType.FALLING_BLOCK, 1.5F, 3.0F, false, MapColor.SAND, SoundType.SAND, NoteBlockInstrument.SNARE, false, false, null, null, false),
            new OreVariant("gravel_%s_ore", "minecraft:gravel", BlockType.FALLING_BLOCK, 1.6F, 3.0F, false, MapColor.STONE, SoundType.GRAVEL, NoteBlockInstrument.SNARE, false, false, null, null, false),
            new OreVariant("soul_sand_%s_ore", "minecraft:soul_sand", BlockType.BLOCK, 1.5F, 3.0F, false, MapColor.COLOR_BROWN, SoundType.SOUL_SAND, NoteBlockInstrument.SNARE, false, false, null, null, false),
            new OreVariant("soul_soil_%s_ore", "minecraft:soul_soil", BlockType.BLOCK, 1.5F, 3.0F, false, MapColor.COLOR_BROWN, SoundType.SOUL_SOIL, NoteBlockInstrument.SNARE, false, false, null, null, false)
    );

    public static final List<ItemVariant> ITEMS_SIMPLE_VARIANTS = List.of(
            new ItemVariant("raw_%s", Rarity.COMMON, false, false, 0, 0, false),
            new ItemVariant("%s_ingot", Rarity.COMMON, true, false, 2, 0.3F, false),
            new ItemVariant("%s_nugget", Rarity.COMMON, true, false, 1, 0.1F, true),
            new ItemVariant("%s_dust", Rarity.COMMON, true, false, 0, 0, false),
            new ItemVariant("%s_scrap", Rarity.UNCOMMON, 16, true, 0, 0, false)
    );
}
