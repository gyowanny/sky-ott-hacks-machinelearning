package com.sky.hackday.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfrastructureApplication {
    private String app;
    private int instances;
    private int averageLoad;

    public InfrastructureApplication(String app, int instances, int averageLoad) {
        this.app = app;
        this.instances = instances;
        this.averageLoad = averageLoad;
    }

    @JsonProperty
    public String getApp() {
        return app;
    }

    @JsonProperty
    public int getInstances() {
        return instances;
    }

    @JsonProperty
    public int getAverageLoad() {
        return averageLoad;
    }
}
