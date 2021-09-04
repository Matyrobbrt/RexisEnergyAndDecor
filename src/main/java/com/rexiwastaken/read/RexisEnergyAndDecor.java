package com.rexiwastaken.read;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rexiwastaken.read.common.item.UraniumBlockItem;
import com.rexiwastaken.read.common.item.UraniumOreItem;
import com.rexiwastaken.read.core.init.*;
import com.rexiwastaken.read.core.itemgroup.READItemGroup;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("read")
@Mod.EventBusSubscriber(modid = RexisEnergyAndDecor.MOD_ID, bus = Bus.MOD)
public class RexisEnergyAndDecor {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "read";

	public RexisEnergyAndDecor() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		bus.addGenericListener(IRecipeSerializer.class, RecipeInit::registerRecipes);

		SoundInit.SOUNDS.register(bus);
		BlockInit.BLOCKS.register(bus);
		ItemInit.ITEMS.register(bus);
		PotionInit.EFFECTS.register(bus);
		TileEntityTypesInit.TILE_ENTITY_TYPE.register(bus);
		ContainerTypesInit.CONTAINER_TYPES.register(bus);
		
		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, FeatureInit::addOres);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent

	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			System.out.println(block);
			if (block == BlockInit.URANIUM_ORE.get()) {
				event.getRegistry()
                .register(new UraniumOreItem (block, new Item.Properties().tab(READItemGroup.READ)).setRegistryName(block.getRegistryName()));
			} else if (block == BlockInit.URANIUM_BLOCK.get()) {
				event.getRegistry()
                .register(new UraniumBlockItem (block, new Item.Properties().tab(READItemGroup.READ)).setRegistryName(block.getRegistryName()));
			} else {
				event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(READItemGroup.READ))
						.setRegistryName(block.getRegistryName()));
			}
			
		});
	}
}
