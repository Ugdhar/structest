package ugdhar.mod.structest;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("deprecation")
@Mod(StrucTest.MODID)
public class StrucTest {

	public static final String MODID = "structest";
	
	public static final ResourceLocation HOUSE_LOC = new ResourceLocation(MODID, "brick_house");
	
	public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, MODID);
	public static RegistryObject<Feature<?>> BRICK_HOUSE = FEATURES.register("brick_house", () -> new BrickHouse(NoFeatureConfig::deserialize));
	
	
	public StrucTest() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::commonSetup);
	}
	
	
	public void commonSetup(FMLCommonSetupEvent args) {
		DeferredWorkQueue.runLater(() -> {
			//Biomes.PLAINS.addStructure(null);
		});
	}
	
}
