package btpos.mcmods.terminus.devutil.ext.kotlin

inline fun <T> T?.ifNull(action: () -> Unit): T? {
	if (this == null)
		action()
	return this
}