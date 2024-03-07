package org.jargus;

import org.jargus.database.configuration.DatabaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Kotelnikov D.M.
 */
@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(DatabaseConfig.class)
public class JargusMetricsApplication {
    public static void main(String[] args) {
        SpringApplication.run(JargusMetricsApplication.class);
    }
}
