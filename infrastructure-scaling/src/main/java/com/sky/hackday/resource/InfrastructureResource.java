package com.sky.hackday.resource;

import com.sky.hackday.infrastructure.Infrastructure;
import com.sky.hackday.representation.InfrastructureApplication;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/infrastructure")
@Produces(MediaType.APPLICATION_JSON)
public class InfrastructureResource {
    private final Infrastructure infrastructure;

    public InfrastructureResource(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    @GET
    public List<InfrastructureApplication> getApplications() {
        return infrastructure.getApplications()
                .stream()
                .map(app -> new InfrastructureApplication(app, infrastructure.getInstances(app)))
                .collect(Collectors.toList());
    }
}
