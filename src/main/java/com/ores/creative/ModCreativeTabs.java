package com.ores.creative;

import com.ores.ORES;
import com.ores.block.ModBlocks;
import com.ores.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ORES.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEMS_TAB = CREATIVE_MODE_TABS.register("items_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.items_tab"))
            .icon(() -> {
                if (!ModItems.SIMPLE_ITEM_NAMES.isEmpty()) {
                    return new ItemStack(ModItems.getItem(ModItems.SIMPLE_ITEM_NAMES.getFirst()).get());
                }
                return new ItemStack(Items.IRON_INGOT); // Fallback icon
            })
            .displayItems((parameters, output) -> {
                ModItems.SIMPLE_ITEM_NAMES.forEach(name -> output.accept(ModItems.getItem(name).get()));
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register("blocks_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.blocks_tab"))
            .icon(() -> {
                if (!ModBlocks.STORAGE_BLOCK_NAMES.isEmpty()) {
                    return new ItemStack(ModItems.getItem(ModBlocks.STORAGE_BLOCK_NAMES.getFirst()).get());
                }
                return new ItemStack(Items.IRON_BLOCK); // Fallback icon
            })
            .displayItems((parameters, output) -> {
                ModBlocks.STORAGE_BLOCK_NAMES.forEach(name -> output.accept(ModItems.getItem(name).get()));
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ORES_TAB = CREATIVE_MODE_TABS.register("ores_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ores_tab"))
            .icon(() -> {
                if (!ModBlocks.ORE_BLOCK_NAMES.isEmpty()) {
                    return new ItemStack(ModItems.getItem(ModBlocks.ORE_BLOCK_NAMES.getFirst()).get());
                }
                return new ItemStack(Items.IRON_ORE); // Fallback icon
            })
            .displayItems((parameters, output) -> {
                ModBlocks.ORE_BLOCK_NAMES.forEach(name -> output.accept(ModItems.getItem(name).get()));
            }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
