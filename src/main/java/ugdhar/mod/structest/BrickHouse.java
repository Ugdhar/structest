package ugdhar.mod.structest;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class BrickHouse extends ScatteredStructure<NoFeatureConfig> {

	public BrickHouse(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, NoFeatureConfig config) {
		// TODO Auto-generated method stub
		boolean placeRes = super.place(worldIn, generator, rand, pos, config); 
		return placeRes;
	}

	@Override
	protected int getSeedModifier() {
		return 165745296;
	}

	@Override
	public IStartFactory getStartFactory() {
		return BrickHouse.Start::new;
	}

	@Override
	public String getStructureName() {
		return StrucTest.HOUSE_LOC.toString();
	}

	@Override
	public int getSize() {
		return 1;
	}

	public class Start extends StructureStart {

		public Start(Structure<?> structIn, int int_1, int int_2, MutableBoundingBox mutableBB, int int_3, long long_1) {
			super(structIn, int_1, int_2, mutableBB, int_3, long_1);
		}

		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
			int worldX = chunkX * 16;
			int worldZ = chunkZ * 16;
			BlockPos blockpos = new BlockPos(worldX, 90, worldZ);
			this.components.add(new BrickHousePiece.Piece(templateManagerIn, StrucTest.HOUSE_LOC, blockpos, Rotation.NONE, 0));
			this.recalculateStructureSize();
		}
		
	}

}
