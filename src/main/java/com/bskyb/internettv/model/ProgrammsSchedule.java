package com.bskyb.internettv.model;

import com.bskyb.internettv.io.FileHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.bskyb.internettv.model.ProgrammsSchedule.getProgrammsSchedule;

/**
 * Created by MKH25 on 24/03/2017.
 */
public class ProgrammsSchedule {

    private static HashMap<Date, Programm> programmsSchedule = new HashMap<Date,Programm>();

    public static void init() {
        FileHelper.readSchedule("ProgrammSchedule.csv");

    }

    private static void addScheduledItem(Date date, String title) {

        programmsSchedule.put(date, new Programm(title, date));

    }


    public static HashMap<Date, Programm> getProgrammsSchedule() {
        return programmsSchedule;
    }

}
