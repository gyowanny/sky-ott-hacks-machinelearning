package com.bskyb.internettv.io;

import com.bskyb.internettv.model.Programm;
import com.bskyb.internettv.model.Trends;

import java.io.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.Properties;

/**
 * Created by MKH25 on 24/03/2017.
 */


public class FileHelper {

    private static String configPath = "C:\\Sky\\hackday\\project\\sky-tot-hacks-machinelearning\\src\\main\\resources\\";

    public static Map<String, Trends> trendsMap = new HashMap<String, Trends>();


    private static Map<String, String> properties = new HashMap<String, String>();


    public static void readTrends(String fileToParse) {
        BufferedReader fileReader = null;

        //Delimiter used in CSV file
        final String DELIMITER = ",";
        try {
            String line = "";
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(configPath + fileToParse));

            Trends trends = new Trends();
            //Read the file line by line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
                for (String token : tokens) {

                    trends.trendsByDate.put(new Date(Date.parse(tokens[0])), Integer.parseInt(tokens[1]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private static void readProperties(String fileName) {


        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(fileName);

            // load a properties file
            prop.load(input);

            for (String name : prop.stringPropertyNames()) {

                System.out.println(prop.getProperty("database"));
                ;

                properties.put(name, prop.getProperty(name));

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static String getProperty(String propertyKey) {

        if (properties == null || properties.containsKey(propertyKey)) {
            readProperties("config.properties");

        }
        return properties.get(propertyKey);
    }


    private static void raedAllTrends() {


        for (final File fileEntry : new File("trends").listFiles()) {
            readTrends(fileEntry.getName());
        }


    }

    public static void readSchedule(String fileToParse) {

        HashMap<Date, Programm> programmsSchedule = new HashMap<Date, Programm>();

        BufferedReader fileReader = null;

        //Delimiter used in CSV file
        final String DELIMITER = ",";
        try {
            String line = "";
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(configPath + fileToParse));
            String[] times = fileReader.readLine().split(DELIMITER); // times
            //Read the file line by line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
                for (int i = 1; i <= 4; i++) {
                    String title = tokens[i];
                    String dateTime = tokens[0] +" " + times[i];
                    Date date = new Date(Date.parse(dateTime));
                    Programm program = new Programm(title, date);
                    programmsSchedule.put(date, program);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}