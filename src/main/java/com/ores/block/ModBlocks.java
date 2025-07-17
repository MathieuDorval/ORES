package com.ores.block;

import com.ores.ORES;
import com.ores.core.ListMaterials;
import com.ores.core.ListVariants;
import com.ores.core.Registry; // On utilise la classe Registry
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ORES.MODID);
    public static final Map<String, DeferredBlock<Block>> REGISTERED_BLOCKS = new HashMap<>();

    public static final List<String> STORAGE_BLOCK_NAMES = new ArrayList<>();
    public static final List<String> ORE_BLOCK_NAMES = new ArrayList<>();

    // --- GÉNÉRATION AUTOMATIQUE ---
    static {
        // Boucle pour les blocs de stockage, utilisant maintenant le record BlockRegistryEntry
        for (Registry.BlockRegistryEntry entry : Registry.STORAGE_BLOCK_ENTRIES) {

            DeferredBlock<Block> storageBlock = BLOCKS.registerBlock(
                    entry.name(),
                    entry.blockConstructor(),
                    entry.properties()
            );

            REGISTERED_BLOCKS.put(entry.name(), storageBlock);
            STORAGE_BLOCK_NAMES.add(entry.name());
        }

        // Boucle pour les minerais (inchangée pour le moment)
        for (ListMaterials material : ListMaterials.ALL_MATERIALS) {
            for (ListVariants.OreVariant variant : ListVariants.ORE_VARIANTS) {
                String oreName = variant.name() + "_" + material.name() + "_ore";
                DeferredBlock<Block> oreBlock = BLOCKS.registerBlock(oreName,
                        (props) -> new DropExperienceBlock(UniformInt.of(0, 0), props),
                        BlockBehaviour.Properties.of()
                                .mapColor(variant.mapColor())
                                .instrument(variant.instrument())
                                .sound(variant.soundType())
                                .strength(variant.destroyTime() * material.oreDestroyTimeFactor(), variant.explosionResistance() * material.oreExplosionResistanceFactor())
                                .requiresCorrectToolForDrops()
                );
                REGISTERED_BLOCKS.put(oreName, oreBlock);
                ORE_BLOCK_NAMES.add(oreName);
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
