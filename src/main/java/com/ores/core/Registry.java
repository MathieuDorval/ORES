package com.ores.core;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Registry {

    // LISTES D'ENREGISTREMENT
    public record BlockRegistryEntry(String ID, Function<BlockBehaviour.Properties, ? extends Block> blockConstructor, BlockBehaviour.Properties properties) {}
    public record ItemRegistryEntry(String ID, Item.Properties properties) {}
    public static final List<BlockRegistryEntry> BLOCKS_STORAGE_ENTRIES = new ArrayList<>();
    public static final List<BlockRegistryEntry> BLOCKS_ORE_ENTRIES = new ArrayList<>();
    public static final List<ItemRegistryEntry> ITEMS_SIMPLE_ENTRIES = new ArrayList<>();

    private static void applyOptionalFloatProperty(BlockBehaviour.Properties properties, Object variantValue, Object materialValue, Consumer<Float> propertyApplier) {
        if (variantValue instanceof Boolean && (Boolean) variantValue && materialValue instanceof Float matValue) {
            propertyApplier.accept(matValue);
        }
        else if (variantValue instanceof Float varValue) {
            float valueToApply = (materialValue instanceof Float matValue) ? Math.max(varValue, matValue) : varValue;
            propertyApplier.accept(valueToApply);
        }
    }

    private static void applyOptionalLightProperty(BlockBehaviour.Properties properties, Object variantValue, Object materialValue) {
        if (variantValue instanceof Boolean && (Boolean) variantValue && materialValue instanceof Integer matValue) {
            properties.lightLevel(s -> matValue);
        }
        else if (variantValue instanceof Integer varValue) {
            int valueToApply = (materialValue instanceof Integer matValue) ? Math.max(varValue, matValue) : varValue;
            properties.lightLevel(s -> valueToApply);
        }
    }

    static {
        for (ListMaterials.Material material : ListMaterials.ALL_MATERIALS) {
            // --- BLOCS DE STOCKAGE ---
            for (ListVariants.BlockVariant variant : ListVariants.BLOCKS_STORAGE_VARIANTS) {
                String blockName = String.format(variant.ID(), material.name());
                BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                        .mapColor(material.color())
                        .instrument(NoteBlockInstrument.BASEDRUM)
                        .sound(material.sound())
                        .strength(variant.destroyTime() * material.blockDestroyTimeFactor(), variant.explosionResistance() * material.blockExplosionResistanceFactor());

                if (variant.requiresCorrectToolForDrops()) {
                    properties.requiresCorrectToolForDrops();
                }

                applyOptionalFloatProperty(properties, variant.friction(), material.friction(), properties::friction);
                applyOptionalFloatProperty(properties, variant.jumpFactor(), material.jumpFactor(), properties::jumpFactor);
                applyOptionalFloatProperty(properties, variant.speedFactor(), material.speedFactor(), properties::speedFactor);
                applyOptionalLightProperty(properties, variant.lightLevel(), material.lightLevel());

                Function<BlockBehaviour.Properties, ? extends Block> constructor = switch (variant.blockType()) {
                    case FALLING_BLOCK -> (props) -> new CustomBlocks.CustomFallingBlock(props, variant.dropsOnFalling());
                    case DROP_EXPERIENCE_BLOCK -> (props) -> new DropExperienceBlock(UniformInt.of(2, 5), props);
                    default -> Block::new;
                };
                BLOCKS_STORAGE_ENTRIES.add(new BlockRegistryEntry(blockName, constructor, properties));
            }

            // --- MINERAIS ---
            for (ListVariants.OreVariant variant : ListVariants.BLOCKS_ORE_VARIANTS) {
                String oreName = variant.ID() + "_" + material.name() + "_ore";
                BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                        .mapColor(variant.mapColor())
                        .instrument(variant.instrument())
                        .sound(variant.soundType())
                        .strength(variant.destroyTime() * material.oreDestroyTimeFactor(), variant.explosionResistance() * material.oreExplosionResistanceFactor());

                if (variant.requiresCorrectToolForDrops()) {
                    properties.requiresCorrectToolForDrops();
                }

                applyOptionalLightProperty(properties, variant.lightLevel(), material.lightLevel());

                Function<BlockBehaviour.Properties, ? extends Block> constructor;
                if (material.isRedstoneLike()) {
                    properties.lightLevel(state -> state.getValue(CustomBlocks.CustomRedstoneOreBlock.LIT) ? 9 : 0);
                    constructor = switch(variant.blockType()) {
                        case FALLING_BLOCK -> (props) -> new CustomBlocks.CustomFallingRedstoneOreBlock(props, UniformInt.of(material.oreMinXP(), material.oreMaxXP()), material.color().col);
                        default -> (props) -> new CustomBlocks.CustomRedstoneOreBlock(props, UniformInt.of(material.oreMinXP(), material.oreMaxXP()), material.color().col);
                    };
                } else {
                    constructor = switch(variant.blockType()) {
                        case FALLING_BLOCK -> (props) -> new CustomBlocks.CustomFallingOreBlock(props, UniformInt.of(material.oreMinXP(), material.oreMaxXP()));
                        default -> (props) -> new DropExperienceBlock(UniformInt.of(material.oreMinXP(), material.oreMaxXP()), props);
                    };
                }
                BLOCKS_ORE_ENTRIES.add(new BlockRegistryEntry(oreName, constructor, properties));
            }

            // --- ITEMS ---
            if (material.selfExist()) {
                Item.Properties properties = new Item.Properties();
                if (material.rarity() != Rarity.COMMON) properties.rarity(material.rarity());
                if (material.stacksTo() instanceof Integer stackSize) properties.stacksTo(stackSize);
                if (material.fireResistant()) properties.fireResistant();
                ITEMS_SIMPLE_ENTRIES.add(new ItemRegistryEntry(material.name(), properties));
            }
            for (ListVariants.ItemVariant variant : ListVariants.ITEMS_SIMPLE_VARIANTS) {
                String itemName = String.format(variant.ID(), material.name());

                Item.Properties properties = new Item.Properties();

                // Logique pour la rareté : on prend la plus élevée des deux
                if (material.rarity().ordinal() > variant.rarity().ordinal()) {
                    properties.rarity(material.rarity());
                } else if (variant.rarity() != Rarity.COMMON) {
                    properties.rarity(variant.rarity());
                }

                // Logique pour la résistance au feu : si l'un des deux est vrai, on l'applique
                if (material.fireResistant() || variant.fireResistant()) {
                    properties.fireResistant();
                }

                // Logique pour la taille des stacks : on prend le plus petit des deux
                Object matStacksTo = material.stacksTo();
                Object varStacksTo = variant.stacksTo();
                if (matStacksTo instanceof Integer matStack && varStacksTo instanceof Integer varStack) {
                    properties.stacksTo(Math.min(matStack, varStack));
                } else if (matStacksTo instanceof Integer matStack) {
                    properties.stacksTo(matStack);
                } else if (varStacksTo instanceof Integer varStack) {
                    properties.stacksTo(varStack);
                }

                ITEMS_SIMPLE_ENTRIES.add(new ItemRegistryEntry(itemName, properties));
            }
        }
    }
}
