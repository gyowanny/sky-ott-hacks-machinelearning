package com.sky.hackday.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfrastructureApplication {
    private String app;
    private int instances;

    public InfrastructureApplication(String app, int instances) {
        this.app = app;
        this.instances = instances;
    }

    @JsonProperty
    public String getApp() {
        return app;
    }

    @JsonProperty
    public int getInstances() {
        return instances;
    }
}
