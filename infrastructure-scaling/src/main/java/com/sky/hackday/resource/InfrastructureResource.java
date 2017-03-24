package com.sky.hackday.resource;

import com.sky.hackday.representation.InfrastructureApplication;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Path("/infrastructure")
@Produces(MediaType.APPLICATION_JSON)
public class InfrastructureResource {

    @GET
    public List<InfrastructureApplication> getApplications() {
        Random random = new Random();
        return Arrays.asList(
                new InfrastructureApplication("VOD", random.nextInt(100)),
                new InfrastructureApplication("Live", random.nextInt(100)),
                new InfrastructureApplication("Download", random.nextInt(100)),
                new InfrastructureApplication("Preview", random.nextInt(100))
        );
    }
}
