@file:Suppress("NOTHING_TO_INLINE")

package btpos.mcmods.terminus.devutil.ext.vanilla

import net.minecraft.network.chat.Component

inline fun String?.asComponent() = Component.literal(this ?: "null")
