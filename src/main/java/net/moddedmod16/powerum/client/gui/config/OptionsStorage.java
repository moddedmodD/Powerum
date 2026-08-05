package net.moddedmod16.powerum.client.gui.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.moddedmod16.powerum.client.PowerumClientMod;
import net.fabricmc.loader.api.FabricLoader;
import net.moddedmod16.powerum.client.gui.config.pages.GeneralPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OptionsStorage {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), PowerumClientMod.MOD_ID + "-options.json");
    private static final Logger LOGGER = LoggerFactory.getLogger("Powerum-OptionsStorage");

    public static void save(){

        new Thread(()->{
            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
                JsonObject json = new JsonObject();

                json.addProperty("_comment", "if options are not saving try deleting this file");
                json.addProperty("debug_overlay", GeneralPage.DEBUG_OVERLAY);

                GSON.toJson(json, writer);
                LOGGER.info("Changes successfully applied");
                } catch (IOException e){
                LOGGER.error("Failed to save configuration file");
            }
        }).start();
    }

    public static void load(){
        if (!CONFIG_FILE.exists()){
            save();
        } else {
            try (FileReader reader = new FileReader(CONFIG_FILE)){
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                if (json.has("debug_overlay")){
                    GeneralPage.DEBUG_OVERLAY = json.get("debug_overlay").getAsBoolean();
                    GeneralPage.TEMP_DEBUG_OVERLAY = GeneralPage.DEBUG_OVERLAY;
                }
                LOGGER.info("Configuration file successfully loaded");
            } catch (IOException e){
                LOGGER.error("Failed to read configuration file ", e);
            }
        }
    }
}
