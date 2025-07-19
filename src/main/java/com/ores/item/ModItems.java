package com.ores.item;

import com.ores.ORES;
import com.ores.block.ModBlocks;
import com.ores.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ORES.MODID);
    public static final Map<String, Supplier<? extends Item>> REGISTERED_ITEMS = new HashMap<>();

    public static void registerItems() {
        // --- 1. Enregistrement des Items Simples ---
        for (Registry.ItemRegistryEntry entry : Registry.ITEMS_SIMPLE_ENTRIES) {
            DeferredItem<Item> simpleItem = ITEMS.registerSimpleItem(entry.ID(), entry.properties());
            REGISTERED_ITEMS.put(entry.ID(), simpleItem);
        }

        // --- 2. Enregistrement des Items de Blocs (BlockItems) ---
        for (Registry.BlockRegistryEntry entry : Registry.BLOCKS_STORAGE_ENTRIES) {
            DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(entry.ID(), ModBlocks.getBlock(entry.ID()), entry.itemProperties());
            REGISTERED_ITEMS.put(entry.ID(), blockItem);
        }
        for (Registry.BlockRegistryEntry entry : Registry.BLOCKS_ORE_ENTRIES) {
            DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(entry.ID(), ModBlocks.getBlock(entry.ID()), entry.itemProperties());
            REGISTERED_ITEMS.put(entry.ID(), blockItem);
        }
    }

    public static Supplier<? extends Item> getItem(String name) {
        return REGISTERED_ITEMS.get(name);
    }
}