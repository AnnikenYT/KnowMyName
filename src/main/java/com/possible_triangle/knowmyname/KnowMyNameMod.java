package com.possible_triangle.knowmyname;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Nameable;

import java.util.Optional;
import java.util.function.Function;

public class KnowMyNameMod implements ModInitializer {
    @Override
    public void onInitialize() {
    }

    public static Optional<Function<RegistryWrapper.WrapperLookup, NbtCompound>> updateNBT(BlockEntity tile) {
        if (tile instanceof Nameable nameable && nameable.hasCustomName()) {
            return Optional.of(lookup -> {
                var nbt = tile.createNbt(lookup);
                nbt.remove("Items");
                return nbt;
            });
        }
        return Optional.empty();
    }

}
