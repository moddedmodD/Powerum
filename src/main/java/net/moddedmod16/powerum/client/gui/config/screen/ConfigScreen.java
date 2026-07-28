package net.moddedmod16.powerum.client.gui.config.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.moddedmod16.powerum.client.gui.config.widget.FlatWidget;

public class ConfigScreen extends Screen {

    public static int textColor = 0x60FFFFFF;
    public static int color = 0x60000000;

    public Screen parent;

    public ConfigScreen(Component title, Screen parent){
        super(title);
        this.parent = parent;
    }

    @Override
    public void onClose(){

        if (this.parent != null){
            this.minecraft.gui.setScreen(this.parent);
        } else {
           super.onClose();
        }
    }

    @Override
    protected void init() {

        Runnable closeAction = () -> this.onClose();

        this.addRenderableWidget(new FlatWidget(
                this.width - 60 - 15,
                this.height - 20 - 15,
                60,
                20,
                Component.literal("Done"),
                closeAction,
                0xFF000000,
                0xFFFFFFFF
        ));
        this.addRenderableWidget(new FlatWidget(
                this.width - 60 - 15 - 60 - 4,
                this.height - 20 - 15 ,
                60,
                20,
                Component.literal("Apply"),
                closeAction,
                color,
                textColor
        ));
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta){

        BackgroundScreen.backgroundScreen(graphics, mouseX, mouseY, delta);

        super.extractRenderState(graphics, mouseX, mouseY, delta);
    }
}
