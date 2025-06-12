@file:Suppress("NOTHING_TO_INLINE")

package btpos.mcmods.terminus.devutil

import dev.architectury.utils.PlatformExpectedError

inline fun expect(): Nothing = throw PlatformExpectedError()

/**
 * Just to suppress the "unused" warning when using `@ExpectPlatform`
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class Actual