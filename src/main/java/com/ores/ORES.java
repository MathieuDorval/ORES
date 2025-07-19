package com.ores;

import com.ores.block.ModBlocks;
import com.ores.core.Registry;
import com.ores.creative.ModCreativeTabs;
import com.ores.item.ModItems;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(ORES.MODID)
public class ORES {
    public static final String MODID = "ores";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ORES(IEventBus modEventBus) {
        modEventBus.addListener(this::commonSetup);

        Registry.initialize();

        ModBlocks.registerBlocks();
        ModItems.registerItems();

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }
}