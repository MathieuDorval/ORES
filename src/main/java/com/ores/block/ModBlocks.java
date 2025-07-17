package com.ores.block;

import com.ores.ORES;
import com.ores.core.ListMaterials;
import com.ores.core.Material;
import com.ores.core.variants.ListVariants;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ORES.MODID);
    public static final Map<String, DeferredBlock<Block>> REGISTERED_BLOCKS = new HashMap<>();

    // Listes pour stocker les noms des blocs pour le tri dans les onglets créatifs
    public static final List<String> STORAGE_BLOCK_NAMES = new ArrayList<>();
    public static final List<String> ORE_BLOCK_NAMES = new ArrayList<>();


    // --- GÉNÉRATION AUTOMATIQUE ---
    static {
        for (Material material : ListMaterials.ALL_MATERIALS) {
            // Boucle pour les blocs de stockage
            for (ListVariants.BlockVariant variant : ListVariants.STORAGE_VARIANTS) {
                String blockName = String.format(variant.nameFormat(), material.name());
                DeferredBlock<Block> storageBlock = BLOCKS.registerSimpleBlock(blockName,
                        BlockBehaviour.Properties.of()
                                .mapColor(material.color())
                                .instrument(NoteBlockInstrument.BASEDRUM)
                                .sound(material.sound())
                                .strength(variant.destroyTime() * material.blockDestroyTimeFactor(), variant.explosionResistance() * material.blockExplosionResistanceFactor())
                );
                REGISTERED_BLOCKS.put(blockName, storageBlock);
                STORAGE_BLOCK_NAMES.add(blockName); // Ajout du nom à la liste des blocs de stockage
            }

            // Boucle pour les minerais
            for (ListVariants.OreVariant variant : ListVariants.ORE_VARIANTS) {
                String oreName = variant.name() + "_" + material.name() + "_ore";
                DeferredBlock<Block> oreBlock = BLOCKS.registerBlock(oreName,
                        (properties) -> new DropExperienceBlock(UniformInt.of(0, 0), properties),
                        BlockBehaviour.Properties.of()
                                .mapColor(variant.mapColor())
                                .instrument(variant.instrument())
                                .sound(variant.soundType())
                                .strength(variant.destroyTime() * material.oreDestroyTimeFactor(), variant.explosionResistance() * material.oreExplosionResistanceFactor())
                                .requiresCorrectToolForDrops()
                );
                REGISTERED_BLOCKS.put(oreName, oreBlock);
                ORE_BLOCK_NAMES.add(oreName); // Ajout du nom à la liste des minerais
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
