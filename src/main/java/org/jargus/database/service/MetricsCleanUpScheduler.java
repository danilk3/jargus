package org.jargus.database.service;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Kotelnikov D.M.
 */
public class MetricsCleanUpScheduler {

    private final TsStorage tsStorage;

    public MetricsCleanUpScheduler(TsStorage tsStorage) {
        this.tsStorage = tsStorage;
    }

    @Scheduled(fixedRateString = "${tsdb.cleanUpFixedDelay}")
    public void cleanUp() {
        tsStorage.cleanUpMetrics();
    }
}
