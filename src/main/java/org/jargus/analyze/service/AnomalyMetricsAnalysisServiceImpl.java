package org.jargus.analyze.service;

import lombok.RequiredArgsConstructor;
import org.jargus.alert.client.AlertSystemClient;
import org.jargus.collect.model.RawMetrics;
import org.jargus.analyze.storage.MetricsAnalysisRuleValidator;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class AnomalyMetricsAnalysisServiceImpl implements AnomalyMetricsAnalysisService {
    private final MetricsAnalysisRuleValidator metricsAnalysisRuleValidator;
    private final AlertSystemClient alertSystemClient;

    @Override
    public void analyzeMetrics(RawMetrics rawMetrics) {
        if (metricsAnalysisRuleValidator.matches(rawMetrics)){
            alertSystemClient.alert(rawMetrics);
        }
    }
}
