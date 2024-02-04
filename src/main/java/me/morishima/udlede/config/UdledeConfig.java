package me.morishima.udlede.config;

import me.morishima.udlede.Tags;
import net.minecraftforge.common.config.Config;

@Config(
        modid = Tags.MOD_ID
)
public class UdledeConfig {

    @Config.Name("Compatibility Options of FTB Quest")
    @Config.RequiresMcRestart
    public static FTBQuest ftbq = new FTBQuest();

    @Config.Name("Compatibility Options of Better Questing")
    @Config.RequiresMcRestart
    public static BQ bq = new BQ();


    public static class FTBQuest {

        @Config.Comment({
                "Use sequential id for exported translation key id when it is true.",
                "Use raw title text for exported translation key id when it is false."
        })
        public boolean useSequentialID = true;

        @Config.Comment({
                "Translation key prefix when player exporting FTBQ translation map.",
                "Format: <exportKeyID>.<ObjectName>.<SequentialKeyID>.<TextType>",
                "Example: modpack.quest.000001.subtitle",
                })
        public String exportKeyID = "modpack";

    }

    public static class BQ {

        @Config.Comment({
                "Use sequential id for exported translation key id when it is true.",
                "Use raw title text for exported translation key id when it is false."
        })
        public boolean useSequentialID = true;

        @Config.Comment({
                "Translation key prefix when player exporting BQ translation map.",
                "Format: <exportKeyID>.<ObjectName>.<SequentialKeyID>.<TextType>",
                "Example: modpack.quest.000001.subtitle",
        })
        public String exportKeyID = "modpack";

    }

}
