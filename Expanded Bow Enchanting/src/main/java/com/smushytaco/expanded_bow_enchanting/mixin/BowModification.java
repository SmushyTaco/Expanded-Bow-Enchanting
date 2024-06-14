package com.smushytaco.expanded_bow_enchanting.mixin;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.smushytaco.expanded_bow_enchanting.ExpandedBowEnchanting;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
@Mixin(Enchantment.class)
public abstract class BowModification {
    @ModifyReturnValue(method = "isAcceptableItem", at = @At("RETURN"))
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
    @ModifyReturnValue(method = "canBeCombined", at = @At("RETURN"))
    private static boolean hookCanBeCombined(boolean original, RegistryEntry<Enchantment> first, RegistryEntry<Enchantment> second) { return ExpandedBowEnchanting.INSTANCE.getConfig().getInfinityAndMendingCanBeMixed() && (first.matchesKey(Enchantments.INFINITY) && second.matchesKey(Enchantments.MENDING) || first.matchesKey(Enchantments.MENDING) && second.matchesKey(Enchantments.INFINITY)) || original; }
}