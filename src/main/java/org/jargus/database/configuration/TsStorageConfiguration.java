package org.jargus.database.configuration;

import org.jargus.database.dao.TsStorageClient;
import org.jargus.database.dao.TsStorageInMemoryClient;
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
    public TsStorageClient tsStorageClient(StorageConfig storageConfig) {
        return new TsStorageInMemoryClient(storageConfig);
    }

}
