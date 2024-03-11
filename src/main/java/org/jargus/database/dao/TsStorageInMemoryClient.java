package org.jargus.database.dao;

import org.jargus.common.model.Label;
import org.jargus.common.model.Metric;
import org.jargus.database.models.Granularity;
import org.jargus.database.service.TsStorage;

import java.util.List;
import java.util.Optional;

/**
 * @author Kotelnikov D.M.
 */
public class TsStorageInMemoryClient implements TsStorageClient {

    private final TsStorage tsStorage;

    public TsStorageInMemoryClient(TsStorage tsStorage) {
        this.tsStorage = tsStorage;
    }


    @Override
    public void addDataPoint(Metric metric) {
        tsStorage.addDataPoint(metric);
    }

    @Override
    public void addDataPoint(List<Metric> metrics) {
        metrics.forEach(this::addDataPoint);
    }

    @Override
    public List<Metric> readMetrics(Granularity granularity,
                                         Optional<Long> fromTime,
                                         Optional<Long> toTime,
                                         String metricName,
                                         List<Label> labels) {
        return tsStorage.readMetrics(
                granularity,
                fromTime,
                toTime,
                metricName,
                labels);
    }
}
