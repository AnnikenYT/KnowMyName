package com.possible_triangle.knowmyname;

import java.util.Optional;
import java.util.function.Function;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Nameable;
import net.minecraft.world.level.block.entity.BlockEntity;

public class KnowMyNameMod implements ModInitializer {
    @Override
    public void onInitialize() {
    }

    public static Optional<Function<HolderLookup.Provider, CompoundTag>> updateNBT(BlockEntity tile) {
        if (tile instanceof Nameable nameable && nameable.hasCustomName()) {
            return Optional.of(lookup -> {
                var nbt = tile.getUpdateTag(lookup);
                nbt.remove("Items");
                return nbt;
            });
        }
        return Optional.empty();
    }

}
