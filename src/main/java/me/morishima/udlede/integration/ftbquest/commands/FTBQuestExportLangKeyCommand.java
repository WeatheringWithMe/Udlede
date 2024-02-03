package me.morishima.udlede.integration.ftbquest.commands;

import me.morishima.udlede.api.utils.QuestFileReader;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class FTBQuestExportLangKeyCommand extends CommandBase {

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
        new QuestFileReader().getLangSerializer().writeToFile(
                "en_us.lang"
                );
    }
}
