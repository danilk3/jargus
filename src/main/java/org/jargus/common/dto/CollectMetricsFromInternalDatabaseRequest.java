package org.jargus.common.dto;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

/**
 * @author Bazhov N.S.
 */
@Getter
public class CollectMetricsFromInternalDatabaseRequest {

    private final Optional<String> fetchName;
    private final List<MetricRequest> metricRequests;

    public CollectMetricsFromInternalDatabaseRequest(Optional<String> fetchName,
                                                     List<MetricRequest> metricRequests) {
        this.fetchName = fetchName;
        this.metricRequests = metricRequests == null ? List.of() : metricRequests;
    }
}
