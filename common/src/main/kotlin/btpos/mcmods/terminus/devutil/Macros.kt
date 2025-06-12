@file:Suppress("NOTHING_TO_INLINE")

package btpos.mcmods.terminus.devutil

inline fun panic(message: String = "") : Nothing {
	throw IllegalStateException(message)
}