package com.sky.hackday.infrastructure;

import java.util.List;

public class ScalingJob implements Runnable {

    private final InfrastructureScaling infrastructureScaling;
    private final List<String> applicationsToScale;

    public ScalingJob(InfrastructureScaling infrastructureScaling, List<String> applicationsToScale) {
        this.infrastructureScaling = infrastructureScaling;
        this.applicationsToScale = applicationsToScale;
    }

    @Override
    public void run() {
        applicationsToScale.forEach(infrastructureScaling::scale);
    }

}
