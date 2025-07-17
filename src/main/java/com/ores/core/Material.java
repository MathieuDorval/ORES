package com.ores.core;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;


public record Material(
        String name,
        MapColor color,
        SoundType sound,
        boolean selfExist,
        float blockDestroyTimeFactor,
        float blockExplosionResistanceFactor,
        float oreDestroyTimeFactor,
        float oreExplosionResistanceFactor
) {
}
