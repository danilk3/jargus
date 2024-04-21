package org.jargus.common.dto;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

/**
 * @author Bazhov N.S.
 */
@Getter
public class CollectMetricsFromInternalDatabaseRequest {

    private final Optional<String> taskName;
    private final List<MetricRequest> metricRequests;

    public CollectMetricsFromInternalDatabaseRequest(Optional<String> taskName,
                                                     List<MetricRequest> metricRequests) {
        this.taskName = taskName;
        this.metricRequests = metricRequests == null ? List.of() : metricRequests;
    }
}
