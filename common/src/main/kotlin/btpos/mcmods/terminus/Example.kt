package btpos.mcmods.terminus

import btpos.mcmods.terminus.devutil.expect
import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.world.item.ItemStack

@ExpectPlatform
fun ItemStack.asVis(): Any = expect()