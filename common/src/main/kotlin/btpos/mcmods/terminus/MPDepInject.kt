package btpos.mcmods.terminus

import btpos.mcmods.terminus.devutil.expect
import btpos.mcmods.terminus.world.chunk.IChunkAuraData
import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.world.level.chunk.ChunkAccess

object MPDepInject {
	@ExpectPlatform @JvmStatic fun extensionFunctions(): IExtensionFunctions = expect()
}

interface IExtensionFunctions {
	fun getAuraData(chunk: ChunkAccess): IChunkAuraData?
}