package com.chapi.r2dbc.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PostgresqlConnectionProperties.class)
public class PropertiesConfig {
    // Empty class, its only purpose is to enable binding of your record
}
