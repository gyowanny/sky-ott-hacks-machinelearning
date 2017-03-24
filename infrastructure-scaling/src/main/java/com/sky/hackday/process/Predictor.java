package com.sky.hackday.process;


import com.sky.hackday.model.Programm;
import com.sky.hackday.model.ProgrammsSchedule;
import com.sky.hackday.model.Trends;
import com.sky.hackday.utils.GenericHelper;

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

       try{ Trends programmTrends = GenericHelper.getTrends(programm.getTitle());
        int average = 0;

        for (int trend : programmTrends.trendsByDate.values()) {
            average += trend;

        }
        average = average / 30;
        programm.setPredictedViewers(average * 999999);
        int nodes = average / 2;
        nodes = nodes < 3 ? 3 : nodes;
        programm.setPredictedViewers(average);
        programm.setRecomendedLiveNodes(nodes);
        programm.setRecomendedDownloadNodes(nodes/2);
        programm.setRecomendedPreviewNodes(nodes/4);
        programm.setRecomendedVodNodes(nodes/3);
           System.out.println(programm.getTitle() + " Prediction is performed for this programm");
    }catch(Exception e){
e.printStackTrace();

    }

    }
}