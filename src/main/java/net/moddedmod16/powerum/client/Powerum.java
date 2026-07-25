package net.moddedmod16.powerum.client;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Powerum {

	public static final String MOD_ID = "powerum";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ModContainer Powerum = FabricLoader.getInstance()
			.getModContainer(MOD_ID)
			.orElseThrow(NullPointerException::new);

	public static void init(){

		final String MOD_VERSION = Powerum
				.getMetadata()
				.getVersion()
				.getFriendlyString();

		final String MOD_NAME = Powerum
				.getMetadata()
				.getName();

		LOGGER.info("{} v{} initialized", MOD_NAME, MOD_VERSION);
	}
}
