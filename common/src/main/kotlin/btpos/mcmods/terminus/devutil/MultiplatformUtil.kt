@file:Suppress("NOTHING_TO_INLINE")

package btpos.mcmods.terminus.devutil

inline fun expect(): Nothing = throw IllegalStateException("Expected platform-specific implementation to be merged!")

/**
 * Just to suppress the "unused" warning when using `@ExpectPlatform`
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class Actual