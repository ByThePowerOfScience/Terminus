@file:Suppress("NOTHING_TO_INLINE")

package btpos.mcmods.terminus.devutil.ext.vanilla

import net.minecraft.world.level.ChunkPos
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.chunk.ChunkAccess

inline fun LevelAccessor.getChunk(pos: ChunkPos): ChunkAccess = this.getChunk(pos.x, pos.z)