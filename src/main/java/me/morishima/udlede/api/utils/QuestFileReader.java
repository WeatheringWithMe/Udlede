package me.morishima.udlede.api.utils;

import com.feed_the_beast.ftbquests.FTBQuests;
import com.feed_the_beast.ftbquests.quest.QuestFile;
import com.feed_the_beast.ftbquests.quest.loot.RewardTable;

import java.util.TreeMap;

public class QuestFileReader {
    private final QuestFile file;

    public QuestFileReader(QuestFile file) {
        this.file = file;
    }

    public QuestFileReader() {
        this.file = FTBQuests.PROXY.getQuestFile(false);
    }

    public void readRewardTable() {
        TreeMap<String, String> transKeys = new TreeMap<>();
        for (int i = 0; i < file.rewardTables.size(); i++) {
            RewardTable table = file.rewardTables.get(i);

            transKeys.put("loot_table." + (i + 1), table.title);
            table.title = "{" + "loot_table." + (i + 1) + "}";
        }
    }

}
