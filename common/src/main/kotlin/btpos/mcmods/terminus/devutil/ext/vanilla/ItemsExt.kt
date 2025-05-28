@file:Suppress("NOTHING_TO_INLINE")

package btpos.mcmods.terminus.devutil.ext.vanilla

import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

inline fun Item.stack() = ItemStack(this)
inline fun Item.stack(count: Int) = ItemStack(this, count)