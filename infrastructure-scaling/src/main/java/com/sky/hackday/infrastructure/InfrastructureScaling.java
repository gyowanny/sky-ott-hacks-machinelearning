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
        Integer currentInstances = infrastructure.getInstances(appName);
        Integer recommendedInstances = recommendations.getInstances(appName);

        if (recommendedInstances > currentInstances) {
            int instances = recommendedInstances - currentInstances;

            log(appName, instances);
            infrastructure.scaleUp(appName, instances);
        } else {
            int instances = currentInstances - recommendedInstances;

            log(appName, instances);
            infrastructure.scaleDown(appName, instances);
        }
    }

    private void log(String appName, int instances) {
        LOGGER.debug("Scaling {} to {} instances", appName, instances);
    }


}
