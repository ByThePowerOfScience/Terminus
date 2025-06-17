package btpos.mcmods.terminus.devutil.commands

import btpos.mcmods.terminus.LOGGER
import btpos.mcmods.terminus.devutil.dsl.brigadier.CommandLiteral
import btpos.mcmods.terminus.devutil.dsl.brigadier.literal
import btpos.mcmods.terminus.devutil.ext.java.join
import btpos.mcmods.terminus.devutil.ext.java.mapThis
import btpos.mcmods.terminus.devutil.ext.vanilla.asComponent
import btpos.mcmods.terminus.devutil.ext.vanilla.getChunk
import btpos.mcmods.terminus.world.chunk.auraData
import com.mojang.brigadier.context.CommandContext
import net.minecraft.commands.CommandSourceStack
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.LivingEntity

object DebugCommands {
	fun make(): CommandLiteral {
		return literal("terminus") {
			then(makeHandCommand())
			then(checkChunkAura())
		}
	}
	
	fun makeHandCommand() = literal("hand") {
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
	
	fun CommandContext<CommandSourceStack>.debug(s: String) {
		LOGGER.warn(s)
		this.source.sendSystemMessage(s.asComponent())
	}
	
	fun checkChunkAura() = literal("chunk") {
		executes { ctx ->
			ctx.debug("Started command")
			try {
				val player = ctx.source.takeIf { it.isPlayer }?.player ?: run {
					ctx.debug("Command must be run by a player")
					return@executes 0
				}
				ctx.debug("got player")
				
				val level = ctx.source.level
				
				ctx.debug("got level")
				
				val chunk = level.getChunk(player.chunkPosition())
				
				ctx.debug("got chunk")
				
				val data = chunk.auraData ?: run {
					ctx.debug("No aura data!")
					return@executes 0
				}
				
				ctx.debug("got aura data")
				
				ctx.debug("Vis: ${data.vis}\nFlux: ${data.flux}")
				
				return@executes 1
				
			} catch (e: Exception) {
				ctx.debug("Exception: ${e.message}")
				ctx.debug(e.stackTraceToString())
				return@executes 0
			}
		}
	}
}