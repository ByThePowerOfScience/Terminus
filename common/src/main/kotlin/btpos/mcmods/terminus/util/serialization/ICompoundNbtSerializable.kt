package btpos.mcmods.terminus.util.serialization

import btpos.mcmods.terminus.devutil.ext.vanilla.destructuring.component1
import btpos.mcmods.terminus.devutil.ext.vanilla.destructuring.component2
import btpos.mcmods.terminus.util.IReverseCloneable
import com.mojang.serialization.Codec
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps

/**
 * Platform-agnostic NBT serialization helper using codecs.
 * Encodes/decodes the data of `this` to and from a [net.minecraft.nbt.CompoundTag] using the [com.mojang.serialization.Codec] provided with [codec].
 */
interface ICompoundNbtSerializable<SELF> : IReverseCloneable<SELF>
		where SELF : IReverseCloneable<SELF>,
		      SELF : ICompoundNbtSerializable<SELF>
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