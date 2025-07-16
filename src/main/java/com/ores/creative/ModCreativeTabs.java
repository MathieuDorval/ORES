package com.ores.creative;

import com.ores.ORES;
import com.ores.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ORES.MODID);

    // Onglet pour les objets (lingots, gemmes, matériaux bruts, etc.)
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEMS_TAB = CREATIVE_MODE_TABS.register("items_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.items_tab"))
            .icon(() -> new ItemStack(Items.IRON_INGOT)) // Icône vanilla
            .displayItems((parameters, output) -> {
                // Ajoute tous les items qui ne sont pas des blocs
                ModItems.REGISTERED_ITEMS.forEach((name, item) -> {
                    if (!name.contains("_block") && !name.contains("_ore")) {
                        output.accept(item.get());
                    }
                });
            }).build());

    // Onglet pour les blocs de stockage
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register("blocks_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.blocks_tab"))
            .icon(() -> new ItemStack(Blocks.IRON_BLOCK)) // Icône vanilla
            .displayItems((parameters, output) -> {
                // Ajoute tous les items dont le nom contient "_block"
                ModItems.REGISTERED_ITEMS.forEach((name, item) -> {
                    if (name.contains("_block")) {
                        output.accept(item.get());
                    }
                });
            }).build());

    // Onglet pour les minerais
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ORES_TAB = CREATIVE_MODE_TABS.register("ores_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ores_tab"))
            .icon(() -> new ItemStack(Blocks.IRON_ORE)) // Icône vanilla
            .displayItems((parameters, output) -> {
                // Ajoute tous les items dont le nom contient "_ore"
                ModItems.REGISTERED_ITEMS.forEach((name, item) -> {
                    if (name.contains("_ore")) {
                        output.accept(item.get());
                    }
                });
            }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
