package net.moddedmod16.powerum.client.gui.config.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.moddedmod16.powerum.client.PowerumClientMod;
import net.moddedmod16.powerum.client.gui.config.widget.FlatWidget;
import net.moddedmod16.powerum.client.gui.config.screen.image.ScreenBlit;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;

public class PowerumConfigScreen extends Screen {

    public static int textColor = 0x60FFFFFF;
    public static int color = 0x60000000;

    private final Identifier CONFIG_ICON = Identifier.fromNamespaceAndPath("powerum", "config_icon.png");

    public Screen parent;

    public PowerumConfigScreen(Component title, Screen parent){
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

        Runnable closeAction = this::onClose;

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
                this::doNull,
                color,
                textColor
        ));
/*        this.addRenderableWidget(new FlatWidget(
                0,
                15,
                148,
                20,
                Component.literal("Debug overlay  " + (DEBUG_OVERLAY ? "ON" : "OFF")),
                () -> {
                    DEBUG_OVERLAY = !DEBUG_OVERLAY;
                    Apply.onModify();
                },
                0x90000000,
                0xFFFFFFFF        )); */

        this.addRenderableWidget(new ScreenBlit(
                15, 17,
                16, 16,
                0, 0,
                16, 16,
                16, 16,
                true,
                15 + 16 + 4, 19,
                CONFIG_ICON,
                Component.literal("Powerum")
        ));
        this.addRenderableWidget(new FlatWidget(
                15,
                15 + 24,
                100,
                20,
                Component.literal(""),
                this::doNull,
                0x60000000,
                0xFF87CEEB
        ));
    }

    public void doNull(){
        Logger doNullPrint = PowerumClientMod.LOGGER;

        doNullPrint.info("nothing changed...");
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta){

        BackgroundScreen.drawBackground(graphics);

        graphics.fill(15, 15, 15 + 100, 15 + 24, 0xFF000000);
        graphics.text(this.minecraft.font, PowerumClientMod.MOD_VERSION.split("-")[0], 15 + 16 + 4, 19 + 8 + 2, 0xFFFFFFFF, false);
        super.extractRenderState(graphics, mouseX, mouseY, delta);
    }
}
