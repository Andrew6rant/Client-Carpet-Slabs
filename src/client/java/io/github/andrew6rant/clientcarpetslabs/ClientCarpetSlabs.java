package io.github.andrew6rant.clientcarpetslabs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.Block;
import net.minecraft.block.CarpetBlock;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class ClientCarpetSlabs implements ClientModInitializer {

	public static final String MOD_ID = "clientcarpetslabs";

	@Override
	public void onInitializeClient() {
		ModelLoadingPlugin.register(pluginContext -> {
			pluginContext.addModels(new Identifier(MOD_ID, "red_carpet_slab"));
		});
		/*
		Set<Identifier> carpetModelIds = new HashSet<>();
		for (Block block : Registries.BLOCK) {
			if (block instanceof CarpetBlock) {
				System.out.println("added!! "+BlockModels.getModelId(block.getDefaultState()));
				carpetModelIds.add(BlockModels.getModelId(block.getDefaultState()));
			}
		}
		RegistryEntryAddedCallback.event(Registries.BLOCK).register((raw, id, block) -> {
			if (block instanceof CarpetBlock) {
				System.out.println("added "+id+", "+BlockModels.getModelId(block.getDefaultState()));
				carpetModelIds.add(BlockModels.getModelId(block.getDefaultState()));
			}
		});

		ModelLoadingPlugin.register(pluginContext -> {
			pluginContext.modifyModelOnLoad().register(ModelModifier.OVERRIDE_PHASE, (model, context) -> {
				if (carpetModelIds.contains(context.id())) {
					System.out.println("yoooo!!! "+context.id());
					//return context.getOrLoadModel(ModelLoader.MISSING_ID);
				}
				return model;
			});

			pluginContext.modifyModelBeforeBake().register(ModelModifier.OVERRIDE_PHASE, (unbakedModel, context) -> {
				if (carpetModelIds.contains(context.id())) {
					Function<SpriteIdentifier, Sprite> textureGetter = context.textureGetter();
					//textureGetter.apply()
					unbakedModel.getModelDependencies().add()
					System.out.println("yoooo (before bake)!!! "+context.id());
					//return context.getOrLoadModel(ModelLoader.MISSING_ID);
				}
				//if (context.id().getPath().equals("block/brown_glazed_terracotta")) {
				//	return context.loader().getOrLoadModel(ModelLoader.MISSING_ID);
				//}
				return unbakedModel;
			});
		});
		*/
	}
}