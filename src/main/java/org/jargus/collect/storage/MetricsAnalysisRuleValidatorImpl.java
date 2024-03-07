package org.jargus.collect.storage;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.model.MetricInfo;
import org.jargus.common.model.Metric;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class MetricsAnalysisRuleValidatorImpl implements MetricsAnalysisRuleValidator {
    private final MetricsAnalysisRuleStorage metricsAnalysisRuleStorage;
    
    @Override
    public boolean matches(MetricInfo metricInfo) {
        return false;
    }
}
