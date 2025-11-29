package com.smushytaco.expanded_bow_enchanting.mixin;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.smushytaco.expanded_bow_enchanting.ExpandedBowEnchanting;
import net.minecraft.core.Holder;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import java.util.Set;
@Mixin(Enchantment.class)
public abstract class BowModification {
    @ModifyReturnValue(method = "canEnchant", at = @At("RETURN"))
    public boolean hookIsAcceptableItem(boolean original, ItemStack stack) {
        Enchantment enchantment = (Enchantment) (Object) this;
        if (ExpandedBowEnchanting.INSTANCE.isSameEnchantment(enchantment, Enchantments.LOOTING)) {
            if (!ExpandedBowEnchanting.INSTANCE.getConfig().getCanUseLootingOnBow() || !(stack.getItem() instanceof BowItem)) return original;
            return true;
        } else if (ExpandedBowEnchanting.INSTANCE.isSameEnchantment(enchantment, Enchantments.PIERCING)) {
            if (!ExpandedBowEnchanting.INSTANCE.getConfig().getCanUsePiercingOnBow() || !(stack.getItem() instanceof BowItem)) return original;
            return true;
        } else if (ExpandedBowEnchanting.INSTANCE.isSameEnchantment(enchantment, Enchantments.MULTISHOT)) {
            if (!ExpandedBowEnchanting.INSTANCE.getConfig().getCanUseMultishotOnBow() || !(stack.getItem() instanceof BowItem)) return original;
            return true;
        }
        return original;
    }
    @ModifyReturnValue(method = "areCompatible", at = @At("RETURN"))
    private static boolean hookCanBeCombined(boolean original, Holder<Enchantment> first, Holder<Enchantment> second) { return original || ExpandedBowEnchanting.INSTANCE.getConfig().getInfinityAndMendingCanBeMixed() && ExpandedBowEnchanting.INSTANCE.canCombineEnchantments(first, second, Set.of(Enchantments.INFINITY, Enchantments.MENDING)); }
}