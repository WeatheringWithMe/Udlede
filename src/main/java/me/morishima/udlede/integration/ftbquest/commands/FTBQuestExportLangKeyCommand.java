package me.morishima.udlede.integration.ftbquest.commands;

import com.feed_the_beast.ftbquests.FTBQuests;
import com.feed_the_beast.ftbquests.quest.QuestFile;
import me.morishima.udlede.config.UdledeConfig;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import java.util.TreeMap;

public class FTBQuestExportLangKeyCommand extends CommandBase {

    public static long idQuest = 0;

    private static String getID() {
        idQuest++;
        int length = String.valueOf(idQuest).length();
        if (length < 6) {
            StringBuilder builder = new StringBuilder();
            for (int i=0; i<6-length; i++) {
                builder.append("0");
            }
        }
        return "";
    }

    @Override
    public String getName() {
        return "export";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "udlede.usage.ftb.export";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        TreeMap<String, String> transMap = new TreeMap<>();
        QuestFile file = FTBQuests.PROXY.getQuestFile(false);
    }

    private String getSequentialTranslationKey(String objectName, String textType) {
        final String base = UdledeConfig.ftbq.exportKeyID + ".%s.%s.%s";
        return "";
    }
}
