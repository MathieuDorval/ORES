package com.ores.core;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import java.util.List;
import java.util.function.Function;

public class ListVariants {

    /**
     * Définit les propriétés d'une variante de bloc.
     * Ajout de blockConstructor pour définir le type de bloc dynamiquement.
     */
    public record BlockVariant(
            String nameFormat,
            Function<BlockBehaviour.Properties, ? extends Block> blockConstructor,
            float destroyTime,
            float explosionResistance,
            Object friction
    ) {}

    /**
     * Définit les propriétés d'une variante de minerai.
     */
    public record OreVariant(
            String name,
            float destroyTime,
            float explosionResistance,
            MapColor mapColor,
            SoundType soundType,
            NoteBlockInstrument instrument
    ) {}

    /**
     * Définit les propriétés d'une variante d'item simple.
     */
    public record ItemVariant(
            String nameFormat
    ) {}

    /**
     * Liste des variantes pour les blocs de stockage (blocs pleins, blocs de matériaux bruts).
     * Le constructeur est maintenant défini ici. Pour le moment, c'est Block::new pour tous.
     */
    public static final List<BlockVariant> STORAGE_VARIANTS = List.of(
            new BlockVariant("%s_block", Block::new, 5.0F, 6.0F, 0.6F),
            new BlockVariant("raw_%s_block", Block::new, 5.0F, 6.0F, false),
            new BlockVariant("dust_%s_block", Block::new, 5.0F, 6.0F, false)
    );

    /**
     * Liste des variantes pour les minerais, définissant la roche dans laquelle ils apparaissent.
     */
    public static final List<OreVariant> ORE_VARIANTS = List.of(
            new OreVariant("stone", 3.0F, 3.0F, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("granite", 3.0F, 3.0F, MapColor.DIRT, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("diorite", 3.0F, 3.0F, MapColor.QUARTZ, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("andesite", 3.0F, 3.0F, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("deepslate", 4.5F, 3.0F, MapColor.DEEPSLATE, SoundType.DEEPSLATE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("tuff", 3.0F, 3.0F, MapColor.TERRACOTTA_GRAY, SoundType.TUFF, NoteBlockInstrument.BASEDRUM),
            new OreVariant("cobblestone", 3.0F, 3.0F, MapColor.STONE, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("basalt", 2.0F, 3.0F, MapColor.COLOR_BLACK, SoundType.BASALT, NoteBlockInstrument.BASEDRUM),
            new OreVariant("polished_basalt", 2.0F, 3.0F, MapColor.COLOR_BLACK, SoundType.BASALT, NoteBlockInstrument.BASEDRUM),
            new OreVariant("calcite", 2.0F, 3.0F, MapColor.TERRACOTTA_WHITE, SoundType.CALCITE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("netherrack", 1.4F, 3.0F, MapColor.NETHER, SoundType.NETHERRACK, NoteBlockInstrument.BASEDRUM),
            new OreVariant("obsidian", 50.0F, 1200.0F, MapColor.COLOR_BLACK, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("end_stone", 3.0F, 5.0F, MapColor.SAND, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("dirt", 1.5F, 3.0F, MapColor.DIRT, SoundType.GRAVEL, NoteBlockInstrument.BASEDRUM),
            new OreVariant("sand", 1.5F, 3.0F, MapColor.SAND, SoundType.SAND, NoteBlockInstrument.SNARE),
            new OreVariant("gravel", 1.6F, 3.0F, MapColor.STONE, SoundType.GRAVEL, NoteBlockInstrument.SNARE),
            new OreVariant("soul_sand", 1.5F, 3.0F, MapColor.COLOR_BROWN, SoundType.SOUL_SAND, NoteBlockInstrument.SNARE),
            new OreVariant("soul_soil", 1.5F, 3.0F, MapColor.COLOR_BROWN, SoundType.SOUL_SOIL, NoteBlockInstrument.SNARE)
    );

    /**
     * Liste des variantes pour les items simples (lingots, pépites, etc.).
     */
    public static final List<ItemVariant> SIMPLE_ITEM_VARIANTS = List.of(
            new ItemVariant("raw_%s"),
            new ItemVariant("%s_ingot"),
            new ItemVariant("%s_nugget"),
            new ItemVariant("%s_dust"),
            new ItemVariant("%s_scrap")
    );
}
