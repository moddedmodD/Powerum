package net.moddedmod16.powerum.client.gui.config.mouse;

import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

public class MouseOver {

    public static Supplier<Boolean> isMouseHover(int x, int y, int width, int height, Minecraft client){
        return() ->{
            if (client.gui.screen() == null) return false;

            double mouseX = client.mouseHandler.xpos() * (double)client.getWindow().getGuiScaledWidth() / (double)client.getWindow().getWidth();
            double mouseY = client.mouseHandler.ypos() * (double)client.getWindow().getGuiScaledHeight() / (double)client.getWindow().getHeight();

            boolean inX = mouseX >= x && mouseX <= (x + width);
            boolean inY = mouseY >= y && mouseY <= (y + height);

            return inX && inY;
        };
    }
}
