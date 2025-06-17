package btpos.mcmods.terminus.forge

import btpos.mcmods.terminus.forge.world.chunk.ChunkAuraCapabilityProvider
import btpos.mcmods.terminus.world.chunk.IChunkAuraData
import net.minecraft.world.level.chunk.LevelChunk
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityToken
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.event.AttachCapabilitiesEvent

object ModCapabilities {
	val CHUNK_AURA: Capability<IChunkAuraData> = CapabilityManager.get(object : CapabilityToken<IChunkAuraData>(){})
	
	fun registerCapabilities(evt: RegisterCapabilitiesEvent) {
		evt.register(IChunkAuraData::class.java)
	}
	
	fun attachChunkCapability(evt: AttachCapabilitiesEvent<LevelChunk>) {
		evt.addCapability(IChunkAuraData.ID, ChunkAuraCapabilityProvider())
	}
}