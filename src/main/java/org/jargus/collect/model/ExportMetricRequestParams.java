package org.jargus.collect.model;

import lombok.Getter;

/**
 * @author Bazhov N.S.
 */
@Getter
public class ExportMetricRequestParams {
    private String metricName;
    private String uri;

    public ExportMetricRequestParams(String metricName, String uri) {
        this.metricName = metricName;
        this.uri = uri;
    }
}
