package com.ores.creative;

import com.ores.ORES;
import com.ores.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ORES.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ORES_TAB = CREATIVE_MODE_TABS.register("ores_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ores_tab"))
            .icon(() -> ModItems.MATERIAL_BLOCK.get().getDefaultInstance()) // L'icône sera votre nouveau bloc
            .displayItems((parameters, output) -> {

                output.accept(ModItems.MATERIAL_BLOCK.get());
                output.accept(ModItems.RAW_MATERIAL_BLOCK.get());
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}