package com.ores.item;

import com.ores.ORES;
import com.ores.block.ModBlocks;
import com.ores.core.Registry; // Importer Registry
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ORES.MODID);

    public static final Map<String, Supplier<? extends Item>> REGISTERED_ITEMS = new HashMap<>();

    static {
        // --- Génération des Items Simples ---
        for (Registry.ItemRegistryEntry entry : Registry.ITEMS_SIMPLE_ENTRIES) {
            DeferredItem<Item> simpleItem = ITEMS.registerSimpleItem(entry.ID(), entry.properties());
            REGISTERED_ITEMS.put(entry.ID(), simpleItem);
        }

        // --- Génération des BlockItems ---
        ModBlocks.REGISTERED_BLOCKS.forEach((name, block) -> {
            DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(name, block);
            REGISTERED_ITEMS.put(name, blockItem);
        });
    }

    public static Supplier<? extends Item> getItem(String name) {
        return REGISTERED_ITEMS.get(name);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
