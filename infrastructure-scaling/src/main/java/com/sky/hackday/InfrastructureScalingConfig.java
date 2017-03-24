package com.sky.hackday;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;
import java.util.List;

public class InfrastructureScalingConfig extends Configuration {
    @NotNull
    private List<String> applications;

    @JsonProperty
    public List<String> getApplications() {
        return applications;
    }

    @JsonProperty
    public void setApplications(List<String> applications) {
        this.applications = applications;
    }
}
