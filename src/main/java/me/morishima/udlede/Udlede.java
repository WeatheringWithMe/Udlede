package me.morishima.udlede;

import me.morishima.udlede.common.commands.UdledeCommand;
import me.morishima.udlede.integration.betterquesting.BetterQuestingIntegration;
import me.morishima.udlede.integration.ftbquest.FTBQuestIntegration;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringJoiner;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION
)
public class Udlede {
    public static final Logger logger = LogManager.getLogger("Udlede");

    public static final FTBQuestIntegration FTBQ = new FTBQuestIntegration();
    public static final BetterQuestingIntegration BQ = new BetterQuestingIntegration();

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new UdledeCommand());
    }

    @Mod.EventHandler
    public void onModConstruct(FMLConstructionEvent event) {
        File file = new File(new StringJoiner(File.separator)
                .add(Minecraft.getMinecraft().gameDir.getPath())
                .add("exported")
                .toString());

        try {
            Files.createDirectory(file.toPath());
        } catch (IOException e) {
            logger.error("Cant create dir!");
        }
    }

}
