package com.possible_triangle.knowmyname.mixin;

import com.possible_triangle.knowmyname.KnowMyNameMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntity.class)
public class BlockEntityMixin {

    @Inject(at = @At("HEAD"), method = "getUpdatePacket", cancellable = true)
    public void toUpdatePacket(CallbackInfoReturnable<Packet<ClientGamePacketListener>> callback) {
        var self = (BlockEntity) (Object) (this);
        KnowMyNameMod.updateNBT(self)
                .map(nbt -> ClientboundBlockEntityDataPacket.create(self, (be, dm) -> nbt.apply(dm)))
                .ifPresent(callback::setReturnValue);
    }

    @Inject(at = @At("HEAD"), method = "saveWithoutMetadata", cancellable = true)
    public void toInitialChunkDataNbt(HolderLookup.Provider lookup, CallbackInfoReturnable<CompoundTag> callback) {
        var self = (BlockEntity) (Object) (this);
        KnowMyNameMod.updateNBT(self).map(it -> it.apply(lookup)).ifPresent(callback::setReturnValue);
    }

}
