package com.auto.filter;

/*import java.io.IOException;
 import java.lang.reflect.Method;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;

 import javax.annotation.security.DenyAll;
 import javax.annotation.security.PermitAll;
 import javax.annotation.security.RolesAllowed;
 import javax.ws.rs.container.ContainerRequestContext;
 import javax.ws.rs.core.MultivaluedMap;
 import javax.ws.rs.ext.Provider;

 import org.jboss.resteasy.annotations.interception.ServerInterceptor;
 import org.jboss.resteasy.core.Headers;
 import org.jboss.resteasy.core.ResourceMethodInvoker;
 import org.jboss.resteasy.core.ServerResponse;*/

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;

@Provider
public class SrcurityFilter implements
		javax.ws.rs.container.ContainerRequestFilter {
	private static final ServerResponse ACCESS_DENIED = new ServerResponse(
			"access denied for this user", 401, new Headers<Object>());
	private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse(
			"nobody can access this resource", 403, new Headers<Object>());

	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext arg0) throws IOException {
		
		Method method = resourceInfo.getResourceMethod();
		// Access allowed for all
		if (!method.isAnnotationPresent(PermitAll.class)) {
			// Access denied for all
			if (method.isAnnotationPresent(DenyAll.class)) {
				arg0.abortWith(ACCESS_FORBIDDEN);
				return;
			}

			// Get request headers
			final MultivaluedMap<String, String> headers = arg0.getHeaders();

			// Fetch authorization header
			final List<String> authorization = headers.get("role");

			if (authorization == null || authorization.isEmpty()) {
				arg0.abortWith(ACCESS_DENIED);
				return;
			}

			final String role = authorization.get(0);

			// Verify user access
			if (method.isAnnotationPresent(RolesAllowed.class)) {
				RolesAllowed rolesAnnotation = method
						.getAnnotation(RolesAllowed.class);
				Set<String> rolesSet = new HashSet<String>(
						Arrays.asList(rolesAnnotation.value()));

				// Is user valid?
				if (!rolesSet.contains(role.toUpperCase())) {
					arg0.abortWith(ACCESS_DENIED);
					return;
				}
			}
		}
	}
}
