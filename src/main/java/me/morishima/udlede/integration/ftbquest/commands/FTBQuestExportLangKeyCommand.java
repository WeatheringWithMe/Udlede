package me.morishima.udlede.integration.ftbquest.commands;

import me.morishima.udlede.config.UdledeConfig;
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
    }

    private String getSequentialTranslationKey(String objectName, String textType) {
        final String base = UdledeConfig.ftbq.exportKeyID + ".%s.%s.%s";
        return String.format(base, objectName, getFormattedID(), textType);
    }
}
