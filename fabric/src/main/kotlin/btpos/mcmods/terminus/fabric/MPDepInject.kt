package btpos.mcmods.terminus.fabric

import btpos.mcmods.terminus.IExtensionFunctions
import btpos.mcmods.terminus.devutil.Actual
import btpos.mcmods.terminus.world.chunk.IChunkAuraData
import net.minecraft.world.level.chunk.ChunkAccess
import kotlin.jvm.optionals.getOrNull

@Actual
object MPDepInjectImpl {
	@JvmStatic fun extensionFunctions(): IExtensionFunctions = ExtensionFunctionsFabric
}

object ExtensionFunctionsFabric : IExtensionFunctions {
	override fun getAuraData(chunk: ChunkAccess): IChunkAuraData? {
		return ComponentKeys.CHUNK_AURA.maybeGet(this).getOrNull()
	}
}