package org.jargus;

import org.jargus.configuration.model.AppConfig;
import org.jargus.database.configuration.StorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Kotelnikov D.M.
 */
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@EnableConfigurationProperties({StorageConfig.class, AppConfig.class})
public class JargusMetricsApplication {
    public static void main(String[] args) {
        SpringApplication.run(JargusMetricsApplication.class);
    }
}
