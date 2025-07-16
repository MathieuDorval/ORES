package com.ores.item;

import com.ores.ORES;
import com.ores.block.ModBlocks;
import com.ores.core.ListMaterials;
import com.ores.core.Material;
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
        // --- Génération des Items de base ---
        for (Material material : ListMaterials.ALL_MATERIALS) {
            // Enregistre une gemme SEULEMENT si le matériau est une gemme
            if (material.selfExist()) {
                String gemName = material.name();
                DeferredItem<Item> gemItem = ITEMS.registerSimpleItem(gemName, new Item.Properties());
                REGISTERED_ITEMS.put(gemName, gemItem);
            }

            // Enregistre un matériau brut (ex: raw_iron)
            String rawName = "raw_" + material.name();
            DeferredItem<Item> rawItem = ITEMS.registerSimpleItem(rawName, new Item.Properties());
            REGISTERED_ITEMS.put(rawName, rawItem);

            // Enregistre un lingot pour TOUS les matériaux
            String ingotName = material.name() + "_ingot";
            DeferredItem<Item> ingotItem = ITEMS.registerSimpleItem(ingotName, new Item.Properties());
            REGISTERED_ITEMS.put(ingotName, ingotItem);

            // Enregistre une pépite pour chaque matériau
            String nuggetName = material.name() + "_nugget";
            DeferredItem<Item> nuggetItem = ITEMS.registerSimpleItem(nuggetName, new Item.Properties());
            REGISTERED_ITEMS.put(nuggetName, nuggetItem);

            // Enregistre une poussière pour chaque matériau
            String dustName = material.name() + "_dust";
            DeferredItem<Item> dustItem = ITEMS.registerSimpleItem(dustName, new Item.Properties());
            REGISTERED_ITEMS.put(dustName, dustItem);

            // Enregistre un débris pour chaque matériau
            String scrapName = material.name() + "_scrap";
            DeferredItem<Item> scrapItem = ITEMS.registerSimpleItem(scrapName, new Item.Properties());
            REGISTERED_ITEMS.put(scrapName, scrapItem);
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
