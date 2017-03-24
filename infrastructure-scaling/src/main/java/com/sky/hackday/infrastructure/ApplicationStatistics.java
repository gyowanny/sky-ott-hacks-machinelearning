package com.sky.hackday.infrastructure;

public class ApplicationStatistics {
    private final String name;
    private final Integer instances;
    private final Integer averageLoad;

    public ApplicationStatistics(String name, Integer instances, Integer averageLoad) {
        this.name = name;
        this.instances = instances;
        this.averageLoad = averageLoad;
    }

    public String getName() {
        return name;
    }

    public Integer getInstances() {
        return instances;
    }

    public Integer getAverageLoad() {
        return averageLoad;
    }
}
