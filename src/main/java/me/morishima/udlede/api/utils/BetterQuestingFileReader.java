package me.morishima.udlede.api.utils;

import betterquesting.api.utils.JsonHelper;
import betterquesting.api.utils.NBTConverter;
import com.google.gson.JsonObject;
import me.morishima.udlede.Udlede;
import me.morishima.udlede.config.UdledeConfig;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.io.File;
import java.util.HashMap;

public class BetterQuestingFileReader {
    private final NBTTagCompound NBT;
    private final File file;
    private final MinecraftLangSerializer langSerializer = new MinecraftLangSerializer();

    public BetterQuestingFileReader(File file) {
        NBT = NBTConverter.JSONtoNBT_Object(JsonHelper.ReadFromFile(file), new NBTTagCompound(), true);
        this.file = file;
    }

    private String getTranslationKey(String objectName, String ID, String textType) {
        final String base = UdledeConfig.ftbq.exportKeyID + ".%s.%s.%s";
        return String.format(base, objectName, ID, textType);
    }

    private void readQuestDatabase() {
        SequentialIDUtil ID = new SequentialIDUtil();

        if (NBT.hasKey("questDatabase",9)) {
            NBTTagList Database = NBT.getTagList("questDatabase", 10);
            for (int i = 0; i < Database.tagCount(); i++) {
                NBTTagCompound data = (NBTTagCompound) Database.get(i);
                if (data.hasKey("properties", 10) && data.getCompoundTag("properties").hasKey("betterquesting", 10)) {
                    NBTTagCompound quest = data.getCompoundTag("properties").getCompoundTag("betterquesting");
                    HashMap<String, String> transKeys = new HashMap<>();

                    String name = "";

                    //Name
                    if (quest.hasKey("name", 8) && !quest.getString("name").startsWith(UdledeConfig.bq.exportKeyID + ".")) {
                        name = quest.getString("name");
                        var key = getTranslationKey(
                                "quest",
                                UdledeConfig.bq.useSequentialID ? String.format("%07d", i) : name.toLowerCase().replace(" ", "_"),
                                "name");

                        quest.removeTag("name");
                        quest.setString("name", key);

                        transKeys.put(key, name);
                    }

                    //Desc
                    if (quest.hasKey("desc", 8) && !quest.getString("desc").startsWith(UdledeConfig.bq.exportKeyID + ".")) {
                        String desc = quest.getString("desc");

                        var key = getTranslationKey(
                                "quest",
                                UdledeConfig.bq.useSequentialID ? String.format("%07d", i) : name.toLowerCase().replace(" ", "_"),
                                "desc");

                        quest.removeTag("desc");
                        quest.setString("desc", key);

                        transKeys.put(key, desc);
                    }

                    langSerializer.addNamedMap(String.format("Quest %s | ", String.format("%07d", i)) + name, transKeys);
                }
            }
        }
    }

    private void readQuestLine() {
        SequentialIDUtil ID = new SequentialIDUtil();

        if (NBT.hasKey("questLines",9)) {
            NBTTagList Lines = NBT.getTagList("questLines", 10);
            for (int i = 0; i < Lines.tagCount(); i++) {
                NBTTagCompound line = (NBTTagCompound) Lines.get(i);
                if (line.hasKey("properties", 10) && line.getCompoundTag("properties").hasKey("betterquesting", 10)) {
                    NBTTagCompound chapter = line.getCompoundTag("properties").getCompoundTag("betterquesting");
                    HashMap<String, String> transKeys = new HashMap<>();

                    String name = "";

                    //Name
                    if (chapter.hasKey("name", 8) && !chapter.getString("name").startsWith(UdledeConfig.bq.exportKeyID + ".")) {
                        name = chapter.getString("name");
                        var key = getTranslationKey(
                                "chapter",
                                UdledeConfig.bq.useSequentialID ? String.format("%07d", i) : name.toLowerCase().replace(" ", "_"),
                                "name");

                        chapter.removeTag("name");
                        chapter.setString("name", key);

                        transKeys.put(key, name);
                    }

                    //Desc
                    if (chapter.hasKey("desc", 8) && !chapter.getString("desc").startsWith(UdledeConfig.bq.exportKeyID + ".")) {
                        String desc = chapter.getString("desc");

                        var key = getTranslationKey(
                                "chapter",
                                UdledeConfig.bq.useSequentialID ? String.format("%07d", i) : name.toLowerCase().replace(" ", "_"),
                                "desc");

                        chapter.removeTag("desc");
                        chapter.setString("desc", key);

                        transKeys.put(key, desc);
                    }

                    langSerializer.addNamedMap(String.format("Quest %s | ", String.format("%07d", i)) + name, transKeys);
                }
            }
        }
    }

    public MinecraftLangSerializer getLangSerializer() {
        // Thread of read configs.
        Thread t1 = new Thread(this::readQuestDatabase);
        Thread t2 = new Thread(this::readQuestLine);
        t1.start();
        t2.start();
        Udlede.logger.info("QuestFileReader: Reading by multi-thread...");

        langSerializer.addHeadComment("BetterQuesting");

        return langSerializer;
    }

    public void saveToConfig() {
        JsonHelper.WriteToFile(file, NBTConverter.NBTtoJSON_Compound(NBT, new JsonObject(), true));
    }
}
