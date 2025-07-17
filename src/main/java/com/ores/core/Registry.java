package com.ores.core;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Registry {

    // LISTES D'ENREGISTREMENT
    public record BlockRegistryEntry(String ID, Function<BlockBehaviour.Properties, ? extends Block> blockConstructor, BlockBehaviour.Properties properties) {}
    public record ItemRegistryEntry(String ID, Item.Properties properties) {}
    public static final List<BlockRegistryEntry> BLOCKS_STORAGE_ENTRIES = new ArrayList<>();
    public static final List<BlockRegistryEntry> BLOCKS_ORE_ENTRIES = new ArrayList<>();
    public static final List<ItemRegistryEntry> ITEMS_SIMPLE_ENTRIES = new ArrayList<>();

    static {
        for (ListMaterials.Material material : ListMaterials.ALL_MATERIALS) {
            for (ListVariants.BlockVariant variant : ListVariants.BLOCKS_STORAGE_VARIANTS) {
                String blockName = String.format(variant.nameFormat(), material.name());
                BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                        .mapColor(material.color())
                        .instrument(NoteBlockInstrument.BASEDRUM)
                        .sound(material.sound())
                        .strength(variant.destroyTime() * material.blockDestroyTimeFactor(), variant.explosionResistance() * material.blockExplosionResistanceFactor());
                Function<BlockBehaviour.Properties, ? extends Block> constructor = switch (variant.blockType()) {
                    case FALLING_BLOCK -> (props) -> new CustomBlocks.CustomFallingBlock(props, variant.dropsOnBreak());
                    case DROP_EXPERIENCE_BLOCK -> (props) -> new DropExperienceBlock(UniformInt.of(2, 5), props);
                    default -> Block::new;
                };
                BLOCKS_STORAGE_ENTRIES.add(new BlockRegistryEntry(blockName, constructor, properties));
            }
            for (ListVariants.OreVariant variant : ListVariants.BLOCKS_ORE_VARIANTS) {
                String oreName = variant.name() + "_" + material.name() + "_ore";
                BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                        .mapColor(variant.mapColor())
                        .instrument(variant.instrument())
                        .sound(variant.soundType())
                        .strength(variant.destroyTime() * material.oreDestroyTimeFactor(), variant.explosionResistance() * material.oreExplosionResistanceFactor())
                        .requiresCorrectToolForDrops();

                Function<BlockBehaviour.Properties, ? extends Block> constructor;
                if (material.isRedstoneLike()) {
                    properties.lightLevel(state -> state.getValue(CustomBlocks.CustomRedstoneOreBlock.LIT) ? 9 : 0);
                    constructor = switch(variant.blockType()) {
                        case FALLING_BLOCK -> (props) -> new CustomBlocks.CustomFallingRedstoneOreBlock(props, UniformInt.of(material.minOreXP(), material.maxOreXP()), material.color().col);
                        default -> (props) -> new CustomBlocks.CustomRedstoneOreBlock(props, UniformInt.of(material.minOreXP(), material.maxOreXP()), material.color().col);
                    };
                } else {
                    constructor = switch(variant.blockType()) {
                        case FALLING_BLOCK -> (props) -> new CustomBlocks.CustomFallingOreBlock(props, UniformInt.of(material.minOreXP(), material.maxOreXP()));
                        default -> (props) -> new DropExperienceBlock(UniformInt.of(material.minOreXP(), material.maxOreXP()), props);
                    };
                }
                BLOCKS_ORE_ENTRIES.add(new BlockRegistryEntry(oreName, constructor, properties));
            }
            if (material.selfExist()) {
                ITEMS_SIMPLE_ENTRIES.add(new ItemRegistryEntry(material.name(), new Item.Properties()));
            }
            for (ListVariants.ItemVariant variant : ListVariants.ITEMS_SIMPLE_VARIANTS) {
                String itemName = String.format(variant.nameFormat(), material.name());
                ITEMS_SIMPLE_ENTRIES.add(new ItemRegistryEntry(itemName, new Item.Properties()));
            }
        }
    }
}
