package com.sky.hackday.infrastructure;

import java.util.Set;

public interface Infrastructure {
    Integer getInstances(String appName);

    void scaleUp(String appName, int instances);

    void scaleDown(String appName, int instances);

    Set<String> getApplications();
}
