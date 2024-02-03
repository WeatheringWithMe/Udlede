package me.morishima.udlede.api.utils;

public class SequentialIDUtil {

    public long idQuest = 0;

    public void update() {
        ++idQuest;
    }

    public String getFormattedID() {
        update();
        return String.format("%06d", idQuest);
    }

}
