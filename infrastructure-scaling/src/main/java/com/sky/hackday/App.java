package com.sky.hackday;

import com.sky.hackday.infrastructure.Infrastructure;
import com.sky.hackday.infrastructure.MockInfrastructure;
import com.sky.hackday.resource.CorsFilter;
import com.sky.hackday.resource.InfrastructureResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class App extends Application<InfrastructureScalingConfig> {
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    public void run(InfrastructureScalingConfig config, Environment environment) throws Exception {
        InfrastructureResource infrastructureResource = new InfrastructureResource(infrastructure(config));

        environment.jersey().register(infrastructureResource);

        CorsFilter.insecure(environment);
    }

    private static Infrastructure infrastructure(InfrastructureScalingConfig config) {
        return new MockInfrastructure(config.getApplications());
    }

    @Override
    public String getName() {
        return "infrastructure-scaling";
    }

}
