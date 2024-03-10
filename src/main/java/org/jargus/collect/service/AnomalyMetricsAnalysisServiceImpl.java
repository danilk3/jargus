package org.jargus.collect.service;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.client.AlertSystemClient;
import org.jargus.collect.model.MetricInfo;
import org.jargus.collect.storage.MetricsAnalysisRuleValidator;
import org.jargus.common.model.Metric;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class AnomalyMetricsAnalysisServiceImpl implements AnomalyMetricsAnalysisService {
    private final MetricsAnalysisRuleValidator metricsAnalysisRuleValidator;
    private final AlertSystemClient alertSystemClient;

    @Override
    public void analyzeMetrics(MetricInfo metricInfo) {
        if (metricsAnalysisRuleValidator.matches(metricInfo)){
            alertSystemClient.alert(metricInfo);
        }
    }
}
