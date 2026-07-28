package net.moddedmod16.powerum.client.gui.integration.debug;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import static net.moddedmod16.powerum.client.Powerum.MOD_NAME;
import static net.moddedmod16.powerum.client.Powerum.MOD_VERSION;

public class DebugOverlay {

    public static void render(GuiGraphicsExtractor graphics, Minecraft client){

        var window = client.getWindow();
        var screenWidth = window.getGuiScaledWidth();

        final var text = "§a " + MOD_NAME + " optimization (v" + MOD_VERSION + ")§r";

        int boxWidth = client.font.width(text) + 8;
        int boxHeight = 11;

        int x1 = screenWidth - boxWidth - 2;
        int y1 = 82;
        int x2 = screenWidth - 1;
        int y2 = y1 + boxHeight;

        graphics.fill(x1, y1, x2, y2, -1873784752);

        int textX = x1 + 4;
        int textY = y1 + 1;

        graphics.text(client.font, text , textX, textY, 0xFFFFFFFF, false);
    }
}
