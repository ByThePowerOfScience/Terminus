package btpos.mcmods.thaumonomical.forge;

import btpos.mcmods.thaumonomical.Thaumonomical;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Thaumonomical.MOD_ID) public final class ThaumonomicalForge {
	public ThaumonomicalForge() {
		// Submit our event bus to let Architectury API register our content on the right time.
		EventBuses.registerModEventBus(Thaumonomical.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
		
		// Run our common setup.
		Thaumonomical.init();
	}
}
