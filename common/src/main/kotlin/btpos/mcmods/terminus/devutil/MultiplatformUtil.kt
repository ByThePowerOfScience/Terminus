@file:Suppress("NOTHING_TO_INLINE")

package btpos.mcmods.terminus.devutil

import btpos.mcmods.terminus.LOGGER
import dev.architectury.platform.Platform

fun expect(): Nothing {
	throw IllegalStateException("Expected ${if (Platform.isFabric()) "Fabric" else "Forge"}-specific method! This is a FATAL ERROR, please report this immediately!").also {
		LOGGER.error("FATAL ERROR", it)
	}
}

/**
 * Just to suppress the "unused" warning on classes when using `@ExpectPlatform`.
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.CLASS)
annotation class Actual