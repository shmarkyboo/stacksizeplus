package com.stacksizeplus.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.component.type.ContainerComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow @Final private Item item;

    @Inject(method = "getMaxCount", at = @At("HEAD"), cancellable = true)
    private void stacksizeplus$getMaxCount(CallbackInfoReturnable<Integer> cir) {
        if (shouldStackTo64((ItemStack)(Object)this)) {
            cir.setReturnValue(64);
        }
    }

    private static boolean shouldStackTo64(ItemStack stack) {
        Item item = stack.getItem();

        // Empty shulker boxes only
        if (isAnyShulkerBox(item)) {
            ContainerComponent container = stack.get(DataComponentTypes.CONTAINER);
            return container == null || container.stream().findAny().isEmpty();
        }

        return item == Items.ARMOR_STAND

            || isBannerPattern(item)
            || isBanner(item)
            || isBed(item)
            || isBoat(item)
            || item == Items.CAKE
            || isChestBoat(item)
            || isMusicDisc(item)
            || item == Items.BUCKET
            || item == Items.EGG
            || item == Items.BROWN_EGG
            || item == Items.BLUE_EGG
            || item == Items.ENDER_PEARL
            || item == Items.ENCHANTED_BOOK
            || item == Items.HONEY_BOTTLE
            || isHangingSign(item)
            || item == Items.LINGERING_POTION
            || isMinecart(item)
            || item == Items.POTION
            || item == Items.SADDLE
            || isSign(item)
            || item == Items.SNOWBALL
            || item == Items.SPLASH_POTION
            || isStew(item)
            || item == Items.TOTEM_OF_UNDYING
            || item == Items.WRITABLE_BOOK
            || item == Items.GOAT_HORN
            || item == Items.WOLF_ARMOR
            || isHorseArmor(item)
            || isNautilusArmor(item);
    }

    private static boolean isBannerPattern(Item item) {
        return item == Items.FLOWER_BANNER_PATTERN
            || item == Items.CREEPER_BANNER_PATTERN
            || item == Items.SKULL_BANNER_PATTERN
            || item == Items.MOJANG_BANNER_PATTERN
            || item == Items.GLOBE_BANNER_PATTERN
            || item == Items.PIGLIN_BANNER_PATTERN
            || item == Items.FLOW_BANNER_PATTERN
            || item == Items.GUSTER_BANNER_PATTERN
            || item == Items.FIELD_MASONED_BANNER_PATTERN
            || item == Items.BORDURE_INDENTED_BANNER_PATTERN;
    }

    private static boolean isBanner(Item item) {
        return item == Items.WHITE_BANNER || item == Items.ORANGE_BANNER
            || item == Items.MAGENTA_BANNER || item == Items.LIGHT_BLUE_BANNER
            || item == Items.YELLOW_BANNER || item == Items.LIME_BANNER
            || item == Items.PINK_BANNER || item == Items.GRAY_BANNER
            || item == Items.LIGHT_GRAY_BANNER || item == Items.CYAN_BANNER
            || item == Items.PURPLE_BANNER || item == Items.BLUE_BANNER
            || item == Items.BROWN_BANNER || item == Items.GREEN_BANNER
            || item == Items.RED_BANNER || item == Items.BLACK_BANNER;
    }

    private static boolean isBed(Item item) {
        return item == Items.WHITE_BED || item == Items.ORANGE_BED
            || item == Items.MAGENTA_BED || item == Items.LIGHT_BLUE_BED
            || item == Items.YELLOW_BED || item == Items.LIME_BED
            || item == Items.PINK_BED || item == Items.GRAY_BED
            || item == Items.LIGHT_GRAY_BED || item == Items.CYAN_BED
            || item == Items.PURPLE_BED || item == Items.BLUE_BED
            || item == Items.BROWN_BED || item == Items.GREEN_BED
            || item == Items.RED_BED || item == Items.BLACK_BED;
    }

    private static boolean isBoat(Item item) {
        return item == Items.OAK_BOAT || item == Items.SPRUCE_BOAT
            || item == Items.BIRCH_BOAT || item == Items.JUNGLE_BOAT
            || item == Items.ACACIA_BOAT || item == Items.DARK_OAK_BOAT
            || item == Items.MANGROVE_BOAT || item == Items.CHERRY_BOAT
            || item == Items.BAMBOO_RAFT;
    }

    private static boolean isChestBoat(Item item) {
        return item == Items.OAK_CHEST_BOAT || item == Items.SPRUCE_CHEST_BOAT
            || item == Items.BIRCH_CHEST_BOAT || item == Items.JUNGLE_CHEST_BOAT
            || item == Items.ACACIA_CHEST_BOAT || item == Items.DARK_OAK_CHEST_BOAT
            || item == Items.MANGROVE_CHEST_BOAT || item == Items.CHERRY_CHEST_BOAT
            || item == Items.BAMBOO_CHEST_RAFT;
    }

    private static boolean isMusicDisc(Item item) {
        return item == Items.MUSIC_DISC_13 || item == Items.MUSIC_DISC_CAT
            || item == Items.MUSIC_DISC_BLOCKS || item == Items.MUSIC_DISC_CHIRP
            || item == Items.MUSIC_DISC_FAR || item == Items.MUSIC_DISC_MALL
            || item == Items.MUSIC_DISC_MELLOHI || item == Items.MUSIC_DISC_STAL
            || item == Items.MUSIC_DISC_STRAD || item == Items.MUSIC_DISC_WARD
            || item == Items.MUSIC_DISC_11 || item == Items.MUSIC_DISC_WAIT
            || item == Items.MUSIC_DISC_OTHERSIDE || item == Items.MUSIC_DISC_5
            || item == Items.MUSIC_DISC_PIGSTEP || item == Items.MUSIC_DISC_RELIC
            || item == Items.MUSIC_DISC_CREATOR || item == Items.MUSIC_DISC_CREATOR_MUSIC_BOX
            || item == Items.MUSIC_DISC_PRECIPICE;
    }

    private static boolean isAnyShulkerBox(Item item) {
        return item == Items.SHULKER_BOX || item == Items.WHITE_SHULKER_BOX
            || item == Items.ORANGE_SHULKER_BOX || item == Items.MAGENTA_SHULKER_BOX
            || item == Items.LIGHT_BLUE_SHULKER_BOX || item == Items.YELLOW_SHULKER_BOX
            || item == Items.LIME_SHULKER_BOX || item == Items.PINK_SHULKER_BOX
            || item == Items.GRAY_SHULKER_BOX || item == Items.LIGHT_GRAY_SHULKER_BOX
            || item == Items.CYAN_SHULKER_BOX || item == Items.PURPLE_SHULKER_BOX
            || item == Items.BLUE_SHULKER_BOX || item == Items.BROWN_SHULKER_BOX
            || item == Items.GREEN_SHULKER_BOX || item == Items.RED_SHULKER_BOX
            || item == Items.BLACK_SHULKER_BOX;
    }

    private static boolean isHangingSign(Item item) {
        return item == Items.OAK_HANGING_SIGN || item == Items.SPRUCE_HANGING_SIGN
            || item == Items.BIRCH_HANGING_SIGN || item == Items.JUNGLE_HANGING_SIGN
            || item == Items.ACACIA_HANGING_SIGN || item == Items.DARK_OAK_HANGING_SIGN
            || item == Items.MANGROVE_HANGING_SIGN || item == Items.CHERRY_HANGING_SIGN
            || item == Items.BAMBOO_HANGING_SIGN || item == Items.CRIMSON_HANGING_SIGN
            || item == Items.WARPED_HANGING_SIGN;
    }

    private static boolean isMinecart(Item item) {
        return item == Items.MINECART || item == Items.CHEST_MINECART
            || item == Items.FURNACE_MINECART || item == Items.TNT_MINECART
            || item == Items.HOPPER_MINECART;
    }

    private static boolean isSign(Item item) {
        return item == Items.OAK_SIGN || item == Items.SPRUCE_SIGN
            || item == Items.BIRCH_SIGN || item == Items.JUNGLE_SIGN
            || item == Items.ACACIA_SIGN || item == Items.DARK_OAK_SIGN
            || item == Items.MANGROVE_SIGN || item == Items.CHERRY_SIGN
            || item == Items.BAMBOO_SIGN || item == Items.CRIMSON_SIGN
            || item == Items.WARPED_SIGN;
    }

    private static boolean isStew(Item item) {
        return item == Items.MUSHROOM_STEW || item == Items.BEETROOT_SOUP
            || item == Items.RABBIT_STEW || item == Items.SUSPICIOUS_STEW;
    }

    private static boolean isHorseArmor(Item item) {
        return item == Items.LEATHER_HORSE_ARMOR
            || item == Items.IRON_HORSE_ARMOR
            || item == Items.GOLDEN_HORSE_ARMOR
            || item == Items.DIAMOND_HORSE_ARMOR
            || item == Items.NETHERITE_HORSE_ARMOR;
    }

    private static boolean isNautilusArmor(Item item) {
        return item == Items.COPPER_NAUTILUS_ARMOR
            || item == Items.IRON_NAUTILUS_ARMOR
            || item == Items.GOLDEN_NAUTILUS_ARMOR
            || item == Items.DIAMOND_NAUTILUS_ARMOR
            || item == Items.NETHERITE_NAUTILUS_ARMOR;
    }

    }