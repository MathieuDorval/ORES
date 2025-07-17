package com.ores.core;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
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

    private static class CustomFallingOreBlock extends FallingBlock {
        private final UniformInt xpRange;
        public static final MapCodec<CustomFallingOreBlock> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        propertiesCodec(),
                        UniformInt.CODEC.fieldOf("xp_range").forGetter(b -> b.xpRange)
                ).apply(instance, CustomFallingOreBlock::new)
        );

        public CustomFallingOreBlock(Properties properties, UniformInt xpRange) {
            super(properties);
            this.xpRange = xpRange;
        }

        @Override
        protected @NotNull MapCodec<? extends FallingBlock> codec() { return CODEC; }

        /**
         * Cette méthode est appelée lorsque l'entité du bloc qui tombe est créée.
         * En mettant dropItem à false, le bloc disparaîtra s'il ne peut pas se poser.
         */
        @Override
        protected void falling(FallingBlockEntity entity) {
            entity.dropItem = false;
        }

        @Override
        public void spawnAfterBreak(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull ItemStack pTool, boolean pDropExperience) {
            super.spawnAfterBreak(pState, pLevel, pPos, pTool, pDropExperience);
            if (pDropExperience) { this.dropExperience(pLevel, pPos, pTool); }
        }

        public void dropExperience(ServerLevel pLevel, BlockPos pPos, ItemStack pTool) {
            if (EnchantmentHelper.getItemEnchantmentLevel((Holder<Enchantment>) Enchantments.SILK_TOUCH, pTool) == 0) {
                int i = this.xpRange.sample(pLevel.random);
                if (i > 0) { this.popExperience(pLevel, pPos, i); }
            }
        }

        @Override
        public int getDustColor(BlockState state, @NotNull BlockGetter getter, @NotNull BlockPos pos) { return state.getMapColor(getter, pos).col; }
    }

    public record BlockRegistryEntry(String ID, Function<BlockBehaviour.Properties, ? extends Block> blockConstructor, BlockBehaviour.Properties properties) {}
    public record ItemRegistryEntry(String ID, Item.Properties properties) {}

    public static final List<BlockRegistryEntry> BLOCKS_STORAGE_ENTRIES = new ArrayList<>();
    public static final List<BlockRegistryEntry> BLOCKS_ORE_ENTRIES = new ArrayList<>();
    public static final List<ItemRegistryEntry> ITEMS_SIMPLE_ENTRIES = new ArrayList<>();

    static {
        for (ListMaterials.Material material : ListMaterials.ALL_MATERIALS) {
            for (ListVariants.BlockVariant variant : ListVariants.BLOCKS_STORAGE_VARIANTS) {
                String blockName = String.format(variant.nameFormat(), material.name());
                BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                        .mapColor(material.color())
                        .instrument(NoteBlockInstrument.BASEDRUM)
                        .sound(material.sound())
                        .strength(variant.destroyTime() * material.blockDestroyTimeFactor(), variant.explosionResistance() * material.blockExplosionResistanceFactor());
                Function<BlockBehaviour.Properties, ? extends Block> constructor = switch (variant.blockType()) {
                    case FALLING_BLOCK -> (props) -> new CustomFallingBlock(props, variant.dropsOnBreak());
                    case DROP_EXPERIENCE_BLOCK -> (props) -> new DropExperienceBlock(UniformInt.of(2, 5), props);
                    default -> Block::new;
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
                Function<BlockBehaviour.Properties, ? extends Block> constructor;
                if (variant.isFalling()) {
                    constructor = (props) -> new CustomFallingOreBlock(props, UniformInt.of(material.minOreXP(), material.maxOreXP()));
                } else {
                    constructor = (props) -> new DropExperienceBlock(UniformInt.of(material.minOreXP(), material.maxOreXP()), props);
                }
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
