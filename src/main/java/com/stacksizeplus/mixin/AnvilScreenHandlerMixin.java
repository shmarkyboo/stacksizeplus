package com.stacksizeplus.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ScreenHandler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {

    @Shadow
    private int repairItemUsage;

    @Shadow
    private boolean keepSecondSlot;

    @Inject(method = "updateResult", at = @At("TAIL"))
    private void stacksizeplus$fixStackedEnchantedBooks(CallbackInfo ci) {
        ScreenHandler handler = (ScreenHandler) (Object) this;
        ItemStack right = handler.getSlot(1).getStack();

        if (right.isEmpty()) {
            return;
        }

        if (!right.isOf(Items.ENCHANTED_BOOK)) {
            return;
        }

        if (right.getCount() <= 1) {
            return;
        }

        this.repairItemUsage = 1;
        this.keepSecondSlot = true;
    }
}