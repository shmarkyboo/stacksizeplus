package com.stacksizeplus.mixin;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilMenu.class)
public abstract class AnvilScreenHandlerMixin {

    @Shadow
    private int repairItemCountCost;

    @Inject(method = "createResult", at = @At("TAIL"))
    private void stacksizeplus$fixStackedEnchantedBooks(CallbackInfo ci) {
        AbstractContainerMenu handler = (AbstractContainerMenu)(Object)this;

        ItemStack addition = handler.getSlot(1).getItem();

        if (addition.isEmpty()) {
            return;
        }

        if (addition.getCount() <= 1) {
            return;
        }

        if (!addition.has(DataComponents.STORED_ENCHANTMENTS)) {
            return;
        }

        this.repairItemCountCost = 1;
    }
}