package org.jargus.analyze.service;

import lombok.RequiredArgsConstructor;
import org.jargus.analyze.storage.MetricsAnalysisRuleValidator;
import org.jargus.common.model.Metric;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
@Service
@RequiredArgsConstructor
public class AnomalyMetricsAnalysisServiceImpl implements AnomalyMetricsAnalysisService {
    private final MetricsAnalysisRuleValidator metricsAnalysisRuleValidator;
//    private final AlertSystemClient alertSystemClient;

    @Override
    public void analyzeMetrics(List<Metric> metrics) {
        for (Metric metric: metrics) {
            metricsAnalysisRuleValidator.matches(metric);
        }
    }
}
