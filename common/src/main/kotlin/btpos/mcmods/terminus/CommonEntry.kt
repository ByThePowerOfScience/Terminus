package btpos.mcmods.terminus

import btpos.mcmods.terminus.registries.Items as RegItems

object CommonEntry {
	fun init() {
		runRegistries()
	}
	
	fun runRegistries() {
		RegItems.register()
	}
}