package btpos.mcmods.terminus.multiplatform

import btpos.mcmods.terminus.devutil.expect
import btpos.mcmods.terminus.world.chunk.IChunkAuraData
import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.world.level.chunk.ChunkAccess

val ChunkAccess.auraData: IChunkAuraData
	@ExpectPlatform get() = expect()
