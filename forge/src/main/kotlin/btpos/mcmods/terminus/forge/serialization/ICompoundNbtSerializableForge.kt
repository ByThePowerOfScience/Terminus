package btpos.mcmods.terminus.forge.serialization

import btpos.mcmods.terminus.util.serialization.ICompoundNbtSerializable
import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.util.INBTSerializable

/**
 * Default functionality to bridge the gap between my crossplatform [ICompoundNbtSerializable] and Forge's [INBTSerializable].
 */
interface ICompoundNbtSerializableForge<SELF : ICompoundNbtSerializable<SELF>>
	: ICompoundNbtSerializable<SELF>, INBTSerializable<CompoundTag>
{
	@Suppress("UNCHECKED_CAST")
	private fun self(): SELF = this as SELF
	
	override fun serializeNBT(): CompoundTag {
		return CompoundTag().also {
			self().writeAsNbt(it)
		}
	}
	
	override fun deserializeNBT(tag: CompoundTag) {
		self().populateFromNbt(tag)
	}
}