package com.sky.hackday.recommendations;

import java.util.Random;

public class Recommendations {
    private final Random random = new Random();

    public Integer getInstances(String app) {
        return 15 + random.nextInt(15);
    }

    public Integer getAverageLoad(String appName) {
        return random.nextInt(70 - 60 + 1) + 60;
    }
}
