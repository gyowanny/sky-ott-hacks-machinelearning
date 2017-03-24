package com.sky.hackday;

import com.sky.hackday.resource.InfrastructureResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class App extends Application<InfrastructureScalingConfig> {
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    public void run(InfrastructureScalingConfig config, Environment environment) throws Exception {
        InfrastructureResource infrastructureResource = new InfrastructureResource();

        environment.jersey().register(infrastructureResource);
    }

    @Override
    public String getName() {
        return "infrastructure-scaling";
    }

}
