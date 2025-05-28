@file:Suppress("NOTHING_TO_INLINE")

package btpos.mcmods.terminus.devutil.ext.architectury

import btpos.mcmods.terminus.MOD_ID
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import net.minecraft.resources.ResourceLocation
import java.util.function.Supplier
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class RegistryObjectDelegate<out T : Any>(val name: String, val supplier: RegistrySupplier<out T>) : ReadOnlyProperty<Any?, T> {
	override fun getValue(thisRef: Any?, property: KProperty<*>): T {
		return supplier.get()
	}
	
	val id: ResourceLocation
		get() = ResourceLocation(MOD_ID, name)
}

fun <T : Any> DeferredRegister<T>.registering(name: String, supplier: Supplier<out T>): RegistryObjectDelegate<T> {
	return RegistryObjectDelegate(name, this.register(name, supplier))
}