package org.jargus.database.configuration;

import org.jargus.database.dao.TsStorageClient;
import org.jargus.database.dao.TsStorageInMemoryClient;
import org.jargus.database.service.MetricsCleanUpScheduler;
import org.jargus.database.service.TsStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Kotelnikov D.M.
 */
@EnableScheduling
@Configuration
public class TsStorageConfiguration {

    @Bean
    public TsStorage tsStorage(StorageConfig storageConfig) {
        return new TsStorage(storageConfig.metricSecondsTtl(),
                storageConfig.metricMinutesTtl(),
                storageConfig.metricHoursTtl());
    }

    @Bean
    public MetricsCleanUpScheduler metricsCleanUpScheduler(TsStorage tsStorage) {
        return new MetricsCleanUpScheduler(tsStorage);
    }

    @Bean
    public TsStorageClient tsStorageClient(TsStorage tsStorage) {
        return new TsStorageInMemoryClient(tsStorage);
    }
}
