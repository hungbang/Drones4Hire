package com.drones4hire.dronesapp.dbaccess.utils;

import java.util.Random;

public class DataGenerator {

    public static Integer generateNumber(int bound) {
        int num = 0;
        Random random = new Random();
        num = random.nextInt(bound);
        return num;
    }
}
