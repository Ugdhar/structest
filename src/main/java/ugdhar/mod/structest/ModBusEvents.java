package ugdhar.mod.structest;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD, modid=StrucTest.MODID)
public class ModBusEvents {
	
	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> args) {
		 StrucTest.BRICK_HOUSE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, StrucTest.HOUSE_LOC, BrickHousePiece.Piece::new);
		 args.getRegistry().register(new BrickHouse(NoFeatureConfig::deserialize).setRegistryName(StrucTest.HOUSE_LOC));
	}

}
