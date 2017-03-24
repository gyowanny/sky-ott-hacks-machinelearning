package com.sky.hackday.infrastructure;

import com.sky.hackday.recommendations.Recommendations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfrastructureScaling {

    private final Logger LOGGER = LoggerFactory.getLogger(InfrastructureScaling.class);
    private final Recommendations recommendations;
    private final Infrastructure infrastructure;

    public InfrastructureScaling(Recommendations recommendations, Infrastructure infrastructure) {
        this.recommendations = recommendations;
        this.infrastructure = infrastructure;
    }

    public void scale(String appName) {
        Integer recommendedInstances = recommendations.getInstances(appName);
        Integer averageLoad = recommendations.getAverageLoad(appName);
        infrastructure.scale(appName, recommendedInstances, averageLoad);
    }

}
