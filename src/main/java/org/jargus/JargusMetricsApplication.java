package org.jargus;

import org.jargus.database.configuration.StorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Kotelnikov D.M.
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageConfig.class)
public class JargusMetricsApplication {
    public static void main(String[] args) {
        SpringApplication.run(JargusMetricsApplication.class);
    }
}
