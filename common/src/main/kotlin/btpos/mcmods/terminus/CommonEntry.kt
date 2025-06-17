package btpos.mcmods.terminus

import btpos.mcmods.terminus.devutil.commands.DebugCommands
import dev.architectury.event.events.common.CommandRegistrationEvent
import btpos.mcmods.terminus.registries.Items as RegItems

object CommonEntry {
	fun init() {
		initRegistries()
		initDebugCommands()
	}
	
	fun initRegistries() {
		RegItems.register()
		
	}
	
	fun initDebugCommands() {
		CommandRegistrationEvent.EVENT.register { dispatcher, _, _ ->
			dispatcher.register(DebugCommands.make())
		}
	}
}