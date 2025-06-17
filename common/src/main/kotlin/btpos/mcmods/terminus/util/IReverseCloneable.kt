package btpos.mcmods.terminus.util

// this probably already exists in the jdk but idk where
interface IReverseCloneable<T> {
	/**
	 * Populates the internal state of `this` to match the internal state of `other`.
	 */
	fun copyFrom(other: T)
}