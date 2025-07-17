package com.ores;

import com.ores.block.ModBlocks;
import com.ores.creative.ModCreativeTabs;
import com.ores.item.ModItems;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(ORES.MODID)
public class ORES {
    public static final String MODID = "ores";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ORES(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Loading mod " + MODID);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
    }
}
