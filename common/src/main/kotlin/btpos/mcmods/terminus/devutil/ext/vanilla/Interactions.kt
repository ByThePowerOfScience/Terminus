@file:Suppress("NOTHING_TO_INLINE")

package btpos.mcmods.terminus.devutil.ext.vanilla

import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder

inline fun <T : Any> InteractionResult.holder(held: T): InteractionResultHolder<T> {
	return InteractionResultHolder(this, held)
}

inline fun String.asComponent() = Component.literal(this)