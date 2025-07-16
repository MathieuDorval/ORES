package com.ores.block;

import com.ores.ORES;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SandBlock; // <-- Importez SandBlock
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ORES.MODID);

    // --- VANILLA BLOCK ---
    public static final DeferredBlock<Block> MATERIAL_BLOCK = BLOCKS.registerSimpleBlock("material_block", BlockBehaviour.Properties.of()
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.METAL)
            .strength(3.0F, 3.0F));
    public static final DeferredBlock<Block> RAW_MATERIAL_BLOCK = BLOCKS.registerSimpleBlock("raw_material_block", BlockBehaviour.Properties.of()
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.METAL)
            .strength(3.0F, 3.0F));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}