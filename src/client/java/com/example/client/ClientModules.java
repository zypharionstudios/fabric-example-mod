package com.example.client;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public final class ClientModules {
	public enum Module {
		FREECAM("Freecam"),
		AUTO_TOTEM("Auto Totem"),
		FLY("Fly"),
		XRAY("X-Ray"),
		FULLBRIGHT("Fullbright"),
		NO_FOG("No Fog");

		private final String label;

		Module(String label) {
			this.label = label;
		}

		public String label() {
			return label;
		}
	}

	private static final Path CONFIG = FabricLoader.getInstance().getConfigDir().resolve("zypharion-client.properties");
	private static final Map<Module, Boolean> enabled = new ConcurrentHashMap<>();

	private ClientModules() {
	}

	public static void load() {
		Properties properties = new Properties();
		if (Files.exists(CONFIG)) {
			try (Reader reader = Files.newBufferedReader(CONFIG)) {
				properties.load(reader);
			} catch (IOException exception) {
				System.err.println("Could not read Zypharion Client config: " + exception.getMessage());
			}
		}

		for (Module module : Module.values()) {
			enabled.put(module, Boolean.parseBoolean(properties.getProperty(module.name(), "false")));
		}
	}

	public static boolean isEnabled(Module module) {
		return enabled.getOrDefault(module, false);
	}

	public static void toggle(Module module) {
		enabled.put(module, !isEnabled(module));
		save();
		ClientModuleRuntime.onToggle(module);
	}

	private static void save() {
		Properties properties = new Properties();
		for (Module module : Module.values()) {
			properties.setProperty(module.name(), Boolean.toString(isEnabled(module)));
		}
		try {
			Files.createDirectories(CONFIG.getParent());
			try (Writer writer = Files.newBufferedWriter(CONFIG)) {
				properties.store(writer, "Zypharion Client settings");
			}
		} catch (IOException exception) {
			System.err.println("Could not save Zypharion Client config: " + exception.getMessage());
		}
	}
}