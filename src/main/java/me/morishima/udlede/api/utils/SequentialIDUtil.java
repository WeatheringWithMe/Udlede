package me.morishima.udlede.api.utils;

import me.morishima.udlede.Udlede;

public class SequentialIDUtil {
    public int idSequential = 0;

    public void update() {
        if (idSequential > 9999999) {
            Udlede.logger.warn("Over ID Limit!");
            idSequential = 0;
        }
        else ++idSequential;
    }

    public String getFormattedID() {
        update();
        return String.format("%07d", idSequential);
    }

}
