package co.uk.mommyheather.betonquestgui.gui;

import co.uk.mommyheather.betonquestgui.BetonQuestGui;
import co.uk.mommyheather.betonquestgui.config.BQGConfig;
import co.uk.mommyheather.betonquestgui.gui.compass.CompassOverlay;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = BetonQuestGui.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class GuiEventListener
{
    @SubscribeEvent
    public static void onRenderGameOverlayPre(RenderGuiOverlayEvent.Pre event) {
        if(!BQGConfig.CONFIG.showCompass.get()) return;

        if (VanillaGuiOverlay.PLAYER_LIST.type() == event.getOverlay()) {
            event.getGuiGraphics().pose().translate(0, (CompassOverlay.POSY + 28), 0);
        }
    }

    @SubscribeEvent
    public static void onRenderGameOverlayPost(RenderGuiOverlayEvent.Post event) {
        if(!BQGConfig.CONFIG.showCompass.get()) return;

        if (VanillaGuiOverlay.PLAYER_LIST.type() == event.getOverlay()) {
            event.getGuiGraphics().pose().translate(0, -(CompassOverlay.POSY + 28), 0);
        }
    }
}
