package btpos.mcmods.terminus.items.basic

import btpos.mcmods.terminus.devutil.ext.vanilla.holder
import btpos.mcmods.terminus.LOGGER
import com.mojang.logging.LogUtils
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.EntityHitResult

/**
 * An exceedingly simple item that, when right-clicked while looking at an entity, damages it for 1 heart.
 */
class SimpleDamageWand(props: Properties) : Item(props) {
	companion object {
		const val MAX_DISTANCE: Int = 10
	}
	
	override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
		LOGGER.trace("> use")
		
		val itemInHand = player.getItemInHand(usedHand)
		
		if (level.isClientSide) {
			LOGGER.debug("passed")
			LOGGER.trace("< use")
			return InteractionResult.PASS.holder(itemInHand)
		}
		
		val target = player.pick(MAX_DISTANCE.toDouble(), 1f, false).let {
			it as? EntityHitResult
		}?.entity as? LivingEntity ?: return InteractionResult.PASS.holder(itemInHand)
		
		target.hurt(level.damageSources().generic(), 1f)
		
		LOGGER.debug("success")
		LOGGER.trace("< use")
		return InteractionResult.SUCCESS.holder(itemInHand)
	}
}