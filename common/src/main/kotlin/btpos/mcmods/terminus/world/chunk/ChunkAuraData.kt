package btpos.mcmods.terminus.world.chunk

import btpos.mcmods.terminus.MPDepInject
import btpos.mcmods.terminus.devutil.expect
import btpos.mcmods.terminus.loc
import btpos.mcmods.terminus.util.INbtSerializable
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.world.level.chunk.ChunkAccess

val ChunkAccess.auraData: IChunkAuraData?
	get() = MPDepInject.extensionFunctions().getAuraData(this)


interface IChunkAuraData : INbtSerializable<IChunkAuraData> {
	companion object {
		val CODEC: Codec<IChunkAuraData> = RecordCodecBuilder.create {
			it.group(
					Codec.INT.fieldOf("vis").forGetter(IChunkAuraData::vis),
					Codec.INT.fieldOf("flux").forGetter(IChunkAuraData::flux)
			).apply(it, ::ChunkAuraData)
		}
		
		val ID = loc("chunk_aura")
	}
	
	var vis: Int
	var flux: Int
	
	override fun codec() = CODEC
	
	override fun copyFrom(other: IChunkAuraData) {
		this.vis = other.vis
		this.flux = other.flux
	}
}

data class ChunkAuraData(
	override var vis: Int = 0,
	override var flux: Int = 0
) : IChunkAuraData