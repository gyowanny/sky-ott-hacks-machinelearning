package com.sky.hackday.model;

import java.util.Date;

/**
 * Created by MKH25 on 24/03/2017.
 */
public class Programm {

    private String title;
    private Date playingDate;
    private int predictedViewers;
    private int recomendedLiveNodes;
    private int recomendedDownloadNodes;
    private int recomendedPreviewNodes;
    private int recomendedVodNodes;


    public Programm(String title, Date date) {
        this.setTitle(title);
        this.setPlayingDate(date);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPlayingDate() {
        return playingDate;
    }

    public void setPlayingDate(Date playingDate) {
        this.playingDate = playingDate;
    }

    public int getPredictedViewers() {
        return predictedViewers;
    }

    public void setPredictedViewers(int predictedViewers) {
        this.predictedViewers = predictedViewers;
    }


    public int getRecomendedLiveNodes() {
        return recomendedLiveNodes;
    }

    public void setRecomendedLiveNodes(int recomendedLiveNodes) {
        this.recomendedLiveNodes = recomendedLiveNodes;
    }

    public int getRecomendedDownloadNodes() {
        return recomendedDownloadNodes;
    }

    public void setRecomendedDownloadNodes(int recomendedDownloadNodes) {
        this.recomendedDownloadNodes = recomendedDownloadNodes;
    }

    public int getRecomendedPreviewNodes() {
        return recomendedPreviewNodes;
    }

    public void setRecomendedPreviewNodes(int recomendedPreviewNodes) {
        this.recomendedPreviewNodes = recomendedPreviewNodes;
    }

    public int getRecomendedVodNodes() {
        return recomendedVodNodes;
    }

    public void setRecomendedVodNodes(int recomendedVodNodes) {
        this.recomendedVodNodes = recomendedVodNodes;
    }

    public String toString() {


        return "========================================================\n"+

                "Programm Title: " + this.getTitle() + "\n" +
                "Programm Play Time: " + this.getPlayingDate() + "\n" +


                "Number of expected Viewers = " + getPredictedViewers() + "\n" +
                "Number of recomended live nodes = " + getRecomendedLiveNodes() + "\n" +
                "Number of recomended vod nodes = " + getRecomendedVodNodes() + "\n" +
                "Number of recomended download nodes = " + getRecomendedDownloadNodes() + "\n" +
                "Number of recomended preview nodes = " + getRecomendedPreviewNodes() + "\n" + "\n";

    }

}
