package com.ores.core;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Registry {

    private static class CustomFallingBlock extends FallingBlock {
        private final boolean dropsOnBreak;
        public static final MapCodec<CustomFallingBlock> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        propertiesCodec(),
                        Codec.BOOL.fieldOf("drops_on_break").forGetter(b -> b.dropsOnBreak)
                ).apply(instance, CustomFallingBlock::new)
        );

        public CustomFallingBlock(BlockBehaviour.Properties properties, boolean dropsOnBreak) {
            super(properties);
            this.dropsOnBreak = dropsOnBreak;
        }

        @Override
        protected @NotNull MapCodec<? extends FallingBlock> codec() { return CODEC; }

        @Override
        protected void falling(FallingBlockEntity entity) { entity.dropItem = this.dropsOnBreak; }

        @Override
        public int getDustColor(BlockState state, @NotNull BlockGetter getter, @NotNull BlockPos pos) { return state.getMapColor(getter, pos).col; }
    }

    public record BlockRegistryEntry(
            String ID,
            Function<BlockBehaviour.Properties, ? extends Block> blockConstructor,
            BlockBehaviour.Properties properties
    ) {}

    public record ItemRegistryEntry(
            String ID,
            Item.Properties properties
    ) {}

    public static final List<BlockRegistryEntry> BLOCKS_STORAGE_ENTRIES = new ArrayList<>();
    public static final List<BlockRegistryEntry> BLOCKS_ORE_ENTRIES = new ArrayList<>();
    public static final List<ItemRegistryEntry> ITEMS_SIMPLE_ENTRIES = new ArrayList<>();

    static {
        for (ListMaterials material : ListMaterials.ALL_MATERIALS) {
            for (ListVariants.BlockVariant variant : ListVariants.BLOCKS_STORAGE_VARIANTS) {
                String blockName = String.format(variant.nameFormat(), material.name());

                BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                        .mapColor(material.color())
                        .instrument(NoteBlockInstrument.BASEDRUM)
                        .sound(material.sound())
                        .strength(variant.destroyTime() * material.blockDestroyTimeFactor(), variant.explosionResistance() * material.blockExplosionResistanceFactor());

                // Le switch utilise maintenant l'énumération, ce qui est plus sûr.
                Function<BlockBehaviour.Properties, ? extends Block> constructor = switch (variant.blockType()) {
                    case FALLING_BLOCK -> (props) -> new CustomFallingBlock(props, variant.dropsOnBreak());
                    case DROP_EXPERIENCE_BLOCK -> (props) -> new DropExperienceBlock(UniformInt.of(2, 5), props);
                    default -> Block::new; // BLOCK est le cas par défaut
                };

                BLOCKS_STORAGE_ENTRIES.add(new BlockRegistryEntry(blockName, constructor, properties));
            }

            for (ListVariants.OreVariant variant : ListVariants.BLOCKS_ORE_VARIANTS) {
                String oreName = variant.name() + "_" + material.name() + "_ore";
                BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                        .mapColor(variant.mapColor())
                        .instrument(variant.instrument())
                        .sound(variant.soundType())
                        .strength(variant.destroyTime() * material.oreDestroyTimeFactor(), variant.explosionResistance() * material.oreExplosionResistanceFactor())
                        .requiresCorrectToolForDrops();
                Function<BlockBehaviour.Properties, ? extends Block> constructor = (props) -> new DropExperienceBlock(UniformInt.of(0, 0), props);
                BLOCKS_ORE_ENTRIES.add(new BlockRegistryEntry(oreName, constructor, properties));
            }

            if (material.selfExist()) {
                ITEMS_SIMPLE_ENTRIES.add(new ItemRegistryEntry(material.name(), new Item.Properties()));
            }
            for (ListVariants.ItemVariant variant : ListVariants.ITEMS_SIMPLE_VARIANTS) {
                String itemName = String.format(variant.nameFormat(), material.name());
                ITEMS_SIMPLE_ENTRIES.add(new ItemRegistryEntry(itemName, new Item.Properties()));
            }
        }
    }
}
