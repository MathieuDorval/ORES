package com.ores.core;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

/**
 * Le record Material a été mis à jour pour inclure un booléen 'isGem'.
 * Cela nous permettra de générer dynamiquement un lingot ou une gemme.
 */
public record Material(
        String name,
        MapColor color,
        SoundType sound,
        boolean isGem
) {
}
