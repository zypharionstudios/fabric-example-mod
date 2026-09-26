package com.example.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class ExampleModClient implements ClientModInitializer {
	private static KeyMapping menuKey;

	@Override
	public void onInitializeClient() {
		ClientModules.load();
		KeyMapping.Category category = KeyMapping.Category.register(
				Identifier.fromNamespaceAndPath("zypharion-client", "main")
		);
		menuKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
				"key.zypharion-client.open_menu",
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_EQUAL,
				category
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			ClientModuleRuntime.tick(client);
			while (menuKey.consumeClick()) {
				if (client.gui.screen() instanceof ClientMenuScreen) {
					client.gui.setScreen(null);
				} else if (client.player != null && client.gui.screen() == null) {
					client.gui.setScreen(new ClientMenuScreen());
				}
			}
		});
	}
}