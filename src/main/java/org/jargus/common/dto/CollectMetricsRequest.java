package org.jargus.common.dto;


import lombok.Getter;

/**
 * @author Bazhov N.S.
 */
@Getter
public abstract class CollectMetricsRequest {

    private String metricName;
    private String fetchName;

    public CollectMetricsRequest(String metricName, String fetchName) {
        this.metricName = metricName;
        this.fetchName = fetchName;
    }
}
