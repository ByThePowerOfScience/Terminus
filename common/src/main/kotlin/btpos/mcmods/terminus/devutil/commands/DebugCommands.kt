package btpos.mcmods.terminus.devutil.commands

import btpos.mcmods.terminus.devutil.dsl.brigadier.Command.literal
import btpos.mcmods.terminus.devutil.dsl.brigadier.CommandLiteral
import btpos.mcmods.terminus.devutil.ext.java.join
import btpos.mcmods.terminus.devutil.ext.java.mapThis
import btpos.mcmods.terminus.devutil.ext.java.mapToString
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.LivingEntity

object DebugCommands {
	
	fun make(): CommandLiteral {
		return literal("terminus") {
			literal("hand") {
				executes { ctx ->
					val ent = ctx.source.entity as? LivingEntity ?: return@executes 0
					
					val response = ent.getItemInHand(InteractionHand.MAIN_HAND).takeIf { !it.isEmpty }.let {
						if (it == null)
							return@let "No item found"
						
						val loc = it.item.`arch$registryName`() ?: "unknown"
						val tags = it.tags.mapThis { " - #$registry/$location" }.join("\n")
						
						"Item: $loc\n" +
						"Tags:\n" + tags
					}
					
					ctx.source.sendSuccess({ Component.literal(response) }, true)
					
					return@executes 1
				}
			}
		}
	}
}