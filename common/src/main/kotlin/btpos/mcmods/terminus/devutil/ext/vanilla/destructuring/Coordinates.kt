package btpos.mcmods.terminus.devutil.ext.vanilla.destructuring

import net.minecraft.core.BlockPos
import net.minecraft.world.level.ChunkPos

fun BlockPos.component1() = this.x
fun BlockPos.component2() = this.y
fun BlockPos.component3() = this.z

fun ChunkPos.component1() = this.x
fun ChunkPos.component2() = this.z