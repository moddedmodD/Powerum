package net.moddedmod16.powerum.client.gui.config.screen.image;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.RenderPipelines;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public class WidgetScreenBlit extends AbstractWidget {

    private Identifier identifier;
    private int u;
    private int v;
    private int regionWidth;
    private int regionHeight;
    private int textureWidth;
    private int textureHeight;
    private final Supplier<Boolean> hoverCondition;

    public WidgetScreenBlit(int x, int y, int width, int height, int u, int v, int regionWidth, int regionHeight, int textureWidth, int textureHeight, Identifier identifier, Component message, Supplier<Boolean> hoverCondition){
        super(x, y, width, height, message);
        this.identifier = identifier;
        this.u = u;
        this.v = v;
        this.regionWidth = regionWidth;
        this.regionHeight = regionHeight;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.hoverCondition = hoverCondition;
    }

    public void setIdentifier(Identifier newIdentifier){
        this.identifier = newIdentifier;
    }

    public void setUV(int u, int v){
        this.u = u;
        this.v = v;
    }

    public void setRegion(int regionWidth, int regionHeight){
        this.regionWidth = regionWidth;
        this.regionHeight = regionHeight;
    }

    public void setTexture(int textureWidth, int textureHeight){
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    @Override
    protected void extractWidgetRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        boolean show = this.hoverCondition != null && this.hoverCondition.get();

        if (this.identifier != null && show) {
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    this.identifier,
                    this.getX(), this.getY(),
                    this.u, this.v,
                    this.width, this.height,
                    this.regionWidth, this.regionHeight,
                    this.textureWidth, this.textureHeight
            );
            graphics.text(
                    Minecraft.getInstance().font,
                    this.getMessage(),
                    this.getX() + (this.width / 2) - (Minecraft.getInstance().font.width(this.getMessage()) / 2), this.getY() + this.height + 6,
                    0xFFFFFFFF,
                    false
            );
        }
    }

    @Override
    protected void updateWidgetNarration(@NonNull NarrationElementOutput output) {
    }
}
