package com.sky.hackday.infrastructure;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MockInfrastructure implements Infrastructure {
    private static final Map<String, ApplicationStatistics> infrastructure = new ConcurrentHashMap<>();

    public MockInfrastructure(List<String> applications) {
        applications.forEach(application -> infrastructure
                .put(application, new ApplicationStatistics(application, 0, 0)));
    }

    @Override
    public Integer getInstances(String appName) {
        return infrastructure.get(appName).getInstances();
    }

    @Override
    public Integer getAverageLoad(String appName) {
        return infrastructure.get(appName).getAverageLoad();
    }

    @Override
    public void scale(String appName, int instances, int averageLoad) {
        write(appName, instances, averageLoad);
    }

    @Override
    public Set<String> getApplications() {
        return infrastructure.keySet();
    }

    private void write(String appName, int instances, int averageLoad) {
        infrastructure.put(appName, new ApplicationStatistics(appName, instances, averageLoad));
    }
}
