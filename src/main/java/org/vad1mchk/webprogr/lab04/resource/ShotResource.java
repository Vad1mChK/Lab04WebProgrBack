package org.vad1mchk.webprogr.lab04.resource;

import io.jsonwebtoken.JwtException;
import jakarta.ejb.EJB;
import jakarta.security.auth.message.AuthException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.vad1mchk.webprogr.lab04.model.request.ShotRequestDto;
import org.vad1mchk.webprogr.lab04.model.response.ErrorMessageResponseDto;
import org.vad1mchk.webprogr.lab04.model.response.ShotResponseDto;
import org.vad1mchk.webprogr.lab04.service.ShotService;

import java.util.List;

@Path("/shot")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShotResource {
    @EJB
    private ShotService service;

    @GET
    public Response getShotsByOwner(@HeaderParam("Authorization") String authHeader) {
        Response errorResponse = checkAuthHeader(authHeader);
        if (errorResponse != null) return errorResponse;

        String jwt = jwtFromAuthHeader(authHeader);

        try {
            List<ShotResponseDto> responseDtoList = service.getShotsByOwner(jwt);
            return Response.ok(responseDtoList).build();
        } catch (AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorMessageResponseDto(e)).build();
        } catch (JwtException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageResponseDto(e)).build();
        }
    }

    @POST
    public Response addShot(@HeaderParam("Authorization") String authHeader, ShotRequestDto requestDto) {
        Response errorResponse = checkAuthHeader(authHeader);
        if (errorResponse != null) return errorResponse;

        String jwt = jwtFromAuthHeader(authHeader);
        try {
            ShotResponseDto responseDto = service.addShot(jwt, requestDto);
            return Response.ok(responseDto).build();
        } catch (AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorMessageResponseDto(e)).build();
        } catch (IllegalArgumentException | JwtException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageResponseDto(e)).build();
        }
    }

    @POST // Because the target server seems to ban DELETE
    @Path("/delete")
    public Response deleteShotsByOwner(@HeaderParam("Authorization") String authHeader) {
        Response errorResponse = checkAuthHeader(authHeader);
        if (errorResponse != null) return errorResponse;

        String jwt = jwtFromAuthHeader(authHeader);
        try {
            return Response.ok(service.deleteShotsByOwner(jwt)).build();
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
