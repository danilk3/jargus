package org.jargus.collect.service;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.client.CollectMetricsClient;
import org.jargus.collect.mapper.MetricMapper;
import org.jargus.collect.model.ExportMetricRequestParams;
import org.jargus.collect.model.RawMetric;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class MetricsCollectionServiceImpl implements MetricsCollectionService {
    private final CollectMetricsClient collectMetricsClient;

    @Override
    public RawMetric exportMetrics(ExportMetricRequestParams exportMetricRequestParams) {
        List<String> metrics = collectMetricsClient.export(exportMetricRequestParams);
        List<RawMetric> rawMetrics = new ArrayList<>();

        MetricMapper metricMapper = new MetricMapper();

        for (String metric :
                metrics) {
            rawMetrics.add(metricMapper.map(metric));
        }
        return null;
    }
}
