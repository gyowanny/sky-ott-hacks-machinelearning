package com.sky.hackday.recommendations;

import com.sky.hackday.model.Programm;
import com.sky.hackday.model.ProgrammsSchedule;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class Recommendations {
    private final Random random = new Random();

    public Integer getInstances(String app) {
        return random.nextInt(100);
    }
}
