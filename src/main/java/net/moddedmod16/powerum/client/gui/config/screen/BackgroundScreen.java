package net.moddedmod16.powerum.client.gui.config.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;

public class BackgroundScreen {

    public static void backgroundScreen(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta){
        Screen screen = Minecraft.getInstance().gui.screen();

        graphics.fill(0, 0, screen.width, screen.height, 0x40000000);

    }
}
