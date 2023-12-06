package org.vad1mchk.webprogr.lab04.resource;

import jakarta.ejb.EJB;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.vad1mchk.webprogr.lab04.service.UserService;
import org.vad1mchk.webprogr.lab04.util.JsonUtils;

import java.util.HashMap;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @EJB
    private UserService service;

    @GET
    @Path("/all")
    public Response getAllUsers() {
        JsonArray array;
        try {
            array = JsonUtils.serializeListOfUserResponseDto(service.getAllUsers());
        } catch (IllegalArgumentException e) {
            return Response.serverError().build();
        }
        return Response.ok(array).build();
    }
}
