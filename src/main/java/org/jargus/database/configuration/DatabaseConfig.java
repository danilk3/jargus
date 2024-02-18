package org.jargus.database.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @author Kotelnikov D.M.
 */
@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record DatabaseConfig(String url,
                             String username,
                             String password,
                             String driverClassName,
                             long shapshotSaveInterval) {}
