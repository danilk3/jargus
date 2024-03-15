package org.jargus.database.dao;

import org.jargus.common.model.Label;
import org.jargus.common.model.Metric;
import org.jargus.database.configuration.StorageConfig;
import org.jargus.database.models.Granularity;
import org.jargus.database.service.TsStorage;

import java.util.*;

/**
 * @author Kotelnikov D.M.
 */
public class TsStorageInMemoryClient implements TsStorageClient {

    private final Map<String, TsStorage> tsStorageMap = new HashMap<>();
    private final StorageConfig storageConfig;

    public TsStorageInMemoryClient(StorageConfig storageConfig) {
        this.storageConfig = storageConfig;
    }


    @Override
    public void addDataPoint(String fetchName, Metric metric) {
        tsStorageMap.computeIfAbsent(fetchName, key -> new TsStorage(storageConfig))
                .addDataPoint(metric);
    }

    @Override
    public void addDataPoints(String fetchName, List<Metric> metrics) {
        metrics.forEach(metric -> addDataPoint(fetchName, metric));
    }

    @Override
    public List<Metric> readMetrics(Optional<String> fetchName,
                                    Granularity granularity,
                                    Optional<Long> fromTime,
                                    Optional<Long> toTime,
                                    String metricName,
                                    List<Label> labels) {
        if (fetchName.isEmpty()) {
            List<Metric> result = new ArrayList<>();
            tsStorageMap.values().forEach(storage ->
                    result.addAll(storage.readMetrics(
                            granularity,
                            fromTime,
                            toTime,
                            metricName,
                            labels)
                    )
            );
            return result;
        }
        return tsStorageMap.computeIfAbsent(fetchName.get(), key -> new TsStorage(storageConfig))
                .readMetrics(
                        granularity,
                        fromTime,
                        toTime,
                        metricName,
                        labels);
    }
}
