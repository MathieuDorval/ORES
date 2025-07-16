package com.ores.item;

import com.ores.ORES;
import com.ores.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ORES.MODID);

    // --- VANILLA BLOCK ITEMS ---
    // Chaque bloc qui doit exister dans l'inventaire a besoin d'un BlockItem.
    public static final DeferredItem<BlockItem> MATERIAL_BLOCK = ITEMS.registerSimpleBlockItem("material_block", ModBlocks.MATERIAL_BLOCK);
    public static final DeferredItem<BlockItem> RAW_MATERIAL_BLOCK = ITEMS.registerSimpleBlockItem("raw_material_block", ModBlocks.RAW_MATERIAL_BLOCK);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}