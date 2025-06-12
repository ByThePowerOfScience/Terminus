package btpos.mcmods.terminus

import net.minecraft.resources.ResourceLocation
import org.slf4j.LoggerFactory

const val MOD_ID = "terminus"

val LOGGER = LoggerFactory.getLogger("Terminus")

fun loc(path: String): ResourceLocation = ResourceLocation(MOD_ID, path)