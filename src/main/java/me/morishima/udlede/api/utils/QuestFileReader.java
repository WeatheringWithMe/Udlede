package me.morishima.udlede.api.utils;

import com.feed_the_beast.ftbquests.FTBQuests;
import com.feed_the_beast.ftbquests.quest.Chapter;
import com.feed_the_beast.ftbquests.quest.Quest;
import com.feed_the_beast.ftbquests.quest.QuestFile;
import com.feed_the_beast.ftbquests.quest.loot.RewardTable;
import com.feed_the_beast.ftbquests.quest.reward.Reward;
import com.feed_the_beast.ftbquests.quest.task.Task;
import me.morishima.udlede.config.UdledeConfig;

import java.util.HashMap;
import java.util.StringJoiner;

public class QuestFileReader {
    private static final String N = System.lineSeparator();
    private final QuestFile file;
    private final MinecraftLangSerializer langSerializer = new MinecraftLangSerializer();

    public QuestFileReader(QuestFile file) {
        this.file = file;
    }

    public QuestFileReader() {
        this.file = FTBQuests.PROXY.getQuestFile(false);
    }

    private String getTranslationKey(String objectName, String ID, String textType) {
        final String base = UdledeConfig.ftbq.exportKeyID + ".%s.%s.%s";
        return String.format(base, objectName, ID, textType);
    }

    public void readRewardTable() {
        SequentialIDUtil ID = new SequentialIDUtil();
        HashMap<String, String> transKeys = new HashMap<>();

        //Title for reward tables.
        for (int i = 0; i < file.rewardTables.size(); i++) {
            RewardTable table = file.rewardTables.get(i);

            if (!table.title.startsWith("{")) {
                var key = getTranslationKey("reward",
                        !UdledeConfig.ftbq.useSequentialID ? table.title.toLowerCase().replace(" ", "_") : ID.getFormattedID(),
                        "title");

                transKeys.put(key, table.title);
                table.title = String.format("{%s}", key);
            }
        }

        langSerializer.addNamedMap("Reward Table", transKeys);
    }

    public void readChapters() {
        SequentialIDUtil ID = new SequentialIDUtil();
        HashMap<String, String> transKeys = new HashMap<>();

        for (int i1 = 0; i1 < file.chapters.size(); i1++) {
            Chapter chapter = file.chapters.get(i1);
            String title;

            if (!chapter.title.startsWith("{")) {
                title = chapter.title.toLowerCase().replace(" ", "_");
            } else {
                title = chapter.title
                        .replace("{", "")
                        .replace(UdledeConfig.ftbq.exportKeyID, "")
                        .replace("chapter", "")
                        .replace("title", "")
                        .replace(".", "");
            }

            //Title for chapters.
            if (!chapter.title.startsWith("{")) {
                var key = getTranslationKey("chapter",
                        !UdledeConfig.ftbq.useSequentialID ? title : ID.getFormattedID(),
                        "title");

                transKeys.put(key, chapter.title);
                chapter.title = String.format("{%s}", key);
            }

            if (!chapter.subtitle.isEmpty() && !chapter.subtitle.get(0).startsWith("{")) {
                var key = getTranslationKey("quest",
                        !UdledeConfig.ftbq.useSequentialID ? title : ID.getFormattedID(),
                        "subtitle");

                var joiner = new StringJoiner("\\n");

                for (String subtitle : chapter.subtitle) {
                    joiner.add(subtitle);
                }

                transKeys.put(key, joiner.toString());
                chapter.subtitle.clear();
                chapter.subtitle.add(String.format("{%s}", key));
            }

            langSerializer.addNamedMap("Chapters", transKeys);

            //For readQuests.
            readQuests(chapter);
        }
    }

    public void readQuests(Chapter chapter) {
        SequentialIDUtil ID = new SequentialIDUtil();
        HashMap<String, String> transKeys = new HashMap<>();

        for (int i1 = 0; i1 < chapter.quests.size(); i1++) {
            Quest quest = chapter.quests.get(i1);
            String title;

            if (!quest.title.startsWith("{")) {
                title = quest.title.toLowerCase().replace(" ", "_");
            } else {
                title = quest.title
                        .replace("{", "")
                        .replace(UdledeConfig.ftbq.exportKeyID, "")
                        .replace("quest", "")
                        .replace("title", "")
                        .replace(".", "");
            }

            //Title for quests.
            if (!quest.title.startsWith("{")) {
                var key = getTranslationKey("quest",
                        !UdledeConfig.ftbq.useSequentialID ? title : ID.getFormattedID(),
                        "title");

                transKeys.put(key, quest.title);
                quest.title = String.format("{%s}", key);
            }

            //Subtitle for quests.
            if (!quest.subtitle.startsWith("{")) {
                var key = getTranslationKey("quest",
                        !UdledeConfig.ftbq.useSequentialID ? title : ID.getFormattedID(),
                        "subtitle");

                transKeys.put(key, quest.subtitle);
                quest.subtitle = String.format("{%s}", key);
            }

            //Description for quests.
            if (!quest.description.isEmpty() && !quest.description.get(0).startsWith("{")) {
                var key = getTranslationKey("quest",
                        !UdledeConfig.ftbq.useSequentialID ? title : ID.getFormattedID(),
                        "desc");

                var joiner = new StringJoiner("\\n");

                for (String desc : quest.description) {
                    joiner.add(desc);
                }

                transKeys.put(key, joiner.toString());
                quest.description.clear();
                quest.description.add(String.format("{%s}", key));
            }

            for (int i2 = 0; i2 < quest.tasks.size(); i2++) {
                Task task = quest.tasks.get(i2);

                if (!task.title.startsWith("{")) {
                    var key = getTranslationKey("quest",
                            !UdledeConfig.ftbq.useSequentialID ? task.title : ID.getFormattedID(),
                            "task");

                    transKeys.put(key, task.title);
                    task.title = String.format("{%s}", key);
                }
            }

            for (int i2 = 0; i2 < quest.rewards.size(); i2++) {
                Reward reward = quest.rewards.get(i2);

                if (!reward.title.startsWith("{")) {
                    var key = getTranslationKey("quest",
                            !UdledeConfig.ftbq.useSequentialID ? reward.title : ID.getFormattedID(),
                            "reward");

                    transKeys.put(key, reward.title);
                    reward.title = String.format("{%s}", key);
                }
            }
        }

        langSerializer.addNamedMap("Quests", transKeys);
    }

    public MinecraftLangSerializer getLangSerializer() {
        readRewardTable();
        readChapters();
        return langSerializer;
    }
}
