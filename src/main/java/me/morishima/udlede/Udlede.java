package me.morishima.udlede;

import me.morishima.udlede.common.commands.UdledeCommand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION
)
public class Udlede {
    public static final Logger logger = LogManager.getLogger("Udlede");

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new UdledeCommand());
    }

}
