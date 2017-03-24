package com.bskyb.internettv;

import com.bskyb.internettv.io.FileHelper;
import com.bskyb.internettv.model.ProgrammsSchedule;
import com.bskyb.internettv.process.Predictor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xalan.internal.xslt.Process;
import spark.Request;

import static spark.Spark.*;

public class Main {

    private static InfraScale infraScale;

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    public static void main(String[] args) {
        analyseAndPredict();
        //displayDashboard();
    }

    private static void analyseAndPredict() {

        ProgrammsSchedule.init();
        FileHelper.readTrends("trends\\Girls.csv");
        Predictor.predict();
    }

    private static void displayDashboard() {
        port(8080);

        enableCORS("*", "*", "Origin, X-Requested-With, Content-Type, Accept");

        get("/start", (req, res) -> startScale(req));
        get("/stop", (req, res) -> stopScale(req));
        get("/status", (req, res) -> getStatus(req));
    }

    private static String getStatus(Request req) throws Exception {
        if (infraScale == null) {
            return "NOT STARTED";
        }
        return jsonMapper.writeValueAsString(infraScale.getStatus());
    }

    private static String stopScale(Request req) {
        String response = infraScale.stop();
        infraScale = null;
        return response;
    }

    private static String startScale(Request req) {
        if (infraScale == null) {
            infraScale = new InfraScale();
            return infraScale.start();
        }
        return "ALREADY STARTED";
    }

    public static void enableCORS(final String origin, final String methods, final String headers) {
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            response.type("application/json");
        });
    }

}
