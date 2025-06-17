package btpos.mcmods.terminus.util

import btpos.mcmods.terminus.devutil.ext.vanilla.destructuring.component1
import btpos.mcmods.terminus.devutil.ext.vanilla.destructuring.component2
import com.mojang.serialization.Codec
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps

interface INbtSerializable<SELF> : CopyableThing<SELF>
		where SELF : CopyableThing<SELF>,
		      SELF : INbtSerializable<SELF>
{
	@Suppress("UNCHECKED_CAST")
	private fun self(): SELF = this as SELF
	
	fun codec(): Codec<SELF>
	
	fun populateFromNbt(tag: CompoundTag) {
		codec().decode(NbtOps.INSTANCE, tag).get().ifLeft { (made, _) ->
			copyFrom(made)
		}.ifRight { res ->
			TODO("I don't know what a 'partial result' is")
		}
	}
	
	fun writeAsNbt(tag: CompoundTag) {
		codec().encode(self(), NbtOps.INSTANCE, tag)
	}
}