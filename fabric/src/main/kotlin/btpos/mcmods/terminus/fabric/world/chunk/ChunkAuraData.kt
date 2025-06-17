package btpos.mcmods.terminus.fabric.world.chunk

import btpos.mcmods.terminus.devutil.Actual
import btpos.mcmods.terminus.fabric.ComponentKeys
import btpos.mcmods.terminus.fabric.ComponentsRegistrar
import btpos.mcmods.terminus.world.chunk.ChunkAuraData
import btpos.mcmods.terminus.world.chunk.IChunkAuraData
import dev.onyxstudios.cca.api.v3.component.Component
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.level.chunk.ChunkAccess
import kotlin.jvm.optionals.getOrNull




data class ChunkAuraData_Fabric(val data: ChunkAuraData) : IChunkAuraData by data, Component {
	constructor() : this(ChunkAuraData())
	
	override fun readFromNbt(p0: CompoundTag) {
		data.populateFromNbt(p0)
	}
	
	override fun writeToNbt(p0: CompoundTag) {
		data.writeAsNbt(p0)
	}
}