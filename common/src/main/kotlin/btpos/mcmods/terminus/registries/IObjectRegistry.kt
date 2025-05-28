package btpos.mcmods.terminus.registries

import dev.architectury.registry.registries.DeferredRegister

interface IObjectRegistry<T> {
	val REGISTRY: DeferredRegister<T>
	
	fun register() {
		REGISTRY.register()
	}
}