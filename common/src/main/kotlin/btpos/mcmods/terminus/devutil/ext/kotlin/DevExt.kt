package btpos.mcmods.terminus.devutil.ext.kotlin

fun <T> T?.ifNull(action: () -> Unit): T? {
	if (this == null)
		action()
	return this
}