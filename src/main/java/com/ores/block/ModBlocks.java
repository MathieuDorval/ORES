package com.ores.block;

import com.ores.ORES;
import com.ores.core.Registry;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ORES.MODID);
    public static final Map<String, DeferredBlock<Block>> REGISTERED_BLOCKS = new HashMap<>();

    public static void registerBlocks() {
        for (Registry.BlockRegistryEntry entry : Registry.BLOCKS_STORAGE_ENTRIES) {
            DeferredBlock<Block> block = BLOCKS.registerBlock(entry.ID(), entry.blockConstructor(), entry.properties());
            REGISTERED_BLOCKS.put(entry.ID(), block);
        }
        for (Registry.BlockRegistryEntry entry : Registry.BLOCKS_ORE_ENTRIES) {
            DeferredBlock<Block> block = BLOCKS.registerBlock(entry.ID(), entry.blockConstructor(), entry.properties());
            REGISTERED_BLOCKS.put(entry.ID(), block);
        }
    }

    public static Supplier<? extends Block> getBlock(String name) {
        return REGISTERED_BLOCKS.get(name);
    }
}
