package com.smushytaco.expanded_bow_enchanting
import io.wispforest.owo.config.annotation.Config
import io.wispforest.owo.config.annotation.Modmenu
@Modmenu(modId = ExpandedBowEnchanting.MOD_ID)
@Config(name = ExpandedBowEnchanting.MOD_ID, wrapperName = "ModConfig")
@Suppress("UNUSED")
class ModConfiguration {
    @JvmField
    var infinityAndMendingCanBeMixed = true
    @JvmField
    var canUseLootingOnBow = true
    @JvmField
    var canUsePiercingOnBow = true
    @JvmField
    var canUseMultishotOnBow = true
}