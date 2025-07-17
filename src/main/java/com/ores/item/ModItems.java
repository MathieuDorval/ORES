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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ORES.MODID);

    public static final Map<String, Supplier<? extends Item>> REGISTERED_ITEMS = new HashMap<>();
    public static final List<String> SIMPLE_ITEM_NAMES = new ArrayList<>();

    static {
        // --- Génération des Items de base ---
        for (Material material : ListMaterials.ALL_MATERIALS) {
            // Enregistre une gemme SEULEMENT si le matériau SelfExist
            if (material.selfExist()) {
                String selfName = material.name();
                DeferredItem<Item> selfItem = ITEMS.registerSimpleItem(selfName, new Item.Properties());
                REGISTERED_ITEMS.put(selfName, selfItem);
                SIMPLE_ITEM_NAMES.add(selfName);
            }

            // Enregistre un matériau brut (ex: raw_iron)
            String rawName = "raw_" + material.name();
            DeferredItem<Item> rawItem = ITEMS.registerSimpleItem(rawName, new Item.Properties());
            REGISTERED_ITEMS.put(rawName, rawItem);
            SIMPLE_ITEM_NAMES.add(rawName);

            // Enregistre un lingot pour TOUS les matériaux
            String ingotName = material.name() + "_ingot";
            DeferredItem<Item> ingotItem = ITEMS.registerSimpleItem(ingotName, new Item.Properties());
            REGISTERED_ITEMS.put(ingotName, ingotItem);
            SIMPLE_ITEM_NAMES.add(ingotName);

            // Enregistre une pépite pour chaque matériau
            String nuggetName = material.name() + "_nugget";
            DeferredItem<Item> nuggetItem = ITEMS.registerSimpleItem(nuggetName, new Item.Properties());
            REGISTERED_ITEMS.put(nuggetName, nuggetItem);
            SIMPLE_ITEM_NAMES.add(nuggetName);

            // Enregistre une poussière pour chaque matériau
            String dustName = material.name() + "_dust";
            DeferredItem<Item> dustItem = ITEMS.registerSimpleItem(dustName, new Item.Properties());
            REGISTERED_ITEMS.put(dustName, dustItem);
            SIMPLE_ITEM_NAMES.add(dustName);

            // Enregistre un débris pour chaque matériau
            String scrapName = material.name() + "_scrap";
            DeferredItem<Item> scrapItem = ITEMS.registerSimpleItem(scrapName, new Item.Properties());
            REGISTERED_ITEMS.put(scrapName, scrapItem);
            SIMPLE_ITEM_NAMES.add(scrapName);
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
