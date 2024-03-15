package org.jargus.common.dto;

import lombok.Getter;

/**
 * @author Bazhov N.S.
 */
@Getter
public class CollectMetricsInTimeRequest extends CollectMetricsRequest{
    private String uri;

    public CollectMetricsInTimeRequest(String metricName, String uri) {
        super(metricName);
        this.uri = uri;
    }
}
