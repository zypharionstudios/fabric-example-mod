package com.example.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class ClientMenuScreen extends Screen {
	public ClientMenuScreen() {
		super(Component.literal("Zypharion Client"));
	}

	@Override
	protected void init() {
		int buttonWidth = 190;
		int gap = 10;
		int columns = 2;
		int gridWidth = buttonWidth * columns + gap;
		int startX = (this.width - gridWidth) / 2;
		int startY = Math.max(64, (this.height - 190) / 2);

		ClientModules.Module[] modules = ClientModules.Module.values();
		for (int index = 0; index < modules.length; index++) {
			ClientModules.Module module = modules[index];
			int column = index % columns;
			int row = index / columns;
			int x = startX + column * (buttonWidth + gap);
			int y = startY + row * 28;
			this.addRenderableWidget(Button.builder(label(module), button -> {
				ClientModules.toggle(module);
				button.setMessage(label(module));
			}).bounds(x, y, buttonWidth, 20).build());
		}

		this.addRenderableWidget(Button.builder(Component.literal("Schliessen"), button -> this.onClose())
				.bounds((this.width - 100) / 2, Math.min(this.height - 34, startY + 4 * 28), 100, 20)
				.build());
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractRenderState(graphics, mouseX, mouseY, delta);
		graphics.text(this.font, this.title, (this.width - this.font.width(this.title)) / 2, 28, 0xFFFFFFFF, true);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	private static Component label(ClientModules.Module module) {
		return Component.literal(module.label() + ": " + (ClientModules.isEnabled(module) ? "AN" : "AUS"));
	}
}