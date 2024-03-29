package org.vad1mchk.webprogr.lab04.filter;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.util.Set;

@Provider
@PreMatching
@Priority(Priorities.HEADER_DECORATOR)
public class HttpMethodFilter implements ContainerRequestFilter {
    private static final Set<String> ALLOWED_METHODS = Set.of(
            "GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"
    );

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String methodOverride = requestContext.getHeaderString("X-Http-Method-Override");
        if (methodOverride != null) requestContext.setMethod(methodOverride);
        String method = requestContext.getMethod();

        if (!ALLOWED_METHODS.contains(method)) {
            requestContext.abortWith(
                    Response
                            .status(Response.Status.METHOD_NOT_ALLOWED)
                            .allow(ALLOWED_METHODS)
                            .build()
            );
        }
    }
}