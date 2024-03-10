package org.jargus.analyze.storage;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.model.RawMetric;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class MetricsAnalysisRuleValidatorImpl implements MetricsAnalysisRuleValidator {
    private final MetricsAnalysisRuleStorage metricsAnalysisRuleStorage;
    
    @Override
    public boolean matches(RawMetric rawMetric) {
        return false;
    }
}
