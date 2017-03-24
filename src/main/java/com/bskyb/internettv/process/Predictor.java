package com.bskyb.internettv.process;


import com.bskyb.internettv.io.FileHelper;
import com.bskyb.internettv.model.Programm;
import com.bskyb.internettv.model.ProgrammsSchedule;
import com.bskyb.internettv.model.Trends;
import com.bskyb.internettv.utils.GenericHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

        Trends programmTrends = GenericHelper.getTrends(programm.getTitle());
        int average = 0;

                for(int trend : programmTrends.trendsByDate.values()){
                average += trend;

                }
                average = average /30;
        programm.setPredictedViewers(average * 999999);
        int  nodes = average/5;
        nodes = nodes <3?3:nodes;
        programm.setPredictedViewers(average );
        programm.setRecomendedLiveNodes(nodes);
        programm.setRecomendedDownloadNodes(nodes);
        programm.setRecomendedPreviewNodes(nodes);
        programm.setRecomendedVodNodes(nodes);

    }
}