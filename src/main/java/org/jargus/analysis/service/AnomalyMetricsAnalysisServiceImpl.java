package org.jargus.analysis.service;

import lombok.RequiredArgsConstructor;
import org.jargus.alert.client.AlertSystemClient;
import org.jargus.analysis.storage.MetricsAnalysisRuleValidator;
import org.jargus.common.model.Metric;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class AnomalyMetricsAnalysisServiceImpl implements AnomalyMetricsAnalysisService {
    private final MetricsAnalysisRuleValidator metricsAnalysisRuleValidator;

    private final AlertSystemClient alertSystemClient;

    @Override
    public void analyzeMetrics(Metric metric) {

    }
}
