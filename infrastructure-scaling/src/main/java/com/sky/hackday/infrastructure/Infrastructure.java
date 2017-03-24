package com.sky.hackday.infrastructure;

import java.util.Set;

public interface Infrastructure {
    Integer getInstances(String appName);

    void scale(String appName, int instances);

    Set<String> getApplications();
}
