package com.auto.resource;

import java.util.HashMap;
import java.util.Map;

import javax.json.stream.JsonGenerator;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.auto.resource.security.SecurityFilter;

public class AppResourceConfig extends ResourceConfig {
	public AppResourceConfig() {
		packages("com.auto.resource");
		register(RolesAllowedDynamicFeature.class);
		register(JacksonFeature.class);
		register(SecurityFilter.class);
		// Register my custom provider - not needed if it's in my.package.
		Map<String, Object> propsMap = new HashMap<String, Object>(1);

		propsMap.put(ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED,
				true);
		addProperties(propsMap);

		property(JsonGenerator.PRETTY_PRINTING, true);
	}
}
