package com.sky.hackday.infrastructure;

import java.util.Set;

public interface Infrastructure {
    Integer getInstances(String appName);

    Integer getAverageLoad(String appName);

    void scale(String appName, int instances, int averageLoad);

    Set<String> getApplications();
}
