package org.jargus.database.dao;

import org.jargus.common.model.Label;
import org.jargus.common.model.Metric;
import org.jargus.database.models.Granularity;

import java.util.List;
import java.util.Optional;

/**
 * @author Kotelnikov D.M.
 */
public interface TsStorageClient {

    void addDataPoint(Metric metric);

    void addDataPoint(List<Metric> metrics);

    List<Metric> readMetrics(Granularity granularity,
                             Optional<Long> fromTime,
                             Optional<Long> toTime,
                             String metricName,
                             List<Label> labels);
}
