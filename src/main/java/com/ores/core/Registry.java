package com.ores.core;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Centralise la création des listes d'objets à enregistrer pour le mod.
 * Cette classe génère dynamiquement toutes les combinaisons de matériaux et de variantes.
 */
public class Registry {

    /**
     * Un "record" pour stocker les 3 arguments de manière sûre, en garantissant leur type.
     * C'est la solution la plus propre pour éviter les avertissements de cast.
     * @param name Le nom d'enregistrement (ex: "raw_iron_block").
     * @param blockConstructor Le constructeur du bloc (ex: Block::new).
     * @param properties L'objet de propriétés pour le bloc.
     */
    public record BlockRegistryEntry(
            String name,
            Function<BlockBehaviour.Properties, ? extends Block> blockConstructor,
            BlockBehaviour.Properties properties
    ) {}

    // La liste contient maintenant des objets BlockRegistryEntry, ce qui est "type-safe".
    public static final List<BlockRegistryEntry> STORAGE_BLOCK_ENTRIES = new ArrayList<>();
    public static final List<BlockRegistryEntry> ORE_BLOCK_ENTRIES = new ArrayList<>(); // Prête pour une utilisation future

    /**
     * Le bloc statique est exécuté au chargement de la classe pour remplir les listes.
     */
    static {
        // Boucle sur chaque matériau (fer, or, etc.)
        for (ListMaterials material : ListMaterials.ALL_MATERIALS) {

            // Génération des Blocs de Stockage
            for (ListVariants.BlockVariant variant : ListVariants.STORAGE_VARIANTS) {
                String blockName = String.format(variant.nameFormat(), material.name());

                // Construction dynamique des propriétés du bloc
                BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                        .mapColor(material.color())
                        .instrument(NoteBlockInstrument.BASEDRUM)
                        .sound(material.sound())
                        .strength(variant.destroyTime() * material.blockDestroyTimeFactor(), variant.explosionResistance() * material.blockExplosionResistanceFactor());

                // Ajout à la liste en utilisant le constructeur de la variante.
                STORAGE_BLOCK_ENTRIES.add(new BlockRegistryEntry(blockName, variant.blockConstructor(), properties));
            }
        }
    }
}
