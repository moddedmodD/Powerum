package net.moddedmod16.powerum.client.gui.config;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.moddedmod16.powerum.client.PowerumClientMod;
import net.moddedmod16.powerum.client.gui.config.pages.GeneralPage;
import net.moddedmod16.powerum.client.gui.config.widget.ScreenBlit;
import net.moddedmod16.powerum.client.gui.config.pages.PagesEnum;
import net.moddedmod16.powerum.client.gui.config.widget.FlatWidget;
import org.jspecify.annotations.NonNull;

public class ConfigScreen extends Screen {

    public static int textColor = 0x60FFFFFF;
    public static int color = 0x60000000;

    private final Identifier CONFIG_ICON = Identifier.fromNamespaceAndPath("powerum", "config_icon.png");

    public Screen parent;

    public ConfigScreen(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    public static PagesEnum actualPage = PagesEnum.GENERAL;

    @Override
    public void onClose() {

        if (this.parent != null) {
            this.minecraft.gui.setScreen(this.parent);
        } else {
            super.onClose();
        }
    }

    public void addFlatRenderableWidget(FlatWidget widget) {
        this.addRenderableWidget(widget);
    }

    @Override
    protected void init() {

        ApplyLogic.clearRegistry();

        this.clearWidgets();

        Runnable closeAction = this::onClose;

        this.addRenderableWidget(new FlatWidget(
                this.width - 60 - 15,
                this.height - 20 - 15,
                60,
                20,
                Component.literal("Done"),
                () -> {
                    ApplyLogic.resetAll();
                    closeAction.run();
                },
                0xFF000000,
                0xFFFFFFFF,
                Component.literal("Close GUI")
        ));
        this.addRenderableWidget(new FlatWidget(
                this.width - 60 - 15 - 60 - 4,
                this.height - 20 - 15,
                60,
                20,
                Component.literal("Apply"),
                () -> {
                    ApplyLogic.applyAll(this);
                    ApplyLogic.updateColors();
                },
                color,
                textColor,
                Component.literal("Apply all")
        ));

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

        if (actualPage == PagesEnum.GENERAL){
            GeneralPage.build(this);
        }
    }

    public void publicInit(){
        init();
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {

        BackgroundScreen.drawBackground(graphics);

        graphics.fill(
                15,
                15,
                15 + 100,
                15 + 24,
                0xFF000000
        );

        super.extractRenderState(graphics, mouseX, mouseY, delta);

        if (actualPage == PagesEnum.GENERAL){
            GeneralPage.render(graphics, this);
        }

        graphics.text(
                this.minecraft.font,
                PowerumClientMod.SPLIT_MOD_VERSION,
                15 + 16 + 4, 19 + 8 + 2,
                0xFFFFFFFF,
                false
        );
    }
}