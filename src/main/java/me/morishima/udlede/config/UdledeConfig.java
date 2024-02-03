package me.morishima.udlede.config;

import me.morishima.udlede.Tags;
import net.minecraftforge.common.config.Config;

@Config(
        modid = Tags.MOD_ID
)
public class UdledeConfig {

    @Config.Comment("Config options for Mod Compatibility")
    @Config.Name("Compatibility Options")
    @Config.RequiresMcRestart
    public static FTBQuest ftbq = new FTBQuest();


    public static class FTBQuest {

        @Config.Comment({
                "Translation key prefix when player exporting FTBQ translation map.",
                "Format: <exportKeyID>.<ObjectName>.<SequentialKeyID>.<TextType>",
                "Example: modpack.quest.000001.subtitle",
                })
        public String exportKeyID = "modpack";

    }

}
