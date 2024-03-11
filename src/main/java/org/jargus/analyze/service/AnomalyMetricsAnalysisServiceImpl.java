package org.jargus.analyze.service;

import lombok.RequiredArgsConstructor;
import org.jargus.alert.client.AlertSystemClient;
import org.jargus.collect.model.RawMetric;
import org.jargus.analyze.storage.MetricsAnalysisRuleValidator;
import org.springframework.stereotype.Service;

/**
 * @author Bazhov N.S.
 */
@Service
@RequiredArgsConstructor
public class AnomalyMetricsAnalysisServiceImpl implements AnomalyMetricsAnalysisService {
    private final MetricsAnalysisRuleValidator metricsAnalysisRuleValidator;
//    private final AlertSystemClient alertSystemClient;

    @Override
    public void analyzeMetrics(RawMetric rawMetric) {
//        if (metricsAnalysisRuleValidator.matches(rawMetric)){
//            alertSystemClient.alert(rawMetric);
//        }
    }
}
