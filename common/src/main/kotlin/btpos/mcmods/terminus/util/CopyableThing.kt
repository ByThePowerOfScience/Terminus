package btpos.mcmods.terminus.util

// this probably already exists in the jdk but idk where
interface CopyableThing<T> {
	fun copyFrom(other: T)
}