package com.ores.block;

import com.ores.ORES;
import com.ores.core.ListMaterials;
import com.ores.core.Material;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ORES.MODID);
    public static final Map<String, DeferredBlock<Block>> REGISTERED_BLOCKS = new HashMap<>();

    // --- DÉFINITION DES VARIANTES ---

    // Record pour les blocs de stockage (bloc plein et bloc brut)
    private record BlockVariant(String nameFormat, float strength, float resistance) {}

    // Record pour les variantes de minerais (pierre, granite, etc.)
    private record OreVariant(String name, float baseStrength, float baseResistance, MapColor mapColor, SoundType soundType, NoteBlockInstrument instrument) {}

    // Liste des variantes de blocs de stockage avec leurs propriétés spécifiques
    private static final List<BlockVariant> STORAGE_VARIANTS = List.of(
            new BlockVariant("%s_block", 5.0F, 6.0F),      // Pour le bloc de stockage principal
            new BlockVariant("raw_%s_block", 5.0F, 6.0F)  // Pour le bloc de matériau brut
    );

    // Liste des variantes de minerais avec leurs propriétés spécifiques
    private static final List<OreVariant> ORE_VARIANTS = List.of(
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
            new OreVariant("netherrack", 0.4F, 0.4F, MapColor.NETHER, SoundType.NETHERRACK, NoteBlockInstrument.BASEDRUM),
            new OreVariant("obsidian", 50.0F, 1200.0F, MapColor.COLOR_BLACK, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("end_stone", 3.0F, 9.0F, MapColor.SAND, SoundType.STONE, NoteBlockInstrument.BASEDRUM),
            new OreVariant("sand", 0.5F, 0.5F, MapColor.SAND, SoundType.SAND, NoteBlockInstrument.SNARE),
            new OreVariant("gravel", 0.6F, 0.6F, MapColor.STONE, SoundType.GRAVEL, NoteBlockInstrument.SNARE),
            new OreVariant("dirt", 0.5F, 0.5F, MapColor.DIRT, SoundType.GRAVEL, NoteBlockInstrument.BASEDRUM),
            new OreVariant("soul_sand", 0.5F, 0.5F, MapColor.COLOR_BROWN, SoundType.SOUL_SAND, NoteBlockInstrument.SNARE),
            new OreVariant("soul_soil", 0.5F, 0.5F, MapColor.COLOR_BROWN, SoundType.SOUL_SOIL, NoteBlockInstrument.SNARE)
    );

    // --- GÉNÉRATION AUTOMATIQUE ---
    static {
        for (Material material : ListMaterials.ALL_MATERIALS) {
            // Boucle pour les blocs de stockage et bruts
            for (BlockVariant variant : STORAGE_VARIANTS) {
                String blockName = String.format(variant.nameFormat(), material.name());
                DeferredBlock<Block> storageBlock = BLOCKS.registerSimpleBlock(blockName,
                        BlockBehaviour.Properties.of()
                                .mapColor(material.color())
                                .instrument(NoteBlockInstrument.BASEDRUM)
                                .sound(material.sound())
                                .strength(variant.strength(), variant.resistance())
                );
                REGISTERED_BLOCKS.put(blockName, storageBlock);
            }

            // Boucle pour les minerais
            for (OreVariant variant : ORE_VARIANTS) {
                String oreName = variant.name() + "_" + material.name() + "_ore";
                DeferredBlock<Block> oreBlock = BLOCKS.registerBlock(oreName,
                        (properties) -> new DropExperienceBlock(UniformInt.of(0, 0), properties),
                        BlockBehaviour.Properties.of()
                                .mapColor(variant.mapColor())
                                .instrument(variant.instrument())
                                .sound(variant.soundType())
                                .strength(variant.baseStrength(), variant.baseResistance())
                                .requiresCorrectToolForDrops()
                );
                REGISTERED_BLOCKS.put(oreName, oreBlock);
            }
        }
    }

    public static Supplier<? extends Block> getBlock(String name) {
        return REGISTERED_BLOCKS.get(name);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
