package org.jargus.analysis.storage;

import lombok.RequiredArgsConstructor;
import org.jargus.common.model.Metric;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class MetricsAnalysisRuleValidatorImpl implements MetricsAnalysisRuleValidator {
    private final MetricsAnalysisRuleStorage metricsAnalysisRuleStorage;
    
    @Override
    public boolean matches(Metric metric) {
        return false;
    }
}
