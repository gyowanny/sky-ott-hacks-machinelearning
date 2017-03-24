package com.sky.hackday.recommendations;

import java.util.Random;

public class Recommendations {
    private final Random random = new Random();

    public Integer getInstances(String app) {
        return random.nextInt(100);
    }
}
