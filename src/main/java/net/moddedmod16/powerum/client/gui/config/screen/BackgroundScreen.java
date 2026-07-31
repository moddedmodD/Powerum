package net.moddedmod16.powerum.client.gui.config.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;

public class BackgroundScreen {

    public static void drawBackground(GuiGraphicsExtractor graphics){
        Screen screen = Minecraft.getInstance().gui.screen();

        if (screen != null) {
            graphics.fill(
                    0, 0,
                    screen.width, screen.height,
                    0x40000000
            );
        }
    }
}
