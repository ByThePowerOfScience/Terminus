package btpos.mcmods.terminus.forge.world.chunk

import btpos.mcmods.terminus.forge.ModCapabilities.CHUNK_AURA
import btpos.mcmods.terminus.world.chunk.ChunkAuraData
import btpos.mcmods.terminus.world.chunk.IChunkAuraData
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.common.util.LazyOptional

data class ChunkAuraData_Forge(val data: IChunkAuraData = ChunkAuraData())
	: IChunkAuraData by data, INBTSerializable<CompoundTag>
{
	override fun serializeNBT(): CompoundTag {
		return CompoundTag().also {
			data.writeAsNbt(it)
		}
	}
	
	override fun deserializeNBT(tag: CompoundTag) {
		data.populateFromNbt(tag)
	}
}

data class ChunkAuraCapabilityProvider(val inst: ChunkAuraData_Forge = ChunkAuraData_Forge())
	: ICapabilityProvider, INBTSerializable<CompoundTag> by inst
{
	val defaultgetter: LazyOptional<IChunkAuraData> = LazyOptional.of { inst }
	
	override fun <T : Any> getCapability(capability: Capability<T>, arg: Direction?): LazyOptional<T> {
		return CHUNK_AURA.orEmpty(capability, defaultgetter)
	}
}