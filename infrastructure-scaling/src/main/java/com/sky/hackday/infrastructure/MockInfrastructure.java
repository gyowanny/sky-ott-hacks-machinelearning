package com.sky.hackday.infrastructure;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MockInfrastructure implements Infrastructure {
    private static final Map<String, Integer> infrastructure = new ConcurrentHashMap<>();

    public MockInfrastructure(List<String> applications) {
        applications.forEach(application -> infrastructure.put(application, 0));
    }

    @Override
    public Integer getInstances(String appName) {
        return infrastructure.get(appName);
    }

    @Override
    public void scaleUp(String appName, int instances) {
        write(appName, instances);
    }

    @Override
    public void scaleDown(String appName, int instances) {
        write(appName, instances);
    }

    @Override
    public Set<String> getApplications() {
        return infrastructure.keySet();
    }

    private void write(String appName, int instances) {
        infrastructure.put(appName, instances);
    }
}
