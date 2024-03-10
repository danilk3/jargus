package org.jargus.common.dto;


/**
 * @author Bazhov N.S.
 */
public abstract class CollectMetricsRequest {

    private final String metricName;

    public CollectMetricsRequest(String metricName) {
        this.metricName = metricName;
    }
}
