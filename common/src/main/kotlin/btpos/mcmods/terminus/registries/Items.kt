package btpos.mcmods.terminus.registries

import btpos.mcmods.terminus.MOD_ID
import btpos.mcmods.terminus.items.basic.SimpleDamageWand
import btpos.mcmods.terminus.devutil.ext.architectury.registering as reg
import dev.architectury.registry.registries.DeferredRegister
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.Item

object Items : IObjectRegistry<Item> {
	/** An unbreakable tool item */
	private val P_TOOL: Item.Properties = Item.Properties().durability(-1).stacksTo(1)
	
	override val REGISTRY = DeferredRegister.create(MOD_ID, Registries.ITEM)
	
	val SIMPLE_DAMAGE_WAND by REGISTRY.reg("simple_damage_wand") { SimpleDamageWand(P_TOOL) }
}