package net.moddedmod16.powerum.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class DebugOverlay {

    public static void render(GuiGraphicsExtractor graphics, Minecraft client){

        var window = client.getWindow();
        int screenWidth = window.getGuiScaledWidth();
        int screenHeight = window.getGuiScaledHeight();

        int boxWidth = 140;
        int boxHeight = 24;

        int x1 = screenWidth - boxWidth - 10;
        int y1 = screenHeight - boxHeight - 10;
        int x2 = screenWidth - 10;
        int y2 = screenHeight - 10;

        graphics.fill(x1, y1, x2, y2, 0x90000000);

        graphics.fill(x1, y1, x2, y1 + 2, 0xFFFF5555);

        int textX = x1 + 8;
        int textY = y1 + 8;

        graphics.text(client.font, "§c[MY MOD]§r Operational", textX, textY, 0xFFFFFFFF);
    }
}
