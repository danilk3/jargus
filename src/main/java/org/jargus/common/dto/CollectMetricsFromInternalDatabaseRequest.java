package org.jargus.common.dto;

import org.jargus.common.model.DataPoint;

/**
 * @author Bazhov N.S.
 */
public class CollectMetricsFromInternalDatabaseRequest extends CollectMetricsRequest {
    private final DataPoint from;
    private final DataPoint to;

    public CollectMetricsFromInternalDatabaseRequest(String MetricName, DataPoint from, DataPoint to) {
        super(MetricName);
        this.from = from;
        this.to = to;
    }
}
