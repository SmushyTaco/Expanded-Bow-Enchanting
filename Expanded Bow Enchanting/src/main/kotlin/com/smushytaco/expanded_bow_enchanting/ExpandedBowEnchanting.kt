package com.smushytaco.expanded_bow_enchanting
import com.smushytaco.expanded_bow_enchanting.configuration_support.ModConfiguration
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.enchantment.Enchantment
import net.minecraft.registry.*
import kotlin.jvm.optionals.getOrNull
object ExpandedBowEnchanting : ModInitializer {
    const val MOD_ID = "expanded_bow_enchanting"
    lateinit var config: ModConfiguration
        private set
    private var dynamicRegistryManager: DynamicRegistryManager? = null
    override fun onInitialize() {
        AutoConfig.register(ModConfiguration::class.java) { definition: Config, configClass: Class<ModConfiguration> ->
            GsonConfigSerializer(definition, configClass)
        }
        config = AutoConfig.getConfigHolder(ModConfiguration::class.java).config
        ServerLifecycleEvents.SERVER_STARTED.register { server -> dynamicRegistryManager = server.registryManager }
    }
    fun isSameEnchantment(enchantment: Enchantment, enchantmentRegistryKey: RegistryKey<Enchantment>) = dynamicRegistryManager?.getOptional(RegistryKeys.ENCHANTMENT)?.getOrNull()?.getEntry(enchantment)?.matchesKey(enchantmentRegistryKey) ?: false
}