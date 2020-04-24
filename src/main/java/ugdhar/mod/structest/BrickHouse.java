package ugdhar.mod.structest;

import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class BrickHouse extends ScatteredStructure<ProbabilityConfig> {

	public BrickHouse(Function<Dynamic<?>, ? extends ProbabilityConfig> configFactoryIn) {
		super(configFactoryIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getSeedModifier() {
		// TODO Auto-generated method stub
		return 42;
	}

	@Override
	public IStartFactory getStartFactory() {
		// TODO Auto-generated method stub
		return BrickHouse.Start::new;
	}

	@Override
	public String getStructureName() {
		// TODO Auto-generated method stub
		return "BrickHouse";
	}

	@Override
	public int getSize() {
		return 1;
	}

	public class Start extends StructureStart {

		public Start(Structure<?> p_i225876_1_, int p_i225876_2_, int p_i225876_3_, MutableBoundingBox p_i225876_4_, int p_i225876_5_, long p_i225876_6_) {
			super(p_i225876_1_, p_i225876_2_, p_i225876_3_, p_i225876_4_, p_i225876_5_, p_i225876_6_);
		}

		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
			@SuppressWarnings("unchecked")
			ProbabilityConfig probConfig = (ProbabilityConfig)generator.getStructureConfig(biomeIn, (Structure<ProbabilityConfig>) StrucTest.BRICK_HOUSE.get());
			int worldX = chunkX * 16;
			int worldZ = chunkZ * 16;
			BlockPos blockpos = new BlockPos(worldX, 90, worldZ);
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
			// DISSECT IGLOOPIECES
			this.recalculateStructureSize();
		}
		
	}

}
