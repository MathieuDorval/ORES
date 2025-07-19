package com.ores.datagen;

import com.ores.ORES;
import com.ores.block.ModBlocks;
import com.ores.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ConditionalItemModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.conditional.HasComponent;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, ORES.MODID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {
        for (var entry : ModBlocks.BLOCKS.getEntries()) {
            blockModels.createTrivialCube(entry.get());
        }
        for (var entry : ModItems.ITEMS.getEntries()) {
            // On vérifie si l'objet n'est PAS un BlockItem.
            // Si c'est le cas, cela veut dire que c'est un objet "simple" (lingot, poudre, etc.).
            if (!(entry.get() instanceof BlockItem)) {
                // Pour ces objets, on génère un modèle plat standard.
                itemModels.generateFlatItem(entry.get(), ModelTemplates.FLAT_ITEM);
            }
        }
    }
}