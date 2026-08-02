package net.moddedmod16.powerum.client;

import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import net.minecraft.network.chat.Component;
import net.moddedmod16.powerum.client.gui.config.ConfigScreen;

public class ModMenuIntegration implements ModMenuApi{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ConfigScreen(Component.literal("PowerumClientMod"), parent);
    }
}
