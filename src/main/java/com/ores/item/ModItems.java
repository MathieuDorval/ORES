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

    // Une Map pour stocker tous nos objets enregistrés (items et blockitems).
    public static final Map<String, Supplier<? extends Item>> REGISTERED_ITEMS = new HashMap<>();

    static {
        // --- Génération des Items de base ---
        for (Material material : ListMaterials.ALL_MATERIALS) {
            // Enregistre un matériau brut pour chaque matériau (ex: raw_iron)
            String rawName = "raw_" + material.name();
            DeferredItem<Item> rawItem = ITEMS.registerSimpleItem(rawName, new Item.Properties());
            REGISTERED_ITEMS.put(rawName, rawItem);

            // Enregistre une gemme OU un lingot en fonction du flag 'isGem'
            if (material.isGem()) {
                String gemName = material.name() + "_gem";
                DeferredItem<Item> gemItem = ITEMS.registerSimpleItem(gemName, new Item.Properties());
                REGISTERED_ITEMS.put(gemName, gemItem);
            } else {
                String ingotName = material.name() + "_ingot";
                DeferredItem<Item> ingotItem = ITEMS.registerSimpleItem(ingotName, new Item.Properties());
                REGISTERED_ITEMS.put(ingotName, ingotItem);
            }
        }

        // --- Génération des BlockItems ---
        // On boucle sur tous les blocs qu'on a enregistrés dans ModBlocks
        ModBlocks.REGISTERED_BLOCKS.forEach((name, block) -> {
            DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(name, block);
            REGISTERED_ITEMS.put(name, blockItem);
        });
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
