package org.jargus.analyze.storage;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.model.RawMetric;
import org.springframework.stereotype.Component;

/**
 * @author Bazhov N.S.
 */
@Component
@RequiredArgsConstructor
public class MetricsAnalysisRuleValidatorImpl implements MetricsAnalysisRuleValidator {
    private final MetricsAnalysisRuleStorage metricsAnalysisRuleStorage;
    
    @Override
    public boolean matches(RawMetric rawMetric) {
        return false;
    }
}
