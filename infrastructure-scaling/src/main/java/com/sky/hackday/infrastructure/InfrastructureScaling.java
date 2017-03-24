package com.sky.hackday.infrastructure;

import com.sky.hackday.recommendations.Recommendations;

public class InfrastructureScaling {

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
            infrastructure.scaleUp(appName, recommendedInstances - currentInstances);
        } else {
            infrastructure.scaleDown(appName, currentInstances - recommendedInstances);
        }
    }

}
