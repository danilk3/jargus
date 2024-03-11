package org.jargus.common.dto;

/**
 * @author Bazhov N.S.
 */
public class CollectMetricsInTimeRequest {

    private final String metricName;

    public CollectMetricsInTimeRequest(String metricName) {
        this.metricName = metricName;
    }
}
