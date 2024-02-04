package me.morishima.udlede.integration.betterquesting.commands;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

public class BQCommandTree extends CommandTreeBase {

    public BQCommandTree() {
        super.addSubcommand(new BQQuestExportCommand());
    }

    @Override
    public String getName() {
        return "bq";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "udlede.usage.command_tree";
    }
}
