package btpos.mcmods.terminus.devutil.ext.vanilla

operator fun <T, U> com.mojang.datafixers.util.Pair<T, U>.component1(): T = this.first
operator fun <T, U> com.mojang.datafixers.util.Pair<T, U>.component2(): U = this.second