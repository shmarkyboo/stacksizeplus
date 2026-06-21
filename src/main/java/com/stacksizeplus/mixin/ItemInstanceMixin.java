package com.stacksizeplus.mixin;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Method;

@Mixin(ItemInstance.class)
public interface ItemInstanceMixin {

    @Inject(method = "getMaxStackSize", at = @At("HEAD"), cancellable = true)
    private void stacksizeplus$getMaxStackSize(CallbackInfoReturnable<Integer> cir) {
        if (!((Object)this instanceof ItemStack stack)) {
            return;
        }

        if (shouldStackTo64(stack)) {
            cir.setReturnValue(64);
        }
    }

    private static boolean shouldStackTo64(ItemStack stack) {
        Item item = stack.getItem();
        String id = getItemPath(item);

        // Empty shulker boxes only
        if (isAnyShulkerBox(id)) {
            return isShulkerBoxEmpty(stack);
        }

        return id.equals("armor_stand")

            || isBannerPattern(id)
            || isBanner(id)
            || isBed(id)
            || isBoat(id)
            || id.equals("cake")
            || isChestBoat(id)
            || isMusicDisc(id)
            || id.equals("bucket")
            || id.equals("egg")
            || id.equals("brown_egg")
            || id.equals("blue_egg")
            || id.equals("ender_pearl")
            || id.equals("enchanted_book")
            || id.equals("honey_bottle")
            || isHangingSign(id)
            || id.equals("lingering_potion")
            || isMinecart(id)
            || id.equals("potion")
            || id.equals("saddle")
            || isSign(id)
            || id.equals("snowball")
            || id.equals("splash_potion")
            || isStew(id)
            || id.equals("totem_of_undying")
            || id.equals("writable_book")
            || id.equals("goat_horn")
            || id.equals("wolf_armor")
            || isHorseArmor(id)
            || isNautilusArmor(id);
    }

    private static String getItemPath(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }

    private static boolean isShulkerBoxEmpty(ItemStack stack) {
        Object container = stack.get(DataComponents.CONTAINER);

        if (container == null) {
            return true;
        }

        try {
            Method isEmptyMethod = container.getClass().getMethod("isEmpty");
            Object result = isEmptyMethod.invoke(container);

            if (result instanceof Boolean empty) {
                return empty;
            }
        } catch (ReflectiveOperationException ignored) {
        }

        return false;
    }

    private static boolean isBannerPattern(String id) {
        return id.endsWith("_banner_pattern");
    }

    private static boolean isBanner(String id) {
        return id.endsWith("_banner");
    }

    private static boolean isBed(String id) {
        return id.endsWith("_bed");
    }

    private static boolean isBoat(String id) {
        return id.endsWith("_boat") || id.equals("bamboo_raft");
    }

    private static boolean isChestBoat(String id) {
        return id.endsWith("_chest_boat") || id.equals("bamboo_chest_raft");
    }

    private static boolean isMusicDisc(String id) {
        return id.startsWith("music_disc_");
    }

    private static boolean isAnyShulkerBox(String id) {
        return id.equals("shulker_box") || id.endsWith("_shulker_box");
    }

    private static boolean isHangingSign(String id) {
        return id.endsWith("_hanging_sign");
    }

    private static boolean isMinecart(String id) {
        return id.equals("minecart")
            || id.equals("chest_minecart")
            || id.equals("furnace_minecart")
            || id.equals("tnt_minecart")
            || id.equals("hopper_minecart");
    }

    private static boolean isSign(String id) {
        return id.endsWith("_sign") && !id.endsWith("_hanging_sign");
    }

    private static boolean isStew(String id) {
        return id.equals("mushroom_stew")
            || id.equals("beetroot_soup")
            || id.equals("rabbit_stew")
            || id.equals("suspicious_stew");
    }

    private static boolean isHorseArmor(String id) {
        return id.endsWith("_horse_armor");
    }

    private static boolean isNautilusArmor(String id) {
        return id.endsWith("_nautilus_armor");
    }
}