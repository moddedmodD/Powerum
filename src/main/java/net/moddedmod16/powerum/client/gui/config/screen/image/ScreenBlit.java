package net.moddedmod16.powerum.client.gui.config.screen.image;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.RenderPipelines;
import org.jspecify.annotations.NonNull;

public class ScreenBlit extends AbstractWidget {

    private Identifier identifier;
    private int u;
    private int v;
    private int regionWidth;
    private int regionHeight;
    private int textureWidth;
    private int textureHeight;
    private boolean showText;
    private int textX;
    private int textY;

    public ScreenBlit(int x, int y, int width, int height, int u, int v, int regionWidth, int regionHeight, int textureWidth, int textureHeight, boolean showText, int textX, int textY, Identifier identifier, Component message){
        super(x, y, width, height, message);
        this.identifier = identifier;
        this.u = u;
        this.v = v;
        this.regionWidth = regionWidth;
        this.regionHeight = regionHeight;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.showText = showText;
        this.textX = textX;
        this.textY = textY;
    }

    public void setShowText(boolean showText){
        this.showText = showText;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
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

    public void setText(int textX, int textY){
        this.textX = textX;
        this.textY = textY;
    }

    @Override
    protected void extractWidgetRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {

        if (identifier != null) {
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    this.identifier,
                    this.getX(), this.getY(),
                    this.u, this.v,
                    this.width, this.height,
                    this.regionWidth, this.regionHeight,
                    this.textureWidth, this.textureHeight
            );
        }

        if (this.showText){
            graphics.text(
                    Minecraft.getInstance().font,
                    this.getMessage(),
                    this.textX, this.textY,
                    0xFFFFFFFF,
                    false
            );
        }
    }

    @Override
    protected void updateWidgetNarration(@NonNull NarrationElementOutput output) {
    }
}
