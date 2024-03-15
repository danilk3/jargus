package org.jargus.database.dao;

import org.jargus.common.model.Label;
import org.jargus.common.model.Metric;
import org.jargus.database.models.Granularity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Kotelnikov D.M.
 */
@Component
public interface TsStorageClient {

    void addDataPoint(String fetchName, Metric metric);

    void addDataPoints(String fetchName, List<Metric> metrics);

    List<Metric> readMetrics(Optional<String> fetchName,
                             Granularity granularity,
                             Optional<Long> fromTime,
                             Optional<Long> toTime,
                             String metricName,
                             List<Label> labels);
}
