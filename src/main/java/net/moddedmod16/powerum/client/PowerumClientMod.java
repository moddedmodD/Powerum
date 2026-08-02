package net.moddedmod16.powerum.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PowerumClientMod implements ClientModInitializer {

	public static final Identifier POWERUM_DEBUG_KEY = Identifier.fromNamespaceAndPath("powerum", "powerum_debug_info");
	public static final String MOD_ID = "powerum";
	public static final Logger LOGGER = LoggerFactory.getLogger("Powerum");

	static {
		LOGGER.info("Initializing Powerum");
	}

	public static final ModContainer Powerum = FabricLoader.getInstance()
			.getModContainer(MOD_ID)
			.orElseThrow(NullPointerException::new);

	public static final String MOD_VERSION = Powerum
			.getMetadata()
			.getVersion()
			.getFriendlyString();

	public static final String MINECRAFT_VERSION = FabricLoader.getInstance()
			.getModContainer("minecraft")
			.map(container -> container.getMetadata().getVersion().getFriendlyString())
			.orElseThrow(NullPointerException::new);

	public static final String SPLIT_MOD_VERSION = MOD_VERSION.
			split("-")[0]
			+ "+" + MINECRAFT_VERSION;

	@Override
	public void onInitializeClient(){
		DebugScreenEntries.register(POWERUM_DEBUG_KEY, new DebugScreenEntry() {
			@Override
			public void display(@NonNull DebugScreenDisplayer displayer, @Nullable Level serverOrClientLevel, @Nullable LevelChunk clientChunk, @Nullable LevelChunk serverChunk) {
				displayer.addLine("§aPowerum Optimization (" + SPLIT_MOD_VERSION + ")§r");
			}
		});
		LOGGER.info("Powerum v{} initialized", MOD_VERSION);
	}
}
