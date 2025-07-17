package com.ores.block;

import com.ores.ORES;
import com.ores.core.Registry; // On utilise la classe Registry
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ORES.MODID);
    public static final Map<String, DeferredBlock<Block>> REGISTERED_BLOCKS = new HashMap<>();

    // --- GÉNÉRATION AUTOMATIQUE ---
    static {
        // Boucle pour les blocs de stockage, utilisant le record BlockRegistryEntry
        for (Registry.BlockRegistryEntry entry : Registry.BLOCKS_STORAGE_ENTRIES) {
            DeferredBlock<Block> storageBlock = BLOCKS.registerBlock(
                    entry.ID(),
                    entry.blockConstructor(),
                    entry.properties()
            );
            REGISTERED_BLOCKS.put(entry.ID(), storageBlock);
        }

        // Boucle pour les minerais, utilisant maintenant aussi la classe Registry
        for (Registry.BlockRegistryEntry entry : Registry.BLOCKS_ORE_ENTRIES) {
            DeferredBlock<Block> oreBlock = BLOCKS.registerBlock(
                    entry.ID(),
                    entry.blockConstructor(),
                    entry.properties()
            );
            REGISTERED_BLOCKS.put(entry.ID(), oreBlock);
        }
    }

    public static Supplier<? extends Block> getBlock(String name) {
        return REGISTERED_BLOCKS.get(name);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
