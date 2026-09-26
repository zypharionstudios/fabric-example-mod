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
		int panelWidth = Math.min(560, this.width - 32);
		int panelHeight = Math.min(310, this.height - 28);
		int panelLeft = (this.width - panelWidth) / 2;
		int panelTop = (this.height - panelHeight) / 2;
		int gap = 12;
		int buttonWidth = (panelWidth - 48 - gap) / 2;
		int startX = panelLeft + 24;
		int startY = panelTop + 86;

		ClientModules.Module[] modules = ClientModules.Module.values();
		for (int index = 0; index < modules.length; index++) {
			ClientModules.Module module = modules[index];
			int column = index % 2;
			int row = index / 2;
			int x = startX + column * (buttonWidth + gap);
			int y = startY + row * 32;
			this.addRenderableWidget(Button.builder(label(module), button -> {
				ClientModules.toggle(module);
				button.setMessage(label(module));
			}).bounds(x, y, buttonWidth, 22).build());
		}

		this.addRenderableWidget(Button.builder(Component.literal("SCHLIESSEN"), button -> this.onClose())
				.bounds(panelLeft + panelWidth - 132, panelTop + panelHeight - 36, 108, 20)
				.build());
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		int panelWidth = Math.min(560, this.width - 32);
		int panelHeight = Math.min(310, this.height - 28);
		int left = (this.width - panelWidth) / 2;
		int top = (this.height - panelHeight) / 2;
		graphics.fill(0, 0, this.width, this.height, 0xA900080D);
		graphics.fill(left, top, left + panelWidth, top + panelHeight, 0xF20B1118);
		graphics.fill(left, top, left + panelWidth, top + 2, 0xFF39D6C5);
		graphics.fill(left + 18, top + 61, left + panelWidth - 18, top + 62, 0xFF263640);
		graphics.outline(left, top, panelWidth, panelHeight, 0xFF31434C);
		graphics.fill(left + 20, top + 18, left + 23, top + 44, 0xFF39D6C5);
		graphics.text(this.font, "Z Y P H A R I O N", left + 32, top + 16, 0xFFF2FAFA, true);
		graphics.text(this.font, "CLIENT  /  MODULE CONTROL", left + 32, top + 34, 0xFF8EA7AD, false);
		graphics.text(this.font, "" + enabledCount() + " MODULES ACTIVE", left + 20, top + 70, 0xFF8EA7AD, false);
		graphics.text(this.font, "26.2", left + panelWidth - 56, top + 34, 0xFF39D6C5, true);
		super.extractRenderState(graphics, mouseX, mouseY, delta);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	private static Component label(ClientModules.Module module) {
		return Component.literal((ClientModules.isEnabled(module) ? "[ON]  " : "[OFF]  ") + module.label());
	}

	private static int enabledCount() {
		int count = 0;
		for (ClientModules.Module module : ClientModules.Module.values()) {
			if (ClientModules.isEnabled(module)) count++;
		}
		return count;
	}
}