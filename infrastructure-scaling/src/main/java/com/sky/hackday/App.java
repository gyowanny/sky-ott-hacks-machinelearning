package com.sky.hackday;

import com.sky.hackday.infrastructure.Infrastructure;
import com.sky.hackday.infrastructure.InfrastructureScaling;
import com.sky.hackday.infrastructure.MockInfrastructure;
import com.sky.hackday.infrastructure.ScalingJob;
import com.sky.hackday.io.FileHelper;
import com.sky.hackday.model.ProgrammsSchedule;
import com.sky.hackday.process.Predictor;
import com.sky.hackday.recommendations.Recommendations;
import com.sky.hackday.resource.CorsFilter;
import com.sky.hackday.resource.InfrastructureResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App extends Application<InfrastructureScalingConfig> {
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    private static void analyseAndPredict() {
        ProgrammsSchedule.init();
        FileHelper.readAllTrends();
        Predictor.predict();
    }

    public void run(InfrastructureScalingConfig config, Environment environment) throws Exception {

        analyseAndPredict();
        InfrastructureResource infrastructureResource = new InfrastructureResource(infrastructure(config));

        environment.jersey().register(infrastructureResource);
        ScheduledExecutorService scheduler = environment.lifecycle()
                .scheduledExecutorService("Scaling").build();

        scheduler.scheduleWithFixedDelay(job(config), 5, 2, TimeUnit.SECONDS);

        CorsFilter.insecure(environment);
    }

    private static Infrastructure infrastructure(InfrastructureScalingConfig config) {
        return new MockInfrastructure(config.getApplications());
    }

    private ScalingJob job(InfrastructureScalingConfig config) {
        return new ScalingJob(
                new InfrastructureScaling(new Recommendations(), infrastructure(config)),
                config.getApplications()
        );
    }

    @Override
    public String getName() {
        return "infrastructure-scaling";
    }

}
