package com.ores.creative;

import com.ores.ORES;
import com.ores.core.Registry;
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
                if (!Registry.ITEMS_SIMPLE_ENTRIES.isEmpty()) {
                    return new ItemStack(ModItems.getItem(Registry.ITEMS_SIMPLE_ENTRIES.getFirst().ID()).get());
                }
                return new ItemStack(Items.IRON_INGOT);
            })
            .displayItems((parameters, output) -> {
                for (Registry.ItemRegistryEntry entry : Registry.ITEMS_SIMPLE_ENTRIES) {
                    output.accept(ModItems.getItem(entry.ID()).get());
                }
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register("blocks_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.blocks_tab"))
            .icon(() -> {
                if (!Registry.BLOCKS_STORAGE_ENTRIES.isEmpty()) {
                    return new ItemStack(ModItems.getItem(Registry.BLOCKS_STORAGE_ENTRIES.getFirst().ID()).get());
                }
                return new ItemStack(Items.IRON_BLOCK);
            })
            .displayItems((parameters, output) -> {
                for (Registry.BlockRegistryEntry entry : Registry.BLOCKS_STORAGE_ENTRIES) {
                    output.accept(ModItems.getItem(entry.ID()).get());
                }            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ORES_TAB = CREATIVE_MODE_TABS.register("ores_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ores_tab"))
            .icon(() -> {
                if (!Registry.BLOCKS_ORE_ENTRIES.isEmpty()) {
                    return new ItemStack(ModItems.getItem(Registry.BLOCKS_ORE_ENTRIES.getFirst().ID()).get());
                }
                return new ItemStack(Items.IRON_ORE);
            })
            .displayItems((parameters, output) -> {
                for (Registry.BlockRegistryEntry entry : Registry.BLOCKS_ORE_ENTRIES) {
                    output.accept(ModItems.getItem(entry.ID()).get());
                }            }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
