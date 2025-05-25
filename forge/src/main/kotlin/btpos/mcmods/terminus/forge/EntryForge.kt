package btpos.mcmods.terminus.forge

import btpos.mcmods.terminus.EntryCommon
import btpos.mcmods.terminus.MOD_ID
import dev.architectury.platform.forge.EventBuses
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(MOD_ID)
object EntryForge {
	init {
		// Submit our event bus to let Architectury API register our content on the right time.
		EventBuses.registerModEventBus(MOD_ID, MOD_BUS)
		
		// Run our common setup.
		EntryCommon.init()
	}
}
