package co.uk.mommyheather.betonquestgui.gui.widgets;

import co.uk.mommyheather.betonquestgui.BetonQuestGui;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class WidgetResizableDiv implements Renderable
{
    private static final ResourceLocation BORDER_TEXTURE = new ResourceLocation(BetonQuestGui.MOD_ID, "textures/gui/border.png");
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(BetonQuestGui.MOD_ID, "textures/gui/background.png");
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public WidgetResizableDiv(int x, int y, int width, int height, float red, float green, float blue, float alpha)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public WidgetResizableDiv(int x, int y, int width, int height)
    {
        this(x, y, width, height, 0.5F, 0.5F, 0.5F, 1F);
    }

    @Override
    public void render(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY, float f)
    {
        //RenderSystem.setShaderColor(this.red, this.green, this.blue, this.alpha);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        this.renderBackground(this.x + 6, this.y + 6, 0, 0, this.width - 12, this.height - 12);
        RenderSystem.setShaderTexture(0, BORDER_TEXTURE);
        this.renderBorder(this.x, this.y, 0, 0, 6, 6);
        boolean flag = true;
        int counter = 0;
        while (flag) {
            int length = this.width - 108 * counter;
            if (length > 108) {
                length = 108;
            } else {
                flag = false;
                length -= 12;
            }
            this.renderBorder(this.x + 6 + 108 * counter, this.y, 6, 0, length, 6);
            counter++;
        }
        flag = true;
        counter = 0;
        while (flag) {
            int length = this.height - 71 * counter;
            if (length > 71) {
                length = 71;
            } else {
                flag = false;
                length -= 12;
            }
            this.renderBorder(this.x, this.y + 6 + 71 * counter, 0, 6, 6, length);
            counter++;
        }
        flag = true;
        counter = 0;
        while (flag) {
            int length = this.height - 71 * counter;
            if (length > 71) {
                length = 71;
            } else {
                flag = false;
                length -= 12;
            }
            this.renderBorder(this.x + this.width - 6, this.y + 6 + 71 * counter, 118, 6, 6, length);
            counter++;
        }
        this.renderBorder(this.x + this.width - 6, this.y, 118, 0, 6, 6);
        this.renderBorder(this.x, this.y + this.height - 6, 0, 77, 6, 6);
        flag = true;
        counter = 0;
        while (flag) {
            int length = this.width - 108 * counter;
            if (length > 108) {
                length = 108;
            } else {
                flag = false;
                length -= 12;
            }
            this.renderBorder(this.x + 6 + 108 * counter, this.y + this.height - 6, 6, 77, length, 6);
            counter++;
        }
        this.renderBorder(this.x + this.width - 6, this.y + this.height - 6, 118, 77, 6, 6);
    }

    public void renderBorder(int x, int y, int offsetX, int offsetY, int width, int height)
    {
        this.renderTexture(x, y, offsetX, offsetY, width, height, 0.008F, 0.012F);
    }

    public void renderBackground(int x, int y, int offsetX, int offsetY, int width, int height)
    {
        this.renderTexture(x, y, offsetX, offsetY, width, height, 0.009F, 0.014F);
    }

    public void renderTexture(int x, int y, int offsetX, int offsetY, int width, int height, float f1, float f2)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(x, y + height, 0F).uv(offsetX * f1, (offsetY + height) * f2).endVertex();
        bufferBuilder.vertex(x + width, y + height, 0F).uv((offsetX + width) * f1, (offsetY + height) * f2).endVertex();
        bufferBuilder.vertex(x + width, y, 0F).uv((offsetX + width) * f1, offsetY * f2).endVertex();
        bufferBuilder.vertex(x, y, 0F).uv(offsetX * f1, offsetY * f2).endVertex();

        BufferUploader.drawWithShader(bufferBuilder.end());
    }
}
