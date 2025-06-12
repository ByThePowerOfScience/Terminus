package btpos.mcmods.terminus.util

import btpos.mcmods.terminus.devutil.ext.vanilla.component1
import btpos.mcmods.terminus.devutil.ext.vanilla.component2
import com.mojang.serialization.Codec
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps

interface INbtSerializable<SELF> : CopyableThing<SELF> where SELF : CopyableThing<SELF> {
	@Suppress("UNCHECKED_CAST")
	private fun self(): SELF = this as SELF
	
	fun codec(): Codec<SELF>
	
	fun populateFromNbt(tag: CompoundTag) {
		codec().decode(NbtOps.INSTANCE, tag).get().ifLeft { (made, tag) ->
			copyFrom(made)
		}.ifRight { res ->
			TODO("I don't know what a 'partial result' is")
		}
	}
	
	fun writeAsNbt(tag: CompoundTag) {
		codec().encode(self(), NbtOps.INSTANCE, tag)
	}
}