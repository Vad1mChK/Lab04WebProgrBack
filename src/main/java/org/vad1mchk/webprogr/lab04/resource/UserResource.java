package org.vad1mchk.webprogr.lab04.resource;

import jakarta.ejb.EJB;
import jakarta.security.auth.message.AuthException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.vad1mchk.webprogr.lab04.model.request.UserRequestDto;
import org.vad1mchk.webprogr.lab04.model.response.ErrorMessageResponseDto;
import org.vad1mchk.webprogr.lab04.model.response.UserResponseDto;
import org.vad1mchk.webprogr.lab04.service.UserService;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @EJB
    private UserService service;

    @GET
    public Response getAllUsers() {
        return Response.ok(service.getAllUsers()).build();
    }

    @POST
    @Path("/auth")
    public Response registerUser(UserRequestDto requestDto) {
        UserResponseDto responseDto;
        try {
            responseDto = service.registerUser(requestDto);
        } catch (AuthException | IllegalArgumentException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessageResponseDto(e))
                    .build();
        }
        return Response.ok(responseDto).build();
    }

    @GET
    @Path("/auth")
    public Response loginUser(UserRequestDto requestDto) {
        // TODO Return JWT
        UserResponseDto responseDto;
        try {
            responseDto = service.logInUser(requestDto);
        } catch (AuthException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessageResponseDto(e))
                    .build();
        }
        return Response.ok(requestDto).build();
    }
}
