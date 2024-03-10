package org.jargus.collect.service;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.client.CollectMetricsClient;
import org.jargus.collect.model.ExportMetricsRequestParams;
import org.jargus.collect.model.RawMetrics;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class MetricsCollectionServiceImpl implements MetricsCollectionService {
    private final CollectMetricsClient collectMetricsClient;

    @Override
    public RawMetrics exportMetrics(ExportMetricsRequestParams exportMetricsRequestParams) {
        return collectMetricsClient.export(exportMetricsRequestParams);
    }
}
