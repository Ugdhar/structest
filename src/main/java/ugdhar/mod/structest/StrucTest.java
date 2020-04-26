package ugdhar.mod.structest;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@SuppressWarnings("deprecation")
@Mod(StrucTest.MODID)
public class StrucTest {

	public static final String MODID = "structest";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final ResourceLocation HOUSE_LOC = new ResourceLocation(StrucTest.MODID, "brick_house");
	
	public static IStructurePieceType BRICK_HOUSE_PIECE = null;
	
	@ObjectHolder(MODID+":brick_house")
	public static Structure<NoFeatureConfig> BRICK_HOUSE; 
	
	public StrucTest() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::commonSetup);
	}
	
	
	public void commonSetup(FMLCommonSetupEvent args) {
		DeferredWorkQueue.runLater(() -> {
			Iterator<Biome> biomes = ForgeRegistries.BIOMES.iterator();
			biomes.forEachRemaining((biome) -> {
				biome.addStructure(BRICK_HOUSE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
				biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, BRICK_HOUSE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
			});
		});
	}
	
}
