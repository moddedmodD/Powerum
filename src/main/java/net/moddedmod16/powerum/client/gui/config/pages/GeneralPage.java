package net.moddedmod16.powerum.client.gui.config.pages;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.moddedmod16.powerum.client.gui.config.ApplyLogic;
import net.moddedmod16.powerum.client.gui.config.ConfigScreen;
import net.moddedmod16.powerum.client.gui.config.widget.FlatWidget;

public class GeneralPage {

    public static boolean DEBUG_OVERLAY = true;

    public static boolean TEMP_DEBUG_OVERLAY = DEBUG_OVERLAY;

    public static void build(ConfigScreen screen) {

        ApplyLogic.register(new ApplyLogic.ConfigOption() {
            @Override
            public boolean hasChanged() {
                return TEMP_DEBUG_OVERLAY != DEBUG_OVERLAY;
            }

            @Override
            public void save() {
                DEBUG_OVERLAY = TEMP_DEBUG_OVERLAY;
            }

            @Override
            public void reset() {
                TEMP_DEBUG_OVERLAY = DEBUG_OVERLAY;
            }
        });

        screen.addFlatRenderableWidget(new FlatWidget(
                15,
                15 + 24,
                100,
                20,
                Component.literal("General"),
                () -> {
                    ConfigScreen.actualPage = PagesEnum.GENERAL;
                    screen.publicInit();
                },
                0x60000000,
                0xFFFFFFFF,
                Component.literal("General Settings")
        ));

        screen.addFlatRenderableWidget(new FlatWidget(
                15 + 90 + 20,
                40,
                screen.width - (15 + 100 + 20) - 20,
                20,
                Component.literal(""),
                () -> {
                    TEMP_DEBUG_OVERLAY = !TEMP_DEBUG_OVERLAY;
                    ApplyLogic.updateColors();

                    screen.publicInit();
                },
                0x40000000,
                0xFFFFFFFF,
                Component.literal("Toggle Powerum Debug Overlay, keep this enabled if you are unsure")
        ));
    }

    public static void render(GuiGraphicsExtractor graphics, ConfigScreen screen) {

        graphics.fill(
                15 + 100 - 2,
                15 + 24,
                15 + 100,
                15 + 24 + 20,
                0xFFFFFFFF
        );
        graphics.text(
                Minecraft.getInstance().font,
                "Debug Overlay" + " ".repeat(Math.max(1, ((screen.width - (15 + 100 + 20) - 20) - 24 - Minecraft.getInstance().font.width("Debug Overlay") - Minecraft.getInstance().font.width(TEMP_DEBUG_OVERLAY ? "ON" : "OFF")) / 4)) + (TEMP_DEBUG_OVERLAY ? "ON" : "OFF"),
                135 + 6,
                40 + (20 / 2) - (9 / 2),
                0xFFFFFFFF,
                false
        );
    }
}
