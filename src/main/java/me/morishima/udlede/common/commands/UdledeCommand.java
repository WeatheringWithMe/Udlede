package me.morishima.udlede.common.commands;

import me.morishima.udlede.Udlede;
import me.morishima.udlede.integration.betterquesting.commands.BQCommandTree;
import me.morishima.udlede.integration.ftbquest.commands.FTBQCommandTree;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

public class UdledeCommand extends CommandTreeBase {

    public UdledeCommand() {
        if (Udlede.FTBQ.isLoaded()) super.addSubcommand(new FTBQCommandTree());
        if (Udlede.BQ.isLoaded()) super.addSubcommand(new BQCommandTree());
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
