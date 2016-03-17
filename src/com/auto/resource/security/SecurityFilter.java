package com.auto.resource.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.auto.exceptions.NotAuthorizedException;

public class SecurityFilter extends AbstractAuthenticationProcessingFilter {
	private static final Log LOGGER = LogFactory.getLog(SecurityFilter.class);

	protected SecurityFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
		setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler());
		setAuthenticationManager(new CustomAuthenticationManager());
	}

	/*
	 * private static final ServerResponse ACCESS_DENIED = new ServerResponse(
	 * "access denied for this user", 401, new Headers<Object>()); private
	 * static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse(
	 * "nobody can access this resource", 403, new Headers<Object>());
	 */

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) req;
		String role = httpRequest.getHeader("role");
		String userType = httpRequest.getHeader("userType");
		String path = ((HttpServletRequest) httpRequest).getRequestURI();
		if (role == null || role.isEmpty() || userType == null
				|| userType.isEmpty()) {
			throw new NotAuthorizedException("access denied");
		}
		LOGGER.info("REQUEST URL" + path);
		AbstractAuthenticationToken userAuthenticationToken = null;

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (role.equalsIgnoreCase("user") && userType.equalsIgnoreCase("1")) {
			authorities.add(new SimpleGrantedAuthority("USER"));
		} else if (role.equalsIgnoreCase("admin")
				&& userType.equalsIgnoreCase("2")) {
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
		} else {
			throw new NotAuthorizedException("access denied");
		}
		User principal = new User(userType, "", authorities);
		userAuthenticationToken = new UsernamePasswordAuthenticationToken(
				principal, "", principal.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				userAuthenticationToken);
		super.doFilter(req, res, chain);
	}

	/*
	 * @Context private ResourceInfo resourceInfo;
	 * 
	 * public void filter(ContainerRequestContext arg0) throws IOException {
	 * 
	 * Method method = resourceInfo.getResourceMethod(); // Access allowed for
	 * all if (!method.isAnnotationPresent(PermitAll.class)) { // Access denied
	 * for all if (method.isAnnotationPresent(DenyAll.class)) {
	 * arg0.abortWith(ACCESS_FORBIDDEN); return; }
	 * 
	 * // Get request headers final MultivaluedMap<String, String> headers =
	 * arg0.getHeaders();
	 * 
	 * // Fetch authorization header final List<String> authorization =
	 * headers.get("role");
	 * 
	 * if (authorization == null || authorization.isEmpty()) {
	 * arg0.abortWith(ACCESS_DENIED); return; }
	 * 
	 * final String role = authorization.get(0);
	 * 
	 * // Verify user access if (method.isAnnotationPresent(RolesAllowed.class))
	 * { RolesAllowed rolesAnnotation = method
	 * .getAnnotation(RolesAllowed.class); Set<String> rolesSet = new
	 * HashSet<String>( Arrays.asList(rolesAnnotation.value()));
	 * 
	 * // Is user valid? if (!rolesSet.contains(role.toUpperCase())) {
	 * arg0.abortWith(ACCESS_DENIED); return; } } } }
	 */

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {
		return null;
	}
}