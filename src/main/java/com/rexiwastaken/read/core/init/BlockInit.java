package com.rexiwastaken.read.core.init;

import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.common.block.FuelGeneratorBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			RexisEnergyAndDecor.MOD_ID);

	//The Ores
	public static final RegistryObject<Block> COPPER_ORE = BLOCKS.register("copper_ore",
			() -> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_GRAY).strength(3f, 3f)
					.harvestTool(ToolType.PICKAXE).harvestLevel(1).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> ALUMINUM_ORE = BLOCKS.register("aluminum_ore",
			() -> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_GRAY).strength(3f, 4f)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> LEAD_ORE = BLOCKS.register("lead_ore",
			() -> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_GRAY).strength(3f, 3f)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> URANIUM_ORE = BLOCKS.register("uranium_ore",
			() -> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_GRAY).strength(6f, 4.5f)
					.harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	
	//The Blocks for the Ores
	public static final RegistryObject<Block> COPPER_BLOCK = BLOCKS.register("copper_block",
			() -> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_GRAY).strength(3f, 3f)
					.harvestTool(ToolType.PICKAXE).harvestLevel(1).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> ALUMINUM_BLOCK = BLOCKS.register("aluminum_block",
			() -> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_GRAY).strength(3f, 4f)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> LEAD_BLOCK = BLOCKS.register("lead_block",
			() -> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_GRAY).strength(3f, 3f)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> URANIUM_BLOCK = BLOCKS.register("uranium_block",
			() -> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_GRAY).strength(6f, 4f)
					.harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	
	//Tile Entity Blocks
	public static final RegistryObject<Block> FUEL_GENERATOR = BLOCKS.register("fuel_generator", () -> new FuelGeneratorBlock());
	
}
