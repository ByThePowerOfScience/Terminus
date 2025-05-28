@file:Suppress("NOTHING_TO_INLINE")

package btpos.mcmods.terminus.devutil.ext.java

import java.util.stream.Collectors
import java.util.stream.Stream

inline fun <T> Stream<T?>.mapToString(): Stream<String> {
	return this.map(java.lang.String::valueOf)
}

inline fun <T> Stream<T?>.join(separator: String = ""): String {
	return this.mapToString().join(separator)
}

inline fun Stream<String>.join(separator: String = ""): String {
	return this.collect(Collectors.joining(separator))
}

inline fun <T, U> Stream<T>.mapThis(noinline transformer: T.() -> U): Stream<U> {
	return this.map(transformer)
}