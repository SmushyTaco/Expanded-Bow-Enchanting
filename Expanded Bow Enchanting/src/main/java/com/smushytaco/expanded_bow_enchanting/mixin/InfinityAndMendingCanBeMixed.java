package com.smushytaco.expanded_bow_enchanting.mixin;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.smushytaco.expanded_bow_enchanting.ExpandedBowEnchanting;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.enchantment.MendingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
@Mixin(InfinityEnchantment.class)
public abstract class InfinityAndMendingCanBeMixed {
    @ModifyReturnValue(method = "canAccept", at = @At("RETURN"))
    private boolean hookCanAccept(boolean original, Enchantment other) { return ExpandedBowEnchanting.INSTANCE.getConfig().getInfinityAndMendingCanBeMixed() && other instanceof MendingEnchantment || original; }
}