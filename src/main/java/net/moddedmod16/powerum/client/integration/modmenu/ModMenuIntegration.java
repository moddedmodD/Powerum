package net.moddedmod16.powerum.client.integration.modmenu;

import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import net.minecraft.network.chat.Component;
import net.moddedmod16.powerum.client.gui.config.screen.PowerumConfigScreen;

public class ModMenuIntegration implements ModMenuApi{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new PowerumConfigScreen(Component.literal("PowerumClientMod"), parent);
    }
}
