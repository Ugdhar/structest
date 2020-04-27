package ugdhar.mod.structest;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.chunk.IChunk;
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
	public boolean func_225558_a_(BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn, Random randIn, int chunkX, int chunkZ, Biome biomeIn) {
	      ChunkPos chunkpos = this.getStartPositionForPosition(generatorIn, randIn, chunkX, chunkZ, 0, 0);
	      boolean xMatch = chunkX == chunkpos.x;
	      boolean zMatch = chunkZ == chunkpos.z;
	      boolean hasStruct = generatorIn.hasStructure(biomeIn, this);
    	  boolean result = xMatch && zMatch && hasStruct;
	      if(xMatch && zMatch)
	    	  StrucTest.LOGGER.info("Matching!");
	      return  result;
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		// TODO Auto-generated method stub
	      if (!worldIn.getWorldInfo().isMapFeaturesEnabled()) {
	          return false;
	       } else {
	          int i = pos.getX() >> 4;
	          int j = pos.getZ() >> 4;
	          int k = i << 4;
	          int l = j << 4;
	          boolean flag = false;
	          IChunk chnk = worldIn.getChunk(i, j);
	          LongSet structRefs = chnk.getStructureReferences(this.getStructureName());
	          // Never make it inside this for block, the structureReferences is empty.
	          // this method is a copy of the super.place method
	          for(Long olong : structRefs) {
	             ChunkPos chunkpos = new ChunkPos(olong);
	             StructureStart structurestart = worldIn.getChunk(chunkpos.x, chunkpos.z).getStructureStart(this.getStructureName());
	             if (structurestart != null && structurestart != StructureStart.DUMMY) {
	                structurestart.func_225565_a_(worldIn, generator, rand, new MutableBoundingBox(k, l, k + 15, l + 15), new ChunkPos(i, j));
	                flag = true;
	             }
	          }

	          return flag;
	       }
		//boolean placeRes = super.place(worldIn, generator, rand, pos, config); 
		//return placeRes;
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
		return 8;
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
