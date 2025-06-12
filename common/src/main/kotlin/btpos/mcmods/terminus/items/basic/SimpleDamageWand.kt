package btpos.mcmods.terminus.items.basic

import btpos.mcmods.terminus.devutil.Debug
import btpos.mcmods.terminus.devutil.ext.vanilla.holder
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult

/**
 * An exceedingly simple item that, when right-clicked while looking at an entity, damages it for 1 heart.
 */
class SimpleDamageWand(props: Properties) : Item(props) {
	companion object {
		const val MAX_DISTANCE: Double = 50.0
	}
	
	override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
		Debug.put("> use - ${if (level.isClientSide) "client" else "server"}")
		
		val itemInHand = player.getItemInHand(usedHand)
		
		if (level.isClientSide) {
			Debug.put("passing")
			Debug.put("< use - client")
			return InteractionResult.PASS.holder(itemInHand)
		}
		
		// TODO figure out why I can't hit entities
		val target = player.pick(MAX_DISTANCE, 1f, false).let {
			when (it.type) {
				HitResult.Type.ENTITY -> it as EntityHitResult
				HitResult.Type.BLOCK -> {
					Debug.put("hit result: blockff")
					null
				}
				HitResult.Type.MISS -> {
					Debug.put("hit result: miss")
					null
				}
			}
		}?.entity ?: run {
			Debug.put("< use | server - pass")
			return InteractionResult.PASS.holder(itemInHand)
		}
		
		if (target is LivingEntity) {
			target.hurt(level.damageSources().generic(), 1f)
			
			Debug.put("success")
			Debug.put("< use - server")
			return InteractionResult.SUCCESS.holder(itemInHand)
		} else {
			Debug.put("not a living entity")
			Debug.put("actual: ${target.type}")
			Debug.put("< use | server - pass")
			return InteractionResult.PASS.holder(itemInHand)
		}
	}
}