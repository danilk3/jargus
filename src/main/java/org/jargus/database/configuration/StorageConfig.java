package org.jargus.database.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import java.time.Duration;

/**
 * @author Kotelnikov D.M.
 */
@Validated
@ConfigurationProperties(prefix = "tsdb", ignoreUnknownFields = false)
public record StorageConfig(Duration metricSecondsTtl,
                            Duration metricMinutesTtl,
                            Duration metricHoursTtl) {
}
