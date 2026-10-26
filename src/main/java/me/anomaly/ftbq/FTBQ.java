package me.anomaly.ftbq;

import betterquesting.api.storage.BQ_Settings;
import betterquesting.api2.client.gui.themes.gui_args.GArgsNone;
import betterquesting.api2.client.gui.themes.presets.PresetGUIs;
import betterquesting.client.BookmarkManager;
import betterquesting.client.gui2.GuiQuestLines;
import betterquesting.client.themes.ThemeRegistry;
import com.feed_the_beast.ftblib.events.client.CustomClickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        modid = FTBQTags.MOD_ID,
        name = FTBQTags.MOD_NAME,
        version = FTBQTags.MOD_VERSION,
        useMetadata = true,
        dependencies = "required:ftblib;required:betterquesting;"
)
public class FTBQ {

    @Mod.EventBusSubscriber(
            modid = FTBQTags.MOD_ID,
            value = Side.CLIENT
    )
    public static class EventHandler {
        @SubscribeEvent
        public static void onCustomClick(CustomClickEvent event) {
            if (event.getID().getNamespace().equals("ftbq")) {
                if (event.getID().getPath().equals("open_bq_gui")) {
                    GuiScreen bookmark = BookmarkManager.INSTANCE.getBookmark();
                    if (BQ_Settings.useBookmark && bookmark != null) {
                        Minecraft.getMinecraft().displayGuiScreen(bookmark);
                    } else {
                        GuiScreen guiToDisplay = ThemeRegistry.INSTANCE.getGui(PresetGUIs.HOME, GArgsNone.NONE);
                        if (BQ_Settings.useBookmark && BQ_Settings.skipHome) {
                            guiToDisplay = new GuiQuestLines(guiToDisplay);
                        }
                        Minecraft.getMinecraft().displayGuiScreen(guiToDisplay);
                    }
                }
                event.setCanceled(true);
            }
        }
    }

}
