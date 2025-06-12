package btpos.mcmods.terminus.devutil

import btpos.mcmods.terminus.devutil.ext.vanilla.asComponent
import dev.architectury.utils.EnvExecutor
import dev.architectury.utils.GameInstance
import net.minecraft.client.Minecraft
import net.minecraft.world.entity.player.Player
import org.apache.commons.logging.LogFactory
import java.util.function.Supplier
import kotlin.jvm.optionals.getOrNull

object Debug {
	val walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
	
	private val logger = LogFactory.getLog("Terminus/Debug")
	
	val player: Player
		get() = EnvExecutor.getEnvSpecific(Debug_ClientHook::getPlayer, Debug_ServerHook::getPlayer)
	
	fun sendMessage(message: String) {
		player.sendSystemMessage(message.asComponent())
	}
	
	fun put(message: String) {
		logger.debug(message)
		sendMessage(message)
	}
	
	object Trace {
		@Suppress("NOTHING_TO_INLINE")
		inline fun StackWalker.caller(): String {
			return walker.walk {
				it.map {frame -> frame.methodName}.findFirst().getOrNull()
			}.orEmpty()
		}
		
		fun enter() {
			put("ENTER: ${walker.caller()}")
		}
		/*
		Ok so what do I actually want here?
		I don't know if any of this is necessary...
		 */
	}
}

object Debug_ClientHook {
	fun getPlayer(): Supplier<Player> = Supplier { Minecraft.getInstance().player!! }
}

object Debug_ServerHook {
	fun getPlayer(): Supplier<Player> = Supplier { GameInstance.getServer()!!.playerList.players.first() }
}