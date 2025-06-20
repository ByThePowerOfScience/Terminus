package btpos.mcmods.terminus.fabric

import btpos.mcmods.terminus.fabric.ComponentKeys.CHUNK_AURA
import btpos.mcmods.terminus.fabric.world.chunk.ChunkAuraData_Fabric
import btpos.mcmods.terminus.loc
import dev.onyxstudios.cca.api.v3.chunk.ChunkComponentFactoryRegistry
import dev.onyxstudios.cca.api.v3.chunk.ChunkComponentInitializer
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry

class ComponentsRegistrar : ChunkComponentInitializer {
	override fun registerChunkComponentFactories(registry: ChunkComponentFactoryRegistry) {
		registry.register(CHUNK_AURA) { ChunkAuraData_Fabric() } // TODO figure out if we need to do anything with the chunk param
	}
}

object ComponentKeys {
	val CHUNK_AURA = ComponentRegistry.getOrCreate(loc("chunk_aura"), ChunkAuraData_Fabric::class.java);
}