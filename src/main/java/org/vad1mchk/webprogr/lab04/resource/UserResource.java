package org.vad1mchk.webprogr.lab04.resource;

import jakarta.ejb.EJB;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import org.vad1mchk.webprogr.lab04.model.request.UserRequestDto;
import org.vad1mchk.webprogr.lab04.security.Secured;
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
    public Response getAllUsers() {
        JsonArray array;
        try {
            array = JsonUtils.serializeListOfUserResponseDto(service.getAllUsers());
        } catch (IllegalArgumentException e) {
            return Response.serverError().build();
        }
        return Response.ok(service.getAllUsers()).build();
    }

    @DELETE
    @Secured
    public Response deleteAll() {
        return Response.status(418).build();
    }
}
