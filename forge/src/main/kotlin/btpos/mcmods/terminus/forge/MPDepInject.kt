package btpos.mcmods.terminus.forge

import btpos.mcmods.terminus.IExtensionFunctions
import btpos.mcmods.terminus.devutil.Actual
import btpos.mcmods.terminus.forge.ModCapabilities.CHUNK_AURA
import btpos.mcmods.terminus.world.chunk.IChunkAuraData
import net.minecraft.world.level.chunk.ChunkAccess
import net.minecraft.world.level.chunk.LevelChunk
import kotlin.jvm.optionals.getOrNull

@Actual
object MPDepInjectImpl {
	@JvmStatic fun extensionFunctions() = ExtensionFunctionsForge
}

object ExtensionFunctionsForge : IExtensionFunctions {
	override fun getAuraData(chunk: ChunkAccess): IChunkAuraData? {
		return (chunk as? LevelChunk)?.run {
			getCapability(CHUNK_AURA).resolve().getOrNull()
		}
	}
}