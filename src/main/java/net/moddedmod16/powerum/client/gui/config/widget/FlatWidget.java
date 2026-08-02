package net.moddedmod16.powerum.client.gui.config.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.Tooltip;
import org.jspecify.annotations.NonNull;

public class FlatWidget extends AbstractWidget {

    private Runnable clickAction;
    private int textColor;
    private int color;

    public FlatWidget(int x, int y, int width, int height, Component message, Runnable clickAction, int color, int textColor, Component toolTipText) {
        super(x, y, width, height, message);
        this.clickAction = clickAction;
        this.color = color;
        this.textColor = textColor;

        if (toolTipText != null){
            this.setTooltip(Tooltip.create(toolTipText));
        }
    }
    @Override
    protected void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        graphics.fill(
                getX(),
                getY(),
                getX() + this.width,
                getY() + this.height,
                this.color
        );

        int x = this.getX() + (this.width / 2) - (Minecraft.getInstance().font.width(this.getMessage()) / 2);
        int y = this.getY() + (this.height / 2) - (Minecraft.getInstance().font.lineHeight / 2) + 1;

        graphics.text(
                Minecraft.getInstance().font,
                this.getMessage(),
                x,
                y,
                this.textColor,
                false
        );
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick){
        if (event.button() == 0 && this.isMouseOver(event.x(), event.y())){
            if (this.clickAction != null){
                this.playDownSound(Minecraft.getInstance().getSoundManager());

                this.clickAction.run();
                return true;
            }
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    protected void updateWidgetNarration(@NonNull NarrationElementOutput output) {
    }
}
