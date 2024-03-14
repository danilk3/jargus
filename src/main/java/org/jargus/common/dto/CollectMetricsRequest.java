package org.jargus.common.dto;


import lombok.Getter;

/**
 * @author Bazhov N.S.
 */
@Getter
public abstract class CollectMetricsRequest {

    private String metricName;

    public CollectMetricsRequest(String metricName) {
        this.metricName = metricName;
    }
}
