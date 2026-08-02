package net.moddedmod16.powerum.client.gui.config;

import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class ApplyLogic {

    public interface ConfigOption {
        boolean hasChanged();
        void save();
        void reset();
    }

    private static final List<ConfigOption> REGISTERED_OPTIONS = new ArrayList<>();

    public static void register(ConfigOption option){
        REGISTERED_OPTIONS.add(option);
    }
    public static void clearRegistry(){
        REGISTERED_OPTIONS.clear();
    }
    public static void updateColors(){
        boolean changes = false;
        for (ConfigOption option : REGISTERED_OPTIONS){
            if (option.hasChanged()){
                changes = true;
                break;
            }
        }
        if (changes){
            ConfigScreen.color = 0xFF000000;
            ConfigScreen.textColor = 0xFFFFFFFF;
        } else {
            ConfigScreen.color = 0x60000000;
            ConfigScreen.textColor = 0x60FFFFFF;
        }
    }
    public static void applyAll(ConfigScreen screen){
        for (ConfigOption option : REGISTERED_OPTIONS){
            if (option.hasChanged()){
                option.save();
            }
        }
        updateColors();

        Minecraft.getInstance().gui.setScreen(screen);

        if (Minecraft.getInstance().getDebugOverlay() != null){
            Minecraft.getInstance().getDebugOverlay().reset();
        }
    }
    public static void resetAll(){
        for (ConfigOption option : REGISTERED_OPTIONS){
            option.reset();
        }
        updateColors();
    }
}