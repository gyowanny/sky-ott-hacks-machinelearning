package com.sky.hackday.resource;

import com.sky.hackday.infrastructure.Infrastructure;
import com.sky.hackday.model.Programm;
import com.sky.hackday.model.ProgrammsSchedule;
import com.sky.hackday.representation.InfrastructureApplication;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.stream.Collectors;

@Path("/infrastructure")
@Produces(MediaType.APPLICATION_JSON)
public class InfrastructureResource {
    private final Infrastructure infrastructure;

    public InfrastructureResource(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    @GET
    @Path("/current")
    public List<InfrastructureApplication> getApplications() {
        return infrastructure.getApplications()
                .stream()
                .map(app -> new InfrastructureApplication(
                        app, infrastructure.getInstances(app), infrastructure.getAverageLoad(app))
                )
                .collect(Collectors.toList());
    }


    @GET
    @Path("/predicted")
    public List<Programm> getApplications1() {
        HashMap<Date, Programm> schedule = ProgrammsSchedule.getProgrammsSchedule();
        List<Programm> programs = new ArrayList<>(schedule.values());
        Collections.sort(programs, (o1, o2) -> o1.getPlayingDate().compareTo(o2.getPlayingDate()));
        return programs;
    }


}
