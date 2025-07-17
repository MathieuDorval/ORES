package com.ores.item;

import com.ores.ORES;
import com.ores.block.ModBlocks;
import com.ores.core.ListMaterials;
import com.ores.core.ListVariants;
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
        // --- Génération des Items ---
        // Utilisation de la nouvelle liste unifiée
        for (ListMaterials material : ListMaterials.ALL_MATERIALS) {
            // Cas spécial pour la gemme/l'item de base (ex: diamant, émeraude)
            if (material.selfExist()) {
                String selfName = material.name();
                DeferredItem<Item> selfItem = ITEMS.registerSimpleItem(selfName, new Item.Properties());
                REGISTERED_ITEMS.put(selfName, selfItem);
                SIMPLE_ITEM_NAMES.add(selfName);
            }

            // Boucle pour les items simples (lingot, pépite, etc.)
            for (ListVariants.ItemVariant variant : ListVariants.SIMPLE_ITEM_VARIANTS) {
                String itemName = String.format(variant.nameFormat(), material.name());
                DeferredItem<Item> simpleItem = ITEMS.registerSimpleItem(itemName, new Item.Properties());
                REGISTERED_ITEMS.put(itemName, simpleItem);
                SIMPLE_ITEM_NAMES.add(itemName);
            }
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
