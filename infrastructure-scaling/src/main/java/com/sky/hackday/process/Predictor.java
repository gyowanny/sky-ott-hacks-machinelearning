package com.sky.hackday.process;


import com.sky.hackday.model.Programm;
import com.sky.hackday.model.ProgrammsSchedule;
import com.sky.hackday.model.Trends;
import com.sky.hackday.utils.GenericHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by MKH25 on 24/03/2017.
 */
public class Predictor {


    public static void predict() {

        HashMap<Date, Programm> schedule = ProgrammsSchedule.getProgrammsSchedule();
        for (Programm programm : schedule.values()) {
            predictTraffic(programm);
        }
    }


    private static void predictTraffic(Programm programm) {

        try {
            Trends programmTrends = GenericHelper.getTrends(programm.getTitle());
            double average = 0;
            Date currentDate = Calendar.getInstance().getTime();

            long daysToPlay = programm.getPlayingDate().getTime() / 1000 / 60 / 60 / 24 - currentDate.getTime() / 1000 / 60 / 60 / 24;

            for (Date dateTrendCaptured : programmTrends.trendsByDate.keySet()) {


                long daysTrendCaptured = currentDate.getTime() / 1000 / 60 / 60 / 24 - dateTrendCaptured.getTime() / 1000 / 60 / 60 / 24;

                double watage = (double)(100 - (26 - (daysToPlay - daysTrendCaptured))) / 100;

                average += programmTrends.trendsByDate.get(dateTrendCaptured) * watage;


            }
            average = average / 25;


            programm.setPredictedViewers((int)( average * 999999));
            int nodes = (int) average / 2;
            nodes = nodes < 3 ? 3 : nodes;
            programm.setRecomendedLiveNodes(nodes);
            programm.setRecomendedDownloadNodes(nodes / 3);
            programm.setRecomendedPreviewNodes(nodes / 4);
            programm.setRecomendedVodNodes(nodes / 2);
            System.out.println(programm.toString());
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}