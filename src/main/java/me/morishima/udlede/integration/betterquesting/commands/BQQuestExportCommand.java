package me.morishima.udlede.integration.betterquesting.commands;

import betterquesting.api.storage.BQ_Settings;
import me.morishima.udlede.Udlede;
import me.morishima.udlede.api.utils.BetterQuestingFileReader;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringJoiner;

public class BQQuestExportCommand extends CommandBase {
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
        File fileDefaultDatabase = new File(BQ_Settings.defaultDir, "DefaultQuests.json");

        if (fileDefaultDatabase.exists()) {
            BetterQuestingFileReader reader = new BetterQuestingFileReader(
                    fileDefaultDatabase
            );
            File file = new File(new StringJoiner(File.separator)
                    .add(Minecraft.getMinecraft().gameDir.getPath())
                    .add("exported")
                    .add("bq")
                    .toString());
            try {
                if (!file.getParentFile().exists()) Files.createDirectory(file.getParentFile().toPath());
                Files.createDirectory(file.toPath());
            } catch (IOException e) {Udlede.logger.error("BQ Exporter: Caught error " + e);}

            reader.getLangSerializer().writeToFile(
                    new StringJoiner(File.separator)
                    .add(file.getPath())
                    .add("en_us.lang")
                    .toString());

            reader.saveToConfig();
        } else {
            Udlede.logger.error("BQ Exporter: Cant find BQ Quest file!");
        }
    }
}
