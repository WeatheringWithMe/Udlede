package me.morishima.udlede.integration.ftbquest.commands;

import me.morishima.udlede.Udlede;
import me.morishima.udlede.api.utils.FTBQuestReader;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringJoiner;

public class FTBQuestExportCommand extends CommandBase {

    @Override
    public String getName() {
        return "export";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "udlede.usage.export";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        File file = new File(new StringJoiner(File.separator)
                .add(Minecraft.getMinecraft().gameDir.getPath())
                .add("exported")
                .add("ftbquest")
                .toString());
        try {
            if (!file.getParentFile().exists()) Files.createDirectory(file.getParentFile().toPath());
            Files.createDirectory(file.toPath());
        } catch (IOException e) {
            Udlede.logger.error("FTBQ Exporter: Cant create directory");
        }

        new FTBQuestReader().getLangSerializer().writeToFile(
                new StringJoiner(File.separator)
                        .add(file.getPath())
                        .add("en_us.lang")
                        .toString()
                );
    }
}
