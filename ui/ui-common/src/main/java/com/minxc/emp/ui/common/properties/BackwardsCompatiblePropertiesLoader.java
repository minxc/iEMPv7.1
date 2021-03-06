package com.minxc.emp.ui.common.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;



public class BackwardsCompatiblePropertiesLoader implements EnvironmentPostProcessor, Ordered {

    public static final int DEFAULT_ORDER = ConfigFileApplicationListener.DEFAULT_ORDER - 1;

    private static final PropertySourceFactory DEFAULT_PROPERTY_SOURCE_FACTORY = new DefaultPropertySourceFactory();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String[] DEPRECATED_LOCATIONS = {
        "classpath:/META-INF/flowable-ui-app/flowable-ui-app.properties",
        "classpath:flowable-ui-app.properties",
        "file:flowable-ui-app.properties"
    };

    private int order = DEFAULT_ORDER;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        ResourceLoader resourceLoader = application.getResourceLoader();
        if (resourceLoader == null) {
            resourceLoader = new DefaultResourceLoader();
        }
        MutablePropertySources propertySources = environment.getPropertySources();
        for (String location : DEPRECATED_LOCATIONS) {
            try {
                Resource resource = resourceLoader.getResource(location);
                PropertySource<?> propertySource = DEFAULT_PROPERTY_SOURCE_FACTORY.createPropertySource(null, new EncodedResource(resource));
                if (propertySources.contains(propertySource.getName())) {
                    propertySources.replace(propertySource.getName(), propertySource);
                } else {
                    propertySources.addLast(propertySource);
                }
                logger.warn("Using deprecated property source {} please switch to using Spring Boot externalized configuration", propertySource);
            } catch (IllegalArgumentException | FileNotFoundException | UnknownHostException ex) {
                // We are always ignoring the deprecated resources. This is done in the same way as in the Spring ConfigurationClassParsers
                // Placeholders not resolvable or resource not found when trying to open it
                if (logger.isInfoEnabled()) {
                    logger.info("Properties location [{}] not resolvable: {}", location, ex.getMessage());
                }
            } catch (IOException ex) {
                throw new UncheckedIOException("Failed to creaty property source", ex);
            }
        }
    }

    @Override
    public int getOrder() {
        return order;
    }
}
