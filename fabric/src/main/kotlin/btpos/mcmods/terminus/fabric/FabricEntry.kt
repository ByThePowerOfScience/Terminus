package btpos.mcmods.terminus.fabric

import btpos.mcmods.terminus.EntryCommon
import net.fabricmc.api.ModInitializer

class FabricEntry : ModInitializer {
	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		
		// Run our common setup.
		
		EntryCommon.init()
	}
}
