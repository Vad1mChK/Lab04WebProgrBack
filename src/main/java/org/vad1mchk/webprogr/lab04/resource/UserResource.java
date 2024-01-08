package org.vad1mchk.webprogr.lab04.resource;

import io.jsonwebtoken.JwtException;
import jakarta.ejb.EJB;
import jakarta.security.auth.message.AuthException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import org.vad1mchk.webprogr.lab04.model.request.UserRequestDto;
import org.vad1mchk.webprogr.lab04.model.response.ErrorMessageResponseDto;
import org.vad1mchk.webprogr.lab04.model.response.JwtResponseDto;
import org.vad1mchk.webprogr.lab04.model.response.UserResponseDto;
import org.vad1mchk.webprogr.lab04.service.UserService;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @EJB
    private UserService service;

    @POST
    @Path("/register")
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

    @POST
    @Path("/login")
    public Response loginUser(UserRequestDto requestDto) {
        JwtResponseDto responseDto;
        try {
            responseDto = service.loginUser(requestDto);
        } catch (AuthException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessageResponseDto(e))
                    .build();
        }
        return Response.ok(responseDto).build();
    }

    @POST
    @Path("/logout")
    public Response logoutUser(@HeaderParam("Authorization") String authHeader) {
        Response errorResponse = checkAuthHeader(authHeader);
        if (errorResponse != null) return errorResponse;

        String jwt = jwtFromAuthHeader(authHeader);

        try {
            service.logoutUser(jwt);
        } catch (AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorMessageResponseDto(e)).build();
        }

        return Response.ok().entity("User logged out successfully.").build();
    }

    @GET
    @Path("/whoami")
    public Response whoAmI(@HeaderParam("Authorization") String authHeader) {
        Response errorResponse = checkAuthHeader(authHeader);
        if (errorResponse != null) return errorResponse;

        String jwt = jwtFromAuthHeader(authHeader);

        try {
            return Response.ok().entity(service.whoAmI(jwt)).build();
        } catch (AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorMessageResponseDto(e)).build();
        } catch (JwtException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageResponseDto(e)).build();
        }
    }

    private Response checkAuthHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(
                    new ErrorMessageResponseDto(new AuthException("Неверный заголовок Authorization."))
            ).build();
        }
        return null;
    }

    private String jwtFromAuthHeader(String authHeader) {
        return authHeader.substring(7);
    }
}
