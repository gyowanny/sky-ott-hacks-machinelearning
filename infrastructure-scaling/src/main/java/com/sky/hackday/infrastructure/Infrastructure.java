package com.sky.hackday.infrastructure;

public interface Infrastructure {
    Integer getInstances(String appName);

    void scaleUp(String appName, int instances);

    void scaleDown(String appName, int instances);
}
