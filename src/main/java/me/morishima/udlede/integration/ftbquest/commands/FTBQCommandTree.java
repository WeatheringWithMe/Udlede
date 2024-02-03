package me.morishima.udlede.integration.ftbquest.commands;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

public class FTBQCommandTree extends CommandTreeBase {

    public FTBQCommandTree() {
        super.addSubcommand(new FTBQuestExportLangKeyCommand());
    }

    @Override
    public String getName() {
        return "ftbq";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "udlede.usage.command_tree";
    }
}
