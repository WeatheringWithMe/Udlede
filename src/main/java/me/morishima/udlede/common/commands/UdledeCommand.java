package me.morishima.udlede.common.commands;

import me.morishima.udlede.integration.ftbquest.commands.FTBQCommandTree;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

public class UdledeCommand extends CommandTreeBase {

    public UdledeCommand() {
        super.addSubcommand(new FTBQCommandTree());
    }

    @Override
    public String getName() {
        return "udlede";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "udlede.usage.command_tree";
    }
}
